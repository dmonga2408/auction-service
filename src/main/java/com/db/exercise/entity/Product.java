package com.db.exercise.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "PRODUCT")
public class Product implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_SEQ_GENERATOR")
	@SequenceGenerator(name = "PRODUCT_SEQ_GENERATOR", sequenceName = "PRODUCT_SEQ")
	@Column(name = "PRODUCT_ID", nullable = false)
	private Long id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "MINIMUM_BID_AMOUNT", nullable = false)
	private Double minimumBidAmount;

}
