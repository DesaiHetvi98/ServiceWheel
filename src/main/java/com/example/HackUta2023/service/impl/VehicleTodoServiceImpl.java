package com.example.HackUta2023.service.impl;

import java.util.List;

import com.example.HackUta2023.entity.VehicleTodo;
import com.example.HackUta2023.exception.NotFoundException;
import com.example.HackUta2023.repository.VehicleTodoRepository;
import com.example.HackUta2023.service.VehicleTodoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VehicleTodoServiceImpl implements VehicleTodoService {

	private final VehicleTodoRepository vehicleTodoRepository;

	public VehicleTodoServiceImpl(VehicleTodoRepository vehicleTodoRepository) {
		this.vehicleTodoRepository = vehicleTodoRepository;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<VehicleTodo> findAllAuthors() {
		return vehicleTodoRepository.findAll();
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public VehicleTodo findAuthorById(Long id) {
		return vehicleTodoRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Author not found with ID %d", id)));
	}

	@Override
	public void createAuthor(VehicleTodo vehicleTodo) {
		vehicleTodoRepository.save(vehicleTodo);
	}

	@Override
	public void updateAuthor(VehicleTodo vehicleTodo) {
		vehicleTodoRepository.save(vehicleTodo);
	}

	@Override
	public void deleteAuthor(Long id) {
		final VehicleTodo vehicleTodo = vehicleTodoRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Author not found with ID %d", id)));

		vehicleTodoRepository.deleteById(vehicleTodo.getId());
	}

}
