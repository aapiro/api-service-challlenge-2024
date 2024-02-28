package com.business.price.infraestructure.adapter.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    @Test
    void test1() throws Exception {
        this.mockMvc.perform(
                        get("/api/v1/brand-id/1/product-id/35455")
                                .queryParam("applicationDate", "2020-06-14 10:00:00")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.startPriceDate", is("2020-06-14T00:00:00")))
                .andExpect(jsonPath("$.endPriceDate", is("2020-12-31T23:00:59.059")))
                .andExpect(jsonPath("$.productId", is(35455)))
                .andExpect(jsonPath("$.brandId", is(1)))
                .andExpect(jsonPath("$.price", is(35.5)))
                .andExpect(jsonPath("$.priceList", is(1)))
                .andDo(print());
    }
    @DisplayName("Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    @Test
    void test2() throws Exception {
            this.mockMvc.perform(
                            get("/api/v1/brand-id/1/product-id/35455")
                                    .queryParam("applicationDate", "2020-06-14 16:00:00")
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.startPriceDate", is("2020-06-14T15:00:00")))
                    .andExpect(jsonPath("$.endPriceDate", is("2020-06-14T18:00:30")))
                    .andExpect(jsonPath("$.productId", is(35455)))
                    .andExpect(jsonPath("$.brandId", is(1)))
                    .andExpect(jsonPath("$.price", is(25.45)))
                    .andExpect(jsonPath("$.priceList", is(2)))
                    .andDo(print());
    }
    @DisplayName("Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    @Test
    void test3() throws Exception {
            this.mockMvc.perform(
                            get("/api/v1/brand-id/1/product-id/35455")
                                    .queryParam("applicationDate", "2020-06-14 21:00:00")
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.startPriceDate", is("2020-06-14T00:00:00")))
                    .andExpect(jsonPath("$.endPriceDate", is("2020-12-31T23:00:59.059")))
                    .andExpect(jsonPath("$.productId", is(35455)))
                    .andExpect(jsonPath("$.brandId", is(1)))
                    .andExpect(jsonPath("$.price", is(35.5)))
                    .andExpect(jsonPath("$.priceList", is(1)))
                    .andDo(print());
    }
    @DisplayName("Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)")
    @Test
    void test4() throws Exception {
            this.mockMvc.perform(
                            get("/api/v1/brand-id/1/product-id/35455")
                                    .queryParam("applicationDate", "2020-06-15 10:00:00")
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.startPriceDate", is("2020-06-15T00:00:00")))
                    .andExpect(jsonPath("$.endPriceDate", is("2020-06-15T11:00:00")))
                    .andExpect(jsonPath("$.productId", is(35455)))
                    .andExpect(jsonPath("$.brandId", is(1)))
                    .andExpect(jsonPath("$.price", is(30.5)))
                    .andExpect(jsonPath("$.priceList", is(3)))
                    .andDo(print());
    }
    @DisplayName("Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)")
    @Test
    void test5() throws Exception {
            this.mockMvc.perform(
                            get("/api/v1/brand-id/1/product-id/35455")
                                    .queryParam("applicationDate", "2020-06-16 21:00:00")
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.startPriceDate", is("2020-06-15T16:00:00")))
                    .andExpect(jsonPath("$.endPriceDate", is("2020-12-31T23:00:59.059")))
                    .andExpect(jsonPath("$.productId", is(35455)))
                    .andExpect(jsonPath("$.brandId", is(1)))
                    .andExpect(jsonPath("$.price", is(38.95)))
                    .andExpect(jsonPath("$.priceList", is(4)))
                    .andDo(print());
    }
}
