package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "test_tbl01")
public class Testtbl {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "testcol1")
	private String col1;
}
