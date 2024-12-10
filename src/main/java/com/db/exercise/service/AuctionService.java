package com.db.exercise.service;

import com.db.exercise.entity.Auction;
import com.db.exercise.entity.Bid;

import java.util.List;

public interface AuctionService {

	List<Auction> getAllActive() throws Exception;
	
	Auction addNewAuction(Auction auction) throws Exception;
	
	Auction updateAuction(String id, Auction auction) throws Exception;

	Bid winningBidForAuction(String auctionId);

}
