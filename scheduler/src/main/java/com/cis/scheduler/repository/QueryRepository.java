package com.cis.scheduler.repository;

import com.cis.scheduler.model.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class QueryRepository {

    private final JdbcTemplate jdbcTemplate;

    public QueryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Método para salvar uma nova query no banco de dados.
     *
     * @param query Objeto Query a ser salvo
     */
    public void save(Query query) {
        String sql = "INSERT INTO queries (query_string) VALUES (?)";
        jdbcTemplate.update(sql, query.getQueryString());
    }

    /**
     * Método para buscar todas as queries do banco de dados.
     *
     * @return Lista de objetos Query
     */
    public List<Query> findAll() {
        String sql = "SELECT * FROM queries";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Query q = new Query();
            q.setId(rs.getLong("id")); // Supondo que há uma coluna id
            q.setQueryString(rs.getString("query_string"));
            return q;
        });
    }

    /**
     * Método para buscar uma query pelo ID.
     *
     * @param id ID da query
     * @return Um Optional contendo a Query, se encontrada
     */
    public Optional<Query> findById(Long id) {
        String sql = "SELECT * FROM queries WHERE id = ?";
        List<Query> queries = jdbcTemplate.query(sql, new Object[]{id}, (rs, rowNum) -> {
            Query q = new Query();
            q.setId(rs.getLong("id"));
            q.setQueryString(rs.getString("query_string"));
            return q;
        });

        return queries.stream().findFirst(); // Retorna o primeiro resultado, se houver
    }

    /**
     * Método para verificar se uma query existe pelo ID.
     *
     * @param id ID da query
     * @return true se a query existir, false caso contrário
     */
    public boolean existsById(Long id) {
        String sql = "SELECT COUNT(*) FROM queries WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{id}, Integer.class);
        return count != null && count > 0; // Retorna true se o count for maior que 0
    }

    /**
     * Método para deletar uma query pelo ID.
     *
     * @param id ID da query a ser deletada
     */
    public void deleteById(Long id) {
        String sql = "DELETE FROM queries WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
