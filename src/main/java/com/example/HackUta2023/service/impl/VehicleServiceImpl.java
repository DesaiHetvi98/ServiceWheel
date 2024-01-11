package com.example.HackUta2023.service.impl;

import java.util.List;

import com.example.HackUta2023.entity.Vehicle;
import com.example.HackUta2023.exception.NotFoundException;
import com.example.HackUta2023.repository.VehicleRepository;
import com.example.HackUta2023.service.VehicleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VehicleServiceImpl implements VehicleService {

	private final VehicleRepository vehicleRepository;

	public VehicleServiceImpl(VehicleRepository vehicleRepository) {
		this.vehicleRepository = vehicleRepository;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<Vehicle> findAllVehicles() {
		return vehicleRepository.findAll();
	}

	@Override
	public Vehicle findVehicleById(Long id) {
		return vehicleRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Vehicle not found  with ID %d", id)));
	}

	@Override
	public void createVehicle(Vehicle vehicle) {
		vehicleRepository.save(vehicle);
	}

	@Override
	public void updateVehicle(Vehicle vehicle) {
		vehicleRepository.save(vehicle);
	}

	@Override
	public void deleteVehicle(Long id) {
		final Vehicle vehicle = vehicleRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Vehicle not found  with ID %d", id)));

		vehicleRepository.deleteById(vehicle.getId());
	}

}
