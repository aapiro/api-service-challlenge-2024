package com.business.price.domain.ports;

import com.business.price.adapters.api.PriceResponse;

import java.time.LocalDateTime;

public interface PriceService {
    PriceResponse findPriceFromDateAndProductAndBrand(Integer brandId, Integer productId, LocalDateTime applicationDate);
}
