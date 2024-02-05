package com.business.price.domain.service;

import com.business.price.application.response.PriceResponse;
import com.business.price.domain.Price;
import com.business.price.domain.repository.PriceRepository;
import com.business.price.application.exception.NoDataFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DomainPriceServiceTest {

    @InjectMocks
    private DomainPriceService priceService;

    @Mock
    private PriceRepository sqlPriceRepository;

    @Test
    public void testFindPriceFromDateAndProductAndBrand() {

        int brandId = 1;
        int productId = 2;
        LocalDateTime applicationDate = LocalDateTime.of(2022, 3, 15, 10, 30);

        Price price = new Price();
        price.setPrice(100.0);
        price.setBrandId(brandId);
        price.setProductId(productId);
        price.setStartDate(LocalDateTime.of(2022, 3, 15, 10, 30));
        price.setEndDate(LocalDateTime.of(2022, 3, 15, 10, 30));
        price.setPriceList(1);

        PriceResponse expectedResponse = PriceResponse.builder()
                .price(100.0)
                .brandId(brandId)
                .productId(productId)
                .startPriceDate(LocalDateTime.of(2022, 3, 15, 10, 30))
                .endPriceDate(LocalDateTime.of(2022, 3, 15, 10, 30))
                .priceList(1)
                .build();

        // Mocking PriceRepository
        when(sqlPriceRepository.findByProductAndBrandDate(brandId, productId, applicationDate))
                .thenReturn(Collections.singletonList(price));

        // Act
        PriceResponse result = priceService.findPriceFromDateAndProductAndBrand(brandId, productId, applicationDate);

        // Assert
        assertNotNull(result);
        assertEquals(expectedResponse, result);
    }

    @Test
    public void testFindPriceFromDateAndProductAndBrand_NoDataFoundException() {
        // Arrange
        int brandId = 1;
        int productId = 2;
        LocalDateTime applicationDate = LocalDateTime.of(2022, 3, 15, 10, 30);

        // Mocking PriceRepository to return an empty list
        when(sqlPriceRepository.findByProductAndBrandDate(brandId, productId, applicationDate))
                .thenReturn(Collections.emptyList());

        // Act and Assert
        assertThrows(NoDataFoundException.class,
                () -> priceService.findPriceFromDateAndProductAndBrand(brandId, productId, applicationDate));
    }
}
