package com.cis.scheduler.service;

import com.cis.scheduler.dto.QueryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SchedulerService {

    @Autowired
    private QueryExecutorService queryExecutorService;

    @Autowired
    private FileService fileService;

    /**
     * Método agendado que será executado periodicamente.
     * Esse método executa a query, gera o Excel e faz o que for necessário com ele (salvar, enviar, etc.).
     */
    @Scheduled(cron = "0 0 0 * * ?") // Agendado para rodar todos os dias à meia-noite
    public void scheduledQueryExecution() {
        // Defina a query que você quer executar periodicamente
        QueryDTO queryDTO = new QueryDTO();
        queryDTO.setQueryString("SELECT * FROM sua_tabela WHERE ...");

        // Executa a query
        List<Map<String, Object>> results = queryExecutorService.executeQuery(queryDTO);

        // Gera o arquivo Excel com os resultados
        try {
            fileService.generateExcelFile(results);
            System.out.println("Arquivo Excel gerado com sucesso.");
        } catch (Exception e) {
            System.err.println("Erro ao gerar arquivo Excel: " + e.getMessage());
        }
    }
}
