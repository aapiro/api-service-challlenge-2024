package com.business.price.domain.service;

import com.business.price.common.exception.DatabaseConnectionException;
import com.business.price.domain.model.Price;
import com.business.price.domain.port.repository.PriceRepository;
import com.business.price.domain.port.service.PriceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    @Override
    public Optional<Price> findPriceFromDateAndProductAndBrand(int brandId, int productId,
                                                               LocalDateTime applicationDate) throws DatabaseConnectionException {
        return priceRepository.findByProductAndBrandDate(brandId, productId, applicationDate);

    }
}
