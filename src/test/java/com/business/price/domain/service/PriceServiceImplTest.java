package com.business.price.domain.service;

import com.business.price.common.exception.DatabaseConnectionException;
import com.business.price.application.dto.PriceDTO;
import com.business.price.domain.model.Price;
import com.business.price.domain.port.repository.PriceRepository;
import com.business.price.common.exception.NoDataFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PriceServiceImplTest {

    @InjectMocks
    private PriceServiceImpl priceService;

    @Mock
    private PriceRepository priceRepository;

    @Test
    public void testFindPriceFromDateAndProductAndBrand() throws DatabaseConnectionException {

        int brandId = 1;
        int productId = 2;
        LocalDateTime applicationDate = LocalDateTime.of(2022, 3, 15, 10, 30);

        Price expectedResponse = new Price();
        expectedResponse.setPrice(100.0);
        expectedResponse.setBrandId(brandId);
        expectedResponse.setProductId(productId);
        expectedResponse.setStartDate(LocalDateTime.of(2022, 3, 15, 10, 30));
        expectedResponse.setEndDate(LocalDateTime.of(2022, 3, 15, 10, 30));
        expectedResponse.setPriceList(1);

        // Mocking PriceRepository
        when(priceRepository.findByProductAndBrandDate(brandId, productId, applicationDate))
                .thenReturn(Optional.of(expectedResponse));

        // Act
        Optional<Price> result = priceService.findPriceFromDateAndProductAndBrand(brandId, productId, applicationDate);

        // Assert
        assertNotNull(result);
        assertEquals(expectedResponse, result.get());
    }
}
