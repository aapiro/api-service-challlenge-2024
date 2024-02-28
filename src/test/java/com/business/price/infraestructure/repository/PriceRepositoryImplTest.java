package com.business.price.infraestructure.repository;

import com.business.price.common.exception.DatabaseConnectionException;
import com.business.price.domain.model.Price;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceRepositoryImplTest {

    @InjectMocks
    private PriceRepositoryImpl priceRepository;

    @Mock
    private SqlPriceRepository sqlPriceRepository;

    @Test
    void findByProductAndBrandDate() throws DatabaseConnectionException {

        int brandId = 1;
        int productId = 2;
        LocalDateTime applicationDate = LocalDateTime.of(2022, 3, 15, 10, 30);

        List<PriceEntity> priceEntityList = new ArrayList<>();
        priceEntityList.add(PriceEntity.builder()
                .price(100.0)
                .brandId(brandId)
                .productId(productId)
                .startDate(LocalDateTime.of(2022, 3, 15, 10, 30))
                .endDate(LocalDateTime.of(2022, 3, 15, 10, 30))
                .priceList(1)
                .build());
        Price expectedResponse = new Price();
        expectedResponse.setPrice(100.0);
        expectedResponse.setBrandId(brandId);
        expectedResponse.setProductId(productId);
        expectedResponse.setStartDate(LocalDateTime.of(2022, 3, 15, 10, 30));
        expectedResponse.setEndDate(LocalDateTime.of(2022, 3, 15, 10, 30));
        expectedResponse.setPriceList(1);

        // Mocking PriceRepository
        when(sqlPriceRepository.findByProductAndBrandDate(any(),any(), any(), any()))
                .thenReturn(priceEntityList);

        // Act
        Optional<Price> result = priceRepository.findByProductAndBrandDate(brandId, productId, applicationDate);

        // Assert
        assertNotNull(result);
        assertEquals(expectedResponse.getBrandId(), result.get().getBrandId());
        assertEquals(expectedResponse.getProductId(), result.get().getProductId());
        assertEquals(expectedResponse.getPrice(), result.get().getPrice());
        assertEquals(expectedResponse.getStartDate(), result.get().getStartDate());
        assertEquals(expectedResponse.getEndDate(), result.get().getEndDate());
        assertEquals(expectedResponse.getPriceList(), result.get().getPriceList());
    }
    @Test
    public void testFindByProductAndBrandDate_ValidateException() {
        given(sqlPriceRepository.findByProductAndBrandDate(any(), any(), any(), any()))
                .willThrow(new DataAccessException("test") {});

        assertThrows(DatabaseConnectionException.class, () ->
                priceRepository.findByProductAndBrandDate(1, 1, LocalDateTime.now()));
    }
}