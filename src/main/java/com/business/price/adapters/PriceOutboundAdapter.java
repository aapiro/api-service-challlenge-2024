package com.business.price.adapters;

import com.business.price.domain.Price;
import com.business.price.infraestructure.repository.PriceEntity;

public class PriceOutboundAdapter {

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

    private PriceOutboundAdapter() {
    }
}
