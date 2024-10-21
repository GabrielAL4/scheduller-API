package com.cis.scheduler.service;

import com.cis.scheduler.dto.QueryDTO;
import com.cis.scheduler.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class QueryExecutorService {

    private final JdbcTemplate jdbcTemplate;
    private final ExcelUtil excelUtil;

    @Autowired
    public QueryExecutorService(JdbcTemplate jdbcTemplate, ExcelUtil excelUtil) {
        this.jdbcTemplate = jdbcTemplate;
        this.excelUtil = excelUtil;
    }

    /**
     * Executa a query e retorna os resultados.
     *
     * @param queryDTO A DTO da query a ser executada
     * @return Lista de resultados, onde cada resultado é um mapa de chave/valor
     */
    public List<Map<String, Object>> executeQuery(QueryDTO queryDTO) {
        // Verifica qual banco de dados usar baseado no tipo selecionado
        String sql = queryDTO.getQueryString(); // Obtém a query

        // Dependendo do tipo de banco, você pode usar diferentes JdbcTemplate ou lógica
        switch (queryDTO.getDatabaseType()) {
            case SQLSERVER_PROTHEUS:
                // Execute a query no banco SQLServer_CIS_DATA
                return jdbcTemplate.queryForList(sql);
            case SQLSERVER_RM:
                // Execute a query no banco SQLServer_CORPORERM
                return jdbcTemplate.queryForList(sql);
            case ORACLE_WMS_PRD:
                // Execute a query no banco Oracle_WMS_PRD
                return jdbcTemplate.queryForList(sql);
            case ORACLE_TECFOOD_PRD:
                // Execute a query no banco Oracle_Tecfood_PRD
                return jdbcTemplate.queryForList(sql);
            default:
                throw new IllegalArgumentException("Tipo de banco de dados não suportado: " + queryDTO.getDatabaseType());
        }
    }
    /**
     * Executa a query e gera um arquivo Excel.
     *
     * @param queryDTO A DTO da query a ser executada
     * @return O caminho do arquivo Excel gerado
     */
    public String executeQueryAndGenerateExcel(QueryDTO queryDTO) {
        // Executa a consulta SQL
        List<Map<String, Object>> results = executeQuery(queryDTO);

        // Gera o arquivo Excel com os resultados
        try {
            String excelFilePath = excelUtil.generateExcelFile(results); // Alterado para String
            return excelFilePath; // Retorna o caminho absoluto do arquivo gerado
        } catch (IOException e) {
            throw new RuntimeException("Erro ao gerar o arquivo Excel: " + e.getMessage(), e);
        }
    }

}
