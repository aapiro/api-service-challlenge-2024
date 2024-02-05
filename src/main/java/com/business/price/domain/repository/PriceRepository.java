package com.business.price.domain.repository;

import com.business.price.domain.Price;
import com.business.price.infraestructure.repository.PriceEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository {

    List<Price>  findByProductAndBrandDate(Integer brandId, Integer productId, LocalDateTime date);
}
