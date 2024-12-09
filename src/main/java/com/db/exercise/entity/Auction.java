package com.db.exercise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;
import java.util.Set;

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

	@OneToOne
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;

	@Column(name = "START_TIME", nullable = false)
	private Date startTime;

	@Column(name = "END_TIME")
	private Date endTime;

	@Column(name = "STARTING_AMOUNT", nullable = false)
	private Double startingAmount;

	@OneToMany
	@JoinColumn(name = "BID_ID")
	private List<Bid> bids;
	
}
