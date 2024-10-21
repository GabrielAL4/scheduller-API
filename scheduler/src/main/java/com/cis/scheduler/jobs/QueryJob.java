package com.cis.scheduler.jobs;

import com.cis.scheduler.dto.QueryDTO;
import com.cis.scheduler.service.QueryExecutorService;
import com.cis.scheduler.service.FileService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.Map;

@Component
public class QueryJob implements Job {

    @Autowired
    private QueryExecutorService queryExecutorService;

    @Autowired
    private FileService fileService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // Aqui, você pode obter a query que deseja executar
        QueryDTO queryDTO = (QueryDTO) context.getMergedJobDataMap().get("queryDTO");

        try {
            // Executa a query e obtém os resultados
            List<Map<String, Object>> results = queryExecutorService.executeQuery(queryDTO);

            // Gera o arquivo Excel com os resultados
            File excelFile = fileService.generateExcelFile(results);
            System.out.println("Arquivo Excel gerado com sucesso em: " + excelFile.getAbsolutePath());
        } catch (Exception e) {
            System.err.println("Erro ao executar a job: " + e.getMessage());
            throw new JobExecutionException(e);
        }
    }
}
