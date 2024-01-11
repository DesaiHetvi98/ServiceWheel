package com.example.HackUta2023;

import com.example.HackUta2023.entity.TaskCategory;
import com.example.HackUta2023.entity.Vehicle;
import com.example.HackUta2023.entity.VehicleTodo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.example.HackUta2023.entity.Task;
import com.example.HackUta2023.service.TaskService;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class HackUta2023 {

	public static void main(String[] args) {
		SpringApplication.run(HackUta2023.class, args);
	}

	@Bean
	public CommandLineRunner initialCreate(TaskService taskService) {
		return (args) -> {

			Task task = new Task("Test isbn", "Test name", "Test serial name", "Test description");
			VehicleTodo vehicleTodo = new VehicleTodo("Test author name", "Test description","Last Date");
			TaskCategory taskCategory = new TaskCategory("Test category name");
			Vehicle vehicle = new Vehicle("Test Vehicle name","total issue");

			task.addAuthors(vehicleTodo);
			task.addCategories(taskCategory);
			task.addPublishers(vehicle);

			taskService.createTask(task);

			Task task1 = new Task("Test isbn1", "Test name1", "Test serial name1", "Test description1");
			VehicleTodo vehicleTodo1 = new VehicleTodo("Test author name1", "Test description1","Last Date");
			TaskCategory taskCategory1 = new TaskCategory("Test category name1");
			Vehicle vehicle1 = new Vehicle("Test Vehicle name","total issue");

			task1.addAuthors(vehicleTodo1);
			task1.addCategories(taskCategory1);
			task1.addPublishers(vehicle1);

			taskService.createTask(task1);

			Task task2 = new Task("Test isbn2", "Test name2", "Test serial name2", "Test description2");
			VehicleTodo vehicleTodo2 = new VehicleTodo("Test author name2", "Test description2","Last Date");
			TaskCategory taskCategory2 = new TaskCategory("Test category name2");
			Vehicle vehicle2 = new Vehicle("Test Vehicle name","total issue");

			task2.addAuthors(vehicleTodo2);
			task2.addCategories(taskCategory2);
			task2.addPublishers(vehicle2);

			taskService.createTask(task2);

		};
	}
}
