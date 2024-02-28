package com.business.price.infraestructure.adapter.controller;


import com.business.price.common.exception.GlobalExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerTest {

    @Autowired
    private PriceController priceController;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(priceController)
                .setControllerAdvice(new GlobalExceptionHandler()).build();
    }

    @Test
    public void testHandleParameterTypeNoPricesFoundException() throws Exception {
        mockMvc.perform(get("/api/v1/brand-id/1/product-id/1")
                .queryParam("applicationDate", "2022-01-01 12:00:00"))
                .andExpect(status()
                        .isNotFound())
                .andExpect(content()
                        .string("{\"message\":\"No Prices found\"}"));
    }

    @Test
    public void testHandleParameterTypeMismatchException() throws Exception {
        mockMvc.perform(get("/api/v1/brand-id/invalid/product-id/123")
                .param("applicationDate", "2022-01-01 12:00:00"))
                .andExpect(status()
                        .isBadRequest())
                .andExpect(content()
                        .string("Incorrect Parameter: brandID"));
    }

}
