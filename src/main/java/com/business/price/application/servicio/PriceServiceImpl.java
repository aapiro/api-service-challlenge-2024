package com.business.price.application.servicio;

import com.business.price.application.dto.PriceDTO;
import com.business.price.application.mapper.PriceMapper;
import com.business.price.common.exception.DatabaseConnectionException;
import com.business.price.common.exception.NoDataFoundException;
import com.business.price.domain.port.repository.PriceRepository;
import com.business.price.domain.port.service.PriceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    @Override
    public PriceDTO findPriceFromDateAndProductAndBrand(int brandId, int productId,
                                                        LocalDateTime applicationDate) throws DatabaseConnectionException {
        return priceRepository.findByProductAndBrandDate(brandId, productId, applicationDate)
                .stream()
                .map(PriceMapper::toDto)
                .findFirst()
                .orElseThrow(NoDataFoundException::new);

    }
}
