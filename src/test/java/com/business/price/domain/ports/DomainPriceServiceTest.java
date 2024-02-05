package com.business.price.domain.ports;

import com.business.price.application.response.PriceResponse;
import com.business.price.infraestructure.repository.PriceRepository;
import com.business.price.infraestructure.repository.PriceEntity;
import com.business.price.domain.service.DomainPriceService;
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
    private PriceRepository priceRepository;

    @Test
    public void testFindPriceFromDateAndProductAndBrand() {

        int brandId = 1;
        int productId = 2;
        LocalDateTime applicationDate = LocalDateTime.of(2022, 3, 15, 10, 30);

        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setPrice(100.0);
        priceEntity.setBrandId(brandId);
        priceEntity.setProductId(productId);
        priceEntity.setStartDate(LocalDateTime.of(2022, 3, 15, 10, 30));
        priceEntity.setEndDate(LocalDateTime.of(2022, 3, 15, 10, 30));
        priceEntity.setPriceList(1);

        PriceResponse expectedResponse = PriceResponse.builder()
                .price(100.0)
                .brandId(brandId)
                .productId(productId)
                .startPriceDate(LocalDateTime.of(2022, 3, 15, 10, 30))
                .endPriceDate(LocalDateTime.of(2022, 3, 15, 10, 30))
                .priceList(1)
                .build();

        // Mocking PriceRepository
        when(priceRepository.findByProductAndBrandDate(brandId, productId, applicationDate))
                .thenReturn(Collections.singletonList(priceEntity));

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
        when(priceRepository.findByProductAndBrandDate(brandId, productId, applicationDate))
                .thenReturn(Collections.emptyList());

        // Act and Assert
        assertThrows(NoDataFoundException.class,
                () -> priceService.findPriceFromDateAndProductAndBrand(brandId, productId, applicationDate));
    }
}
