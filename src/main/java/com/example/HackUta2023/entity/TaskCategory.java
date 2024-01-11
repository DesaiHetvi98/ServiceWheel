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
@Table(name = "categories")
public class TaskCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "categoryName", length = 50, nullable = false, unique = true)
	private String categoryName;

	@Column(name = "totalNumber", length = 50, nullable = false, unique = true)
	private String totalNumber;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "categories")
	private Set<Task> tasks = new HashSet<Task>();

	public TaskCategory(String name) {
		this.categoryName = name;
	}


}
