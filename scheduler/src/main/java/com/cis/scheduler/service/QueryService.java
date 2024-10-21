package com.cis.scheduler.service;

import com.cis.scheduler.dto.QueryDTO;
import com.cis.scheduler.model.Query;
import com.cis.scheduler.repository.QueryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QueryService {

    private final QueryRepository queryRepository;

    public QueryService(QueryRepository queryRepository) {
        this.queryRepository = queryRepository;
    }

    public QueryDTO save(QueryDTO queryDTO) {
        Query query = new Query();
        query.setQueryString(queryDTO.getQueryString());
        // Aqui você pode definir outros campos se necessário, como params, description, etc.
        queryRepository.save(query);
        queryDTO.setId(query.getId());
        return queryDTO;
    }

    public List<QueryDTO> findAll() {
        return queryRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public QueryDTO findById(Long id) {
        Optional<Query> optionalQuery = queryRepository.findById(id);
        if (optionalQuery.isPresent()) {
            return convertToDTO(optionalQuery.get());
        } else {
            // Aqui você pode lançar uma exceção ou retornar null dependendo do seu design
            throw new RuntimeException("Query not found with id: " + id);
        }
    }

    public QueryDTO update(QueryDTO queryDTO) {
        // Verifica se a query existe
        Optional<Query> optionalQuery = queryRepository.findById(queryDTO.getId());
        if (optionalQuery.isPresent()) {
            Query query = optionalQuery.get();
            query.setQueryString(queryDTO.getQueryString());
            // Atualize outros campos se necessário
            queryRepository.save(query); // Salva a query atualizada
            return convertToDTO(query);
        } else {
            throw new RuntimeException("Query not found with id: " + queryDTO.getId());
        }
    }

    public void delete(Long id) {
        if (queryRepository.existsById(id)) {
            queryRepository.deleteById(id);
        } else {
            throw new RuntimeException("Query not found with id: " + id);
        }
    }

    private QueryDTO convertToDTO(Query query) {
        QueryDTO dto = new QueryDTO();
        dto.setId(query.getId());
        dto.setQueryString(query.getQueryString());
        // Adicione outros campos se necessário
        return dto;
    }
}
