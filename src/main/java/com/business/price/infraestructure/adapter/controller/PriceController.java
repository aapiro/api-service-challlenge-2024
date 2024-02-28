package com.business.price.infraestructure.adapter.controller;

import com.business.price.application.dto.PriceDTO;
import com.business.price.application.servicio.PriceApplicationService;
import com.business.price.common.exception.DatabaseConnectionException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/api/v1")
@AllArgsConstructor
public class PriceController {

    private final PriceApplicationService priceApplicationService;
    private static final String DEFAULT_MEDIA_TYPE = MediaType.APPLICATION_JSON_VALUE;

    @Operation(summary = "Get price")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success")})
    @GetMapping(value = "/brand-id/{brandID}/product-id/{productId}", produces = DEFAULT_MEDIA_TYPE)
    ResponseEntity<PriceDTO> getPrice(@PathVariable("brandID") final int brandId,
                                      @PathVariable("productId") final int productId,
                                      @RequestParam("applicationDate")
                                           @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                           final LocalDateTime applicationDate) throws DatabaseConnectionException {
        return ResponseEntity.ok(priceApplicationService.findByProductAndBrandDate(brandId, productId, applicationDate));
    }
}
