package com.cis.scheduler.dto;

import com.cis.scheduler.model.DatabaseType;

public class QueryDTO {

    private Long id; // ID da query
    private String title; // Título da query
    private String queryString; // String da query
    private DatabaseType databaseType; // Tipo de banco selecionado
    private String frequency; // Periodicidade (diária, semanal, etc.)
    private String filePath; // Caminho para salvar o arquivo Excel

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public DatabaseType getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(DatabaseType databaseType) {
        this.databaseType = databaseType;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
