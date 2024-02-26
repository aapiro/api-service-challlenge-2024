package com.business.price.infraestructure.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SqlPriceRepository extends CrudRepository<PriceEntity, Integer> {

    @Query(value = "select p from PriceEntity p where p.brandId = :brandId and p.productId = :productId and p.startDate <= :date and p.endDate > :date")
    List<PriceEntity> findByProductAndBrandDate(Integer brandId, Integer productId, LocalDateTime date, Pageable pageable);
}
