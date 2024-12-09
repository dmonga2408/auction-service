package com.db.exercise.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "USER")
public class User {

	// TODO: This needs some thinking on how this information will be coming to the service
	// as this is stored in the user service and is not supposed to be stored in the auction service
	// opaque token will be introspected to fetch this information and then only user id can be stored with the
	// auction?

	@Column(name = "USER_ID", nullable = false)
	private String id;
}
