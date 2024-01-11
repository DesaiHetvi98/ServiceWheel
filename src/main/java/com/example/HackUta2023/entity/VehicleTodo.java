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
@Table(name = "authors")
public class VehicleTodo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "vehicleName", length = 100, nullable = false,unique = true)
	private String vehicleName;

	@Column(name = "issue", length = 250, nullable = false)
	private String issue;

	@Column(name = "lastDate", length = 250, nullable = false)
	private String lastDate;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE , CascadeType.REMOVE}, mappedBy = "authors")
	private Set<Task> tasks = new HashSet<Task>();

	public VehicleTodo(String name, String description,String lastdate) {
		this.vehicleName = name;
		this.issue = description;
		this.lastDate = lastdate;
	}

}
