package com.business.price.adapters.api;

import com.business.price.domain.ports.PriceService;
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
public class PriceEndpoint {

    private final PriceService priceService;
    private static final String DEFAULT_MEDIA_TYPE = MediaType.APPLICATION_JSON_VALUE;

    @Operation(summary = "Get price")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success")})
    @GetMapping(value = "/brand-id/{brandID}/product-id/{productId}", produces = DEFAULT_MEDIA_TYPE)
    ResponseEntity<PriceResponse> getPrice(@PathVariable("brandID") final Integer brandId,
                                           @PathVariable("productId") final Integer productId,
                                           @RequestParam("applicationDate")
                                           @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                           final LocalDateTime applicationDate) {
        return ResponseEntity.ok(priceService.findPriceFromDateAndProductAndBrand(brandId, productId, applicationDate));
    }
}
