package com.business.price.application.servicio;

import com.business.price.application.dto.PriceDTO;
import com.business.price.application.mapper.PriceMapper;
import com.business.price.common.exception.DatabaseConnectionException;
import com.business.price.common.exception.NoDataFoundException;
import com.business.price.domain.port.service.PriceService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PriceApplicationService {

    private final PriceService priceService;

    public PriceApplicationService(PriceService priceService) {
        this.priceService = priceService;
    }

    public PriceDTO findByProductAndBrandDate(Integer brandId, Integer productId, LocalDateTime applicationDate) throws DatabaseConnectionException {
        return priceService.findPriceFromDateAndProductAndBrand(brandId, productId, applicationDate)
                .stream()
                .map(PriceMapper::toDto)
                .findFirst()
                .orElseThrow(NoDataFoundException::new);
    }
}
