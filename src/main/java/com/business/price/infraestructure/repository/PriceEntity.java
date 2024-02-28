package com.business.price.infraestructure.repository;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRICES")
public class PriceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "BRAND_ID", nullable = false)
    private Integer brandId;
    @Column(name = "START_DATE", nullable = false)
    private LocalDateTime startDate;
    @Column(name = "END_DATE", nullable = false)
    private LocalDateTime endDate;
    @Column(name = "PRICE_LIST", nullable = false)
    private Integer priceList;
    @Column(name = "PRODUCT_ID", nullable = false)
    private Integer productId;
    @Column(name = "PRIORITY", nullable = false)
    private Integer priority;
    @Column(name = "PRICE", nullable = false)
    private Double price;
    @Column(name = "CURR", nullable = false)
    private String currency;

  }
