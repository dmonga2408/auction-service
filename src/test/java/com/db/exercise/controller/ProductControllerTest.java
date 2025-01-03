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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.opaqueToken;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
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
        Product product = new Product(1L, "testPro", 50.0);
        Mockito.when(productService.getById("1")).thenReturn(Optional.of(product));
        this.mockMvc.perform(get("/product/1")
                .with(opaqueToken()
                        .authorities(new SimpleGrantedAuthority("SCOPE_SELLER"))))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("testPro")));
    }

    @Test
    void shouldThrow401WhenNoValidAccessTokenInRequest() throws Exception {
        Product product = new Product(1L, "testPro", 50.0);
        Mockito.when(productService.getById("1")).thenReturn(Optional.of(product));
        this.mockMvc.perform(get("/product/1"))
                .andDo(print()).andExpect(status().isUnauthorized());
    }

    @Test
    void shouldReturn404ForANonExistingProduct() throws Exception {
        Product product = new Product(1L, "testPro", 50.0);
        Mockito.when(productService.getById("1")).thenReturn(Optional.of(product));
        this.mockMvc.perform(get("/product/3")
                .with(opaqueToken()
                        .authorities(new SimpleGrantedAuthority("SCOPE_SELLER"))))
                .andDo(print()).andExpect(status().isNotFound())
                .andExpect(content().string(containsString("The product with ID 3 was not found")));
    }

    @Test
    void shouldReturn401ForANonExistingProductWithoutToken() throws Exception {
        Product product = new Product(1L, "testPro", 50.0);
        Mockito.when(productService.getById("1")).thenReturn(Optional.of(product));
        this.mockMvc.perform(get("/product/3"))
                .andDo(print()).andExpect(status().isUnauthorized());
    }

    @Test
    void shouldCreateAProductAndReturnTheCreatedProduct() throws Exception {
        Product product = new Product(1L, "testPro", 50.0);
        Mockito.when(productService.addNewProduct(product)).thenReturn(product);
        this.mockMvc.perform(post("/product/create")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product))
                        .with(opaqueToken()
                                .authorities(new SimpleGrantedAuthority("SCOPE_SELLER"))))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(content().string(containsString("testPro")));
    }

    @Test
    void shouldReturnForbiddenForRequestWihoutValidToken() throws Exception {
        Product product = new Product(1L, "testPro", 50.0);
        Mockito.when(productService.addNewProduct(product)).thenReturn(product);
        this.mockMvc.perform(post("/product/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andDo(print()).andExpect(status().isForbidden());
    }

}