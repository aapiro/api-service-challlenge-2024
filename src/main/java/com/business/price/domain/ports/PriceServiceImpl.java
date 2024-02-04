package com.business.price.domain.ports;

import com.business.price.adapters.api.PriceResponse;
import com.business.price.adapters.db.PriceEntity;
import com.business.price.exception.NoDataFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;

@Service
@AllArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    @Override
    public PriceResponse findPriceFromDateAndProductAndBrand(Integer brandId, Integer productId,
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
