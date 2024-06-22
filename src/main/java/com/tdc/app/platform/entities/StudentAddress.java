package com.tdc.app.platform.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Table
public class StudentAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "ADD_ID", nullable = false, unique = true, length = 50)
	private Integer addId;

	@Column(nullable = false)

	private String street1;

	private String street2;

	private String city;

	@Column(nullable = false)

	private String state;

	@Column(nullable = false)

	private String pincode;

	@Column(nullable = false)
	private String country;

}
