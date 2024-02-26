package com.business.price.domain.repository;

import com.business.price.application.exception.DatabaseConnectionException;
import com.business.price.domain.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository {

    List<Price>  findByProductAndBrandDate(Integer brandId, Integer productId, LocalDateTime date) throws DatabaseConnectionException;
}
