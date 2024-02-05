package com.business.price.domain.service;

import com.business.price.application.response.PriceResponse;
import com.business.price.infraestructure.repository.PriceEntity;
import com.business.price.infraestructure.repository.PriceRepository;
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
                                                             LocalDateTime applicationDate) {
        return priceRepository.findByProductAndBrandDate(brandId, productId, applicationDate)
                .stream()
                .max(Comparator.comparing(PriceEntity::getPriority))
                .map(priceResult -> PriceResponse.builder()
                        .price(priceResult.getPrice())
                        .brandId(priceResult.getBrandId())
                        .productId(priceResult.getProductId())
                        .startPriceDate(priceResult.getStartDate())
                        .endPriceDate(priceResult.getEndDate())
                        .priceList(priceResult.getPriceList())
                        .build()).orElseThrow(NoDataFoundException::new);
    }
}
