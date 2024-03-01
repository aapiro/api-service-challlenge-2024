package com.business.price.infraestructure.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface SqlPriceRepository extends CrudRepository<PriceEntity, Integer> {

    @Query(value = "SELECT * FROM PRICES p WHERE p.brand_id = :brandId AND p.product_id = :productId AND p.start_date <= :date AND p.end_date > :date ORDER BY p.priority DESC LIMIT 1", nativeQuery = true)
    Optional<PriceEntity> findByProductAndBrandDate(Integer brandId, Integer productId, LocalDateTime date);
}
