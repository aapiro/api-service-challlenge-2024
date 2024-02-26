package com.business.price.infraestructure.repository;

import com.business.price.application.exception.DatabaseConnectionException;
import com.business.price.domain.Price;
import com.business.price.domain.repository.PriceRepository;
import com.business.price.adapters.PriceOutboundAdapter;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class SqlDbPriceRepository implements PriceRepository {
    private final SqlPriceRepository sqlPriceRepository;

    public SqlDbPriceRepository(SqlPriceRepository sqlPriceRepository) {
        this.sqlPriceRepository = sqlPriceRepository;
    }

    @Override
    @Retryable(value = DataAccessException.class, maxAttempts = 5, backoff = @Backoff(delay = 5000))
    public List<Price> findByProductAndBrandDate(Integer brandId, Integer productId, LocalDateTime date) throws DatabaseConnectionException {
        try {
            return sqlPriceRepository.findByProductAndBrandDate(brandId, productId, date, PageRequest.of(0, 10))
                    .stream()
                    .map(PriceOutboundAdapter::toDomain)
                    .toList();
        } catch (DataAccessException e) {
            throw new DatabaseConnectionException("Could not connect to the database.");
        }
    }
}
