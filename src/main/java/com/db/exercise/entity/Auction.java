package com.db.exercise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "AUCTION")
public class Auction {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUCTION_SEQ_GENERATOR")
	@SequenceGenerator(name = "AUCTION_SEQ_GENERATOR", sequenceName = "AUCTION_SEQ")
	@Column(name = "AUCTION_ID", nullable = false)
	private Long id;

	@OneToOne(mappedBy = "auction", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@PrimaryKeyJoinColumn
	private Product product;

	@Column(name = "START_TIME", nullable = false)
	private Date startTime;

	@Column(name = "END_TIME")
	private Date endTime;

	@OneToMany(mappedBy="auction", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Bid> bids;

	public List<Bid> addBid(Bid bid){
		bids.add(bid);
		return bids;
	}
	
}
