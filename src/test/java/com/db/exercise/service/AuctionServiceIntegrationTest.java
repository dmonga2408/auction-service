package com.db.exercise.service;

import com.db.exercise.entity.Auction;
import com.db.exercise.entity.Bid;
import com.db.exercise.entity.Product;
import com.db.exercise.repository.AuctionRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AuctionServiceIntegrationTest {

    @Autowired
    AuctionServiceImpl auctionService;
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    AuctionRepository auctionRepository;

    @Test
    void shouldAddNewAuctionsAndReturnAllActiveAuctionsWhenRequested() throws Exception {
        Product product1 = Instancio.of(Product.class).ignore(field(Product::getId)).create();
//        productService.addNewProduct(product1);

        Product product2 = Instancio.of(Product.class).ignore(field(Product::getId)).create();
//        productService.addNewProduct(product2);

        Auction auction1 = Instancio.create(Auction.class);
        auction1.setProduct(product1);
        product1.setAuction(auction1);
        auction1.setEndTime(null);
        auctionService.addNewAuction(auction1);

        Auction auction2 = Instancio.create(Auction.class);
        auction2.setProduct(product2);
        product2.setAuction(auction2);
        auction2.setEndTime(null);
        auctionService.addNewAuction(auction2);

        int totalActiveAuctions = auctionService.getAllActive().size();
        assertEquals(2, totalActiveAuctions);

    }

    @Test
    void shouldCreateAuctionUpdateBidsAndFetchWinningBid() throws Exception{

        Product product1 = Instancio.of(Product.class).ignore(field(Product::getId)).create();
        Auction auction1 = Instancio.create(Auction.class);
        Bid bid1 = Instancio.of(Bid.class).ignore(field(Bid::getId)).create();
        bid1.setAmount(10.0);
        auction1.addBid(bid1);
        auction1.setProduct(product1);
        auction1.setEndTime(null);

        product1.setAuction(auction1);
        bid1.setAuction(auction1);

        Auction savedAuction1 = auctionService.addNewAuction(auction1);

        Bid bid2 = Instancio.of(Bid.class).ignore(field(Bid::getId)).create();
        bid2.setAmount(15.0);
        savedAuction1.addBid(bid2);

        bid2.setAuction(savedAuction1);

        Auction savedAuction2 = auctionService.updateAuction(String.valueOf(savedAuction1.getId()), savedAuction1);

        Bid bid3 = Instancio.of(Bid.class).ignore(field(Bid::getId)).create();
        bid3.setAmount(18.0);
        savedAuction2.addBid(bid3);

        bid3.setAuction(savedAuction2);
        Auction savedAuction3 = auctionService.updateAuction(String.valueOf(savedAuction2.getId()), savedAuction2);

        Bid bid = auctionService.winningBidForAuction(String.valueOf(savedAuction3.getId()));
        assertEquals(18.0, bid.getAmount());
    }

}