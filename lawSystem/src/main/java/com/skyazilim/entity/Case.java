package com.skyazilim.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cases")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Case {
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "date")
	private LocalDate date;
	
	@Column(name = "status")
	private Boolean status;
	
	@Column(name = "court")
	private String court;
	
	@Column(name = "description")
	private String description;
	
	@ManyToOne
	private Client client;
}
