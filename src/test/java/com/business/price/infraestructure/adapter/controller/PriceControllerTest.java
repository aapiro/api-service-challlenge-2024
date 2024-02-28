package com.business.price.infraestructure.adapter.controller;


import com.business.price.common.exception.GlobalExceptionHandler;
import com.business.price.domain.port.service.PriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(MockitoExtension.class)
public class PriceControllerTest {

    @InjectMocks
    private PriceController priceController;

    @Mock
    private PriceService priceService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(priceController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    public void testHandleParameterTypeMismatchException() throws Exception {
        mockMvc.perform(get("/api/v1/brand-id/invalid/product-id/123")
                        .param("applicationDate", "2022-01-01 12:00:00"))
                .andExpect(status().isBadRequest()) // Verifica que se reciba un 400 Bad Request
                .andExpect(content().string("Incorrect Parameter: brandID")); // Verifica el mensaje de error
    }

}
