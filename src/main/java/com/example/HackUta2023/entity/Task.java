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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "priority", length = 50, nullable = false, unique = true)
	private String Priority;

	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@Column(name = "date", length = 50, nullable = false)
	private String date;

	@Column(name = "description", length = 250, nullable = false)
	private String description;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	@JoinTable(name = "books_authors", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {
			@JoinColumn(name = "author_id") })
	private Set<VehicleTodo> vehicleTodos = new HashSet<VehicleTodo>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "books_categories", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {
			@JoinColumn(name = "category_id") })
	private Set<TaskCategory> categories = new HashSet<TaskCategory>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "books_publishers", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {
			@JoinColumn(name = "publisher_id") })
	private Set<Vehicle> vehicles = new HashSet<Vehicle>();

	public Task(String priority, String name, String date, String description) {
		this.Priority = priority;
		this.name = name;
		this.date = date;
		this.description = description;
	}

	public void addAuthors(VehicleTodo vehicleTodo) {
		this.vehicleTodos.add(vehicleTodo);
		vehicleTodo.getTasks().add(this);
	}

	public void removeAuthors(VehicleTodo vehicleTodo) {
		this.vehicleTodos.remove(vehicleTodo);
		vehicleTodo.getTasks().remove(this);
	}

	public void addCategories(TaskCategory taskCategory) {
		this.categories.add(taskCategory);
		taskCategory.getTasks().add(this);
	}

	public void removeCategories(TaskCategory taskCategory) {
		this.categories.remove(taskCategory);
		taskCategory.getTasks().remove(this);
	}

	public void addPublishers(Vehicle vehicle) {
		this.vehicles.add(vehicle);
		vehicle.getTasks().add(this);
	}

	public void removePublishers(Vehicle vehicle) {
		this.vehicles.remove(vehicle);
		vehicle.getTasks().remove(this);
	}
}
