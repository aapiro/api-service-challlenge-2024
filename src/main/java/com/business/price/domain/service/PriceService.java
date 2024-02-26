package com.business.price.domain.service;

import com.business.price.application.exception.DatabaseConnectionException;
import com.business.price.application.response.PriceResponse;

import java.time.LocalDateTime;

public interface PriceService {
    PriceResponse findPriceFromDateAndProductAndBrand(int brandId, int productId, LocalDateTime applicationDate) throws DatabaseConnectionException;
}
