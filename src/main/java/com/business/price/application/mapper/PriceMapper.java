package com.business.price.application.mapper;

import com.business.price.application.dto.PriceDTO;
import com.business.price.domain.model.Price;
import com.business.price.infraestructure.repository.PriceEntity;

public class PriceMapper {
    
    public static PriceDTO toDto(Price price) {
        return PriceDTO.builder()
                .price(price.getPrice())
                .brandId(price.getBrandId())
                .productId(price.getProductId())
                .startPriceDate(price.getStartDate())
                .endPriceDate(price.getEndDate())
                .priceList(price.getPriceList())
                .build();
    }
    public static Price toDomain(PriceEntity priceEntity) {
        Price price = new Price();
        price.setId(priceEntity.getId());
        price.setBrandId(priceEntity.getBrandId());
        price.setStartDate(priceEntity.getStartDate());
        price.setEndDate(priceEntity.getEndDate());
        price.setPriceList(priceEntity.getPriceList());
        price.setProductId(priceEntity.getProductId());
        price.setPriority(priceEntity.getPriority());
        price.setPrice(priceEntity.getPrice());
        price.setCurrency(priceEntity.getCurrency());

        return price;
    }
    private PriceMapper() {
    }
}
