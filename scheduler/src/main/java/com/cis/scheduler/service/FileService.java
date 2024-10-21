package com.cis.scheduler.service;

import com.cis.scheduler.util.ExcelUtil;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class FileService {

    private final ExcelUtil excelUtil;

    public FileService(ExcelUtil excelUtil) {
        this.excelUtil = excelUtil;
    }

    /**
     * Gera um arquivo Excel a partir dos resultados da query.
     *
     * @param queryResults Lista de Mapas representando os resultados da query
     * @return Arquivo Excel gerado
     * @throws IOException Se ocorrer erro durante a geração do arquivo
     */
    public File generateExcelFile(List<Map<String, Object>> queryResults) throws IOException {
        // Chama o utilitário para criar o arquivo Excel
        String filePath = excelUtil.generateExcelFile(queryResults); // Chama o método correto
        return new File(filePath); // Retorna um objeto File baseado no caminho gerado
    }
}
