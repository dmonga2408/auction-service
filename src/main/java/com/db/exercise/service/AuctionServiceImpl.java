package com.db.exercise.service;

import com.db.exercise.entity.Auction;
import com.db.exercise.entity.Bid;
import com.db.exercise.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class AuctionServiceImpl implements AuctionService {

	@Autowired
	AuctionRepository auctionRepository;
	@Override
	public Auction addNewAuction(Auction auction) throws Exception {
		return auctionRepository.save(auction);
	}
	@Override
	public Auction updateAuction(String id, Auction auction) throws Exception {
		if(auctionRepository.existsById(id)) {
			auction.setId(Long.valueOf(id));
			return auctionRepository.save(auction);
		}
		throw new RuntimeException(String.format("No such Auction exists with Auction id %s ", id));
	}

	@Override
	public Bid winningBidForAuction(String auctionId) {
		Optional<Auction> auctionById = auctionRepository.findById(auctionId);
		if (auctionById.isPresent()){
			return auctionById.get().getBids().stream().max(Comparator.comparing(Bid::getAmount)).get();
		}
		throw new RuntimeException(String.format("No such Auction exists with Auction id %s ", auctionId));
	}

	@Override
	public List<Auction> getAllActive() throws Exception {
		return auctionRepository.findAllActiveAuctions();
	}
}