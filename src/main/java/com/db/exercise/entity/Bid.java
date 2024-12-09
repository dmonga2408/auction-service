package com.db.exercise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "BID")
public class Bid {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BID_SEQ_GENERATOR")
	@SequenceGenerator(name = "BID_SEQ_GENERATOR", sequenceName = "BID_SEQ")
	@Column(name = "BID_ID", nullable = false)
	private Long id;

	@Column(name = "USER_ID", nullable = false)
	private Long userId;

	@Column(name = "AMOUNT", nullable = false)
	private Double amount;

}
