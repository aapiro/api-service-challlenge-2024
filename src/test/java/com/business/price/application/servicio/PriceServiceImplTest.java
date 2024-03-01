package com.business.price.application.servicio;

import com.business.price.application.dto.PriceDTO;
import com.business.price.common.exception.DatabaseConnectionException;
import com.business.price.common.exception.NoDataFoundException;
import com.business.price.domain.model.Price;
import com.business.price.domain.port.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
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

        Price price = new Price();
        price.setPrice(100.0);
        price.setBrandId(brandId);
        price.setProductId(productId);
        price.setStartDate(LocalDateTime.of(2022, 3, 15, 10, 30));
        price.setEndDate(LocalDateTime.of(2022, 3, 15, 10, 30));
        price.setPriceList(1);

        PriceDTO expectedResponse = PriceDTO.builder()
                .price(100.0)
                .brandId(brandId)
                .productId(productId)
                .startPriceDate(LocalDateTime.of(2022, 3, 15, 10, 30))
                .endPriceDate(LocalDateTime.of(2022, 3, 15, 10, 30))
                .priceList(1)
                .build();

        // Mocking PriceRepository
        when(priceRepository.findByProductAndBrandDate(brandId, productId, applicationDate))
                .thenReturn(Optional.of(price));

        // Act
        PriceDTO result = priceService.findPriceFromDateAndProductAndBrand(brandId, productId, applicationDate);

        // Assert
        assertNotNull(result);
        assertEquals(expectedResponse, result);
    }
    @Test
    public void testFindPriceFromDateAndProductAndBrand_NoDataFoundException() throws DatabaseConnectionException {
        // Arrange
        int brandId = 1;
        int productId = 2;
        LocalDateTime applicationDate = LocalDateTime.of(2022, 3, 15, 10, 30);

        // Mocking PriceRepository to return an empty list
        when(priceRepository.findByProductAndBrandDate(brandId, productId, applicationDate))
                .thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NoDataFoundException.class,
                () -> priceService.findPriceFromDateAndProductAndBrand(brandId, productId, applicationDate));
    }
}
