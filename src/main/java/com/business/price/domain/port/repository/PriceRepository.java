package com.business.price.domain.port.repository;

import com.business.price.common.exception.DatabaseConnectionException;
import com.business.price.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository {

    Optional<Price> findByProductAndBrandDate(Integer brandId, Integer productId, LocalDateTime date) throws DatabaseConnectionException;
}
