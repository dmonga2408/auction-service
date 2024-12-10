package com.db.exercise.controller;

import com.db.exercise.entity.Auction;
import com.db.exercise.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auction")
public class AuctionController {
	AuctionService auctionService;
	
	@RequestMapping(value="/", method = RequestMethod.POST)
	public ResponseEntity<?> addNewAuction(@RequestBody Auction auction) {
		try {
			return new ResponseEntity<>(auctionService.addNewAuction(auction), HttpStatus.CREATED);
		}
		catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ResponseEntity<?> getAllAuctions() {
		try {
			return new ResponseEntity<>(auctionService.getAllActive(), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@RequestMapping(value="/{auctionId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateAuction(@PathVariable("auctionId") String auctionId, @RequestBody Auction auction) {
		try {
			return new ResponseEntity<>(auctionService.updateAuction(auctionId, auction), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
