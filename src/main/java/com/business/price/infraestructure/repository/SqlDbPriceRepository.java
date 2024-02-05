package com.business.price.infraestructure.repository;

import com.business.price.domain.Price;
import com.business.price.domain.repository.PriceRepository;
import com.business.price.adapters.PriceOutboundAdapter;
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
    public List<Price> findByProductAndBrandDate(Integer brandId, Integer productId, LocalDateTime date) {
        return sqlPriceRepository.findByProductAndBrandDate(brandId, productId, date).stream().map(PriceOutboundAdapter::toDomain).toList();
    }
}
