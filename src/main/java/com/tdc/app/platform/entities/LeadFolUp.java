package com.tdc.app.platform.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class LeadFolUp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
	private Integer leadFollowUpId;

	@Column(nullable = false)
	private LocalDate folUpDate;

	@Column(nullable = false)
	private String folUpTime;

	private String Comments;

	@ManyToOne
	@JoinColumn(name = "lead_id", nullable = false)
	@JsonIgnore
	private LeadGenerate leadGenerate;
}
