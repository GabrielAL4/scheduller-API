package com.cis.scheduler.model;

import java.util.Map;

public class Query {

    private Long id; // Adiciona um campo para o ID da query
    private String queryString;        // A string da query SQL
    private Map<String, Object> params; // Parâmetros da query, caso seja necessário
    private String description;        // Descrição ou nome da query, caso relevante
    private String databaseName;       // Nome do banco de dados (se for multi-banco)
    private boolean isScheduled;       // Se a query está agendada para execução

    // Construtores
    public Query() {}

    public Query(Long id, String queryString, Map<String, Object> params, String description, String databaseName, boolean isScheduled) {
        this.id = id; // Inicializa o campo id
        this.queryString = queryString;
        this.params = params;
        this.description = description;
        this.databaseName = databaseName;
        this.isScheduled = isScheduled;
    }

    // Getters e Setters
    public Long getId() {
        return id; // Adiciona o getter para id
    }

    public void setId(Long id) {
        this.id = id; // Adiciona o setter para id
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public boolean isScheduled() {
        return isScheduled;
    }

    public void setScheduled(boolean scheduled) {
        isScheduled = scheduled;
    }

    @Override
    public String toString() {
        return "Query{" +
                "id=" + id + // Inclui o id na representação em string
                ", queryString='" + queryString + '\'' +
                ", params=" + params +
                ", description='" + description + '\'' +
                ", databaseName='" + databaseName + '\'' +
                ", isScheduled=" + isScheduled +
                '}';
    }
}
