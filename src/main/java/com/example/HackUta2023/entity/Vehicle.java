package com.example.HackUta2023.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Vehicle")
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "vehiclename", length = 100, nullable = false, unique = true)
	private String vehiclename;

	@Column(name = "totalIssue", length = 100, nullable = false, unique = true)
	private String totalIssue;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "publishers")
	private Set<Task> tasks = new HashSet<Task>();

	public Vehicle(String name,String totalIssue) {
		this.vehiclename = name;
		this.totalIssue = totalIssue;
	}

}
