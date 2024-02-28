package com.business.price.domain.port.service;

import com.business.price.common.exception.DatabaseConnectionException;
import com.business.price.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceService {
    Optional<Price> findPriceFromDateAndProductAndBrand(int brandId, int productId, LocalDateTime applicationDate) throws DatabaseConnectionException;
}
