package com.example.HackUta2023.controller;

import java.util.List;

import com.example.HackUta2023.entity.Vehicle;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.HackUta2023.service.VehicleService;

@Controller
public class VehicleController {

	private final VehicleService vehicleService;

	public VehicleController(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	@RequestMapping("/publishers")
	public String findAllPublishers(Model model) {
		final List<Vehicle> vehicles = vehicleService.findAllVehicles();

		model.addAttribute("publishers", vehicles);
		return "list-publishers";
	}

	@RequestMapping("/publisher/{id}")
	public String findPublisherById(@PathVariable("id") Long id, Model model) {
		final Vehicle vehicle = vehicleService.findVehicleById(id);

		model.addAttribute("publisher", vehicle);
		return "list-publisher";
	}

	@GetMapping("/addPublisher")
	public String showCreateForm(Vehicle vehicle) {
		return "add-publisher";
	}

	@RequestMapping("/add-publisher")
	public String createPublisher(Vehicle vehicle, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-publisher";
		}

		vehicleService.createVehicle(vehicle);
		model.addAttribute("publisher", vehicleService.findAllVehicles());
		return "redirect:/publishers";
	}

	@GetMapping("/updatePublisher/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		final Vehicle vehicle = vehicleService.findVehicleById(id);

		model.addAttribute("publisher", vehicle);
		return "update-publisher";
	}

	@RequestMapping("/update-publisher/{id}")
	public String updatePublisher(@PathVariable("id") Long id, Vehicle vehicle, BindingResult result, Model model) {
		if (result.hasErrors()) {
			vehicle.setId(id);
			return "update-publishers";
		}

		vehicleService.updateVehicle(vehicle);
		model.addAttribute("publisher", vehicleService.findAllVehicles());
		return "redirect:/publishers";
	}

	@RequestMapping("/remove-publisher/{id}")
	public String deletePublisher(@PathVariable("id") Long id, Model model) {
		vehicleService.deleteVehicle(id);

		model.addAttribute("publisher", vehicleService.findAllVehicles());
		return "redirect:/publishers";
	}

}
