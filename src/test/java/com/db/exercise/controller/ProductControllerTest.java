package com.db.exercise.controller;

import com.db.exercise.entity.Auction;
import com.db.exercise.entity.Product;
import com.db.exercise.service.ProductServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest
class ProductControllerTest {

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProductServiceImpl productService;

    @Test
    void shouldReturnAProductForValidProductId() throws Exception {
        Product product = new Product(1L, "testPro", 50.0, new Auction());
        Mockito.when(productService.getById("1")).thenReturn(Optional.of(product));
        this.mockMvc.perform(get("/product/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("testPro")));
    }


    @Test
    void shouldReturn404ForANonExistingProduct() throws Exception {
        Product product = new Product(1L, "testPro", 50.0, new Auction());
        Mockito.when(productService.getById("1")).thenReturn(Optional.of(product));
        this.mockMvc.perform(get("/product/3")).andDo(print()).andExpect(status().isNotFound())
                .andExpect(content().string(containsString("The product with ID 3 was not found")));
    }

    @Test
    void shouldCreateAProductAndReturnTheCreatedProduct() throws Exception {
        Product product = new Product(1L, "testPro", 50.0, new Auction());
        Mockito.when(productService.addNewProduct(product)).thenReturn(product);
        this.mockMvc.perform(post("/product/create")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(content().string(containsString("testPro")));
    }

}