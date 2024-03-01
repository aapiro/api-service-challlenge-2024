package com.business.price.domain.port.service;

import com.business.price.application.dto.PriceDTO;
import com.business.price.common.exception.DatabaseConnectionException;

import java.time.LocalDateTime;

public interface PriceService {
    PriceDTO findPriceFromDateAndProductAndBrand(int brandId, int productId, LocalDateTime applicationDate) throws DatabaseConnectionException;
}
