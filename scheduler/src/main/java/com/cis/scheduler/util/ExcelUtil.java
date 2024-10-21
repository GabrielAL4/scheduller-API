package com.cis.scheduler.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class ExcelUtil {

    /**
     * Gera um arquivo Excel a partir dos resultados da query.
     *
     * @param queryResults Lista de mapas com os resultados da query
     * @return Caminho do arquivo Excel gerado
     * @throws IOException Se ocorrer um erro ao gerar o arquivo
     */
    public String generateExcelFile(List<Map<String, Object>> queryResults) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Resultados");

        createHeaderRow(sheet, queryResults);
        fillDataRows(sheet, queryResults);

        String filePath = "resultados_query.xlsx"; // Pode usar um caminho absoluto ou relativo
        File file = new File(filePath);

        try (FileOutputStream fileOut = new FileOutputStream(file)) {
            workbook.write(fileOut);
        } finally {
            workbook.close();
        }

        return file.getAbsolutePath(); // Retorna o caminho do arquivo gerado
    }

    private void createHeaderRow(Sheet sheet, List<Map<String, Object>> queryResults) {
        Row headerRow = sheet.createRow(0);
        if (!queryResults.isEmpty()) {
            Map<String, Object> firstRow = queryResults.get(0);
            int cellIndex = 0;

            Workbook workbook = sheet.getWorkbook();
            CellStyle headerStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            headerStyle.setFont(font); // Aplica a fonte ao estilo

            for (String columnName : firstRow.keySet()) {
                Cell cell = headerRow.createCell(cellIndex++);
                cell.setCellValue(columnName);
                cell.setCellStyle(headerStyle); // Aplica o estilo ao cabeçalho
            }
        }
    }

    private void fillDataRows(Sheet sheet, List<Map<String, Object>> queryResults) {
        int rowIndex = 1; // Começa na segunda linha, após o cabeçalho
        for (Map<String, Object> result : queryResults) {
            Row dataRow = sheet.createRow(rowIndex++);
            int cellIndex = 0;
            for (Object value : result.values()) {
                Cell cell = dataRow.createCell(cellIndex++);
                if (value instanceof Number) {
                    cell.setCellValue(((Number) value).doubleValue());
                } else {
                    cell.setCellValue(value != null ? value.toString() : "");
                }
            }
        }
    }
}
