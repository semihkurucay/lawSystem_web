package com.skyazilim.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reading")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reading {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "current_pagr")
	private Integer currentPage = 0;
	
	@Column(name = "percentage")
	private Integer percentage = 0;
	
	@Column(name = "status")
	private String status = "Okunuyor";
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Book book;
}
