package com.cis.scheduler.controller;

import com.cis.scheduler.dto.QueryDTO;
import com.cis.scheduler.service.QueryExecutorService;
import com.cis.scheduler.service.QueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/query")
public class QueryController {

    private final QueryService queryService;
    private final QueryExecutorService queryExecutorService;

    public QueryController(QueryService queryService, QueryExecutorService queryExecutorService) {
        this.queryService = queryService;
        this.queryExecutorService = queryExecutorService;
    }

    @PostMapping
    public ResponseEntity<QueryDTO> createQuery(@RequestBody QueryDTO queryDTO) {
        QueryDTO createdQuery = queryService.save(queryDTO);
        return ResponseEntity.ok(createdQuery);
    }

    @GetMapping
    public ResponseEntity<List<QueryDTO>> getAllQueries() {
        List<QueryDTO> queries = queryService.findAll();
        return ResponseEntity.ok(queries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QueryDTO> getQueryById(@PathVariable Long id) {
        QueryDTO queryDTO = queryService.findById(id);
        return ResponseEntity.ok(queryDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QueryDTO> updateQuery(@PathVariable Long id, @RequestBody QueryDTO queryDTO) {
        queryDTO.setId(id);
        QueryDTO updatedQuery = queryService.update(queryDTO);
        return ResponseEntity.ok(updatedQuery);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuery(@PathVariable Long id) {
        queryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Novo endpoint para executar a query e gerar o Excel
    @PostMapping("/{id}/execute")
    public ResponseEntity<String> executeQuery(@PathVariable Long id) {
        QueryDTO queryDTO = queryService.findById(id);
        String excelFilePath = queryExecutorService.executeQueryAndGenerateExcel(queryDTO);
        return ResponseEntity.ok(excelFilePath);
    }
}
