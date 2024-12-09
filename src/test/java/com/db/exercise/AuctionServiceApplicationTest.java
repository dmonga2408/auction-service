package com.db.exercise;

import com.db.exercise.controller.ProductController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AuctionServiceApplicationTest {

    @Autowired
    private ProductController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

}