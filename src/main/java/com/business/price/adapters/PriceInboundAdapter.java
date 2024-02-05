package com.business.price.adapters;

import com.business.price.application.response.PriceResponse;
import com.business.price.domain.Price;

public class PriceInboundAdapter {
    
    public static PriceResponse toDto(Price price) {
        return PriceResponse.builder()
                .price(price.getPrice())
                .brandId(price.getBrandId())
                .productId(price.getProductId())
                .startPriceDate(price.getStartDate())
                .endPriceDate(price.getEndDate())
                .priceList(price.getPriceList())
                .build();
    }

    private PriceInboundAdapter() {
    }
}
