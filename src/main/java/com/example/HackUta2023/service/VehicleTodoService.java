package com.example.HackUta2023.service;

import java.util.List;

import com.example.HackUta2023.entity.VehicleTodo;

public interface VehicleTodoService {

	public List<VehicleTodo> findAllAuthors();

	public VehicleTodo findAuthorById(Long id);

	public void createAuthor(VehicleTodo vehicleTodo);

	public void updateAuthor(VehicleTodo vehicleTodo);

	public void deleteAuthor(Long id);

}
