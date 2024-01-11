package com.example.HackUta2023.service;

import java.util.List;

import com.example.HackUta2023.entity.Vehicle;

public interface VehicleService {

	public List<Vehicle> findAllVehicles();

	public Vehicle findVehicleById(Long id);

	public void createVehicle(Vehicle vehicle);

	public void updateVehicle(Vehicle vehicle);

	public void deleteVehicle(Long id);

}
