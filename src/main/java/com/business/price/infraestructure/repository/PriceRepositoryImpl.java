package com.business.price.infraestructure.repository;

import com.business.price.application.mapper.PriceMapper;
import com.business.price.common.exception.DatabaseConnectionException;
import com.business.price.domain.model.Price;
import com.business.price.domain.port.repository.PriceRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class PriceRepositoryImpl implements PriceRepository {
    private final SqlPriceRepository sqlPriceRepository;

    public PriceRepositoryImpl(SqlPriceRepository sqlPriceRepository) {
        this.sqlPriceRepository = sqlPriceRepository;
    }

    @Override
    public Optional<Price> findByProductAndBrandDate(Integer brandId, Integer productId, LocalDateTime date) throws DatabaseConnectionException {
        try {
            return sqlPriceRepository.findByProductAndBrandDate(brandId, productId, date)
                    .stream()
                    .map(PriceMapper::toDomain)
                    .findFirst();
        } catch (DataAccessException  e) {
            throw new DatabaseConnectionException("Error connecting database.");
        }
    }
}
