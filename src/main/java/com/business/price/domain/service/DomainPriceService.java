package com.business.price.domain.service;

import com.business.price.adapters.PriceInboundAdapter;
import com.business.price.application.exception.DatabaseConnectionException;
import com.business.price.application.response.PriceResponse;
import com.business.price.domain.Price;
import com.business.price.domain.repository.PriceRepository;
import com.business.price.application.exception.NoDataFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;

@Service
@AllArgsConstructor
public class DomainPriceService implements PriceService {

    private final PriceRepository priceRepository;

    @Override
    public PriceResponse findPriceFromDateAndProductAndBrand(int brandId, int productId,
                                                             LocalDateTime applicationDate) throws DatabaseConnectionException {
        return priceRepository.findByProductAndBrandDate(brandId, productId, applicationDate)
                .stream()
                .max(Comparator.comparing(Price::getPriority))
                .map(PriceInboundAdapter::toDto).orElseThrow(NoDataFoundException::new);
    }
}
