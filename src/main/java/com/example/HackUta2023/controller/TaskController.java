package com.example.HackUta2023.controller;

import java.util.List;

import com.example.HackUta2023.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.HackUta2023.service.VehicleTodoService;
import com.example.HackUta2023.service.TaskService;
import com.example.HackUta2023.service.TaskCategoryService;
import com.example.HackUta2023.service.VehicleService;

@Controller
public class TaskController {

	private final TaskService taskService;
	private final VehicleTodoService vehicleTodoService;
	private final TaskCategoryService taskCategoryService;
	private final VehicleService vehicleService;

	@Autowired
	public TaskController(TaskService taskService, VehicleTodoService vehicleTodoService, TaskCategoryService taskCategoryService,
						  VehicleService vehicleService) {
		this.taskService = taskService;
		this.vehicleTodoService = vehicleTodoService;
		this.taskCategoryService = taskCategoryService;
		this.vehicleService = vehicleService;
	}

	@RequestMapping("/books")
	public String findAllBooks(Model model) {
		final List<Task> tasks = taskService.findAllTasks();

		model.addAttribute("books", tasks);
		return "list-books";
	}

	@RequestMapping("/searchBook")
	public String searchBook(@Param("keyword") String keyword, Model model) {
		final List<Task> tasks = taskService.searchTasks(keyword);

		model.addAttribute("books", tasks);
		model.addAttribute("keyword", keyword);
		return "list-books";
	}

	@RequestMapping("/book/{id}")
	public String findBookById(@PathVariable("id") Long id, Model model) {
		final Task task = taskService.findTaskById(id);

		model.addAttribute("book", task);
		return "list-book";
	}

	@GetMapping("/add")
	public String showCreateForm(Task task, Model model) {
		model.addAttribute("categories", taskCategoryService.findAllCategories());
		model.addAttribute("authors", vehicleTodoService.findAllAuthors());
		model.addAttribute("publishers", vehicleService.findAllVehicles());
		return "add-book";
	}

	@RequestMapping("/add-book")
	public String createTask(Task task, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-book";
		}

		taskService.createTask(task);
		model.addAttribute("book", taskService.findAllTasks());
		return "redirect:/books";
	}

	@GetMapping("/update/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		final Task task = taskService.findTaskById(id);

		model.addAttribute("book", task);
		return "update-book";
	}

	@RequestMapping("/update-book/{id}")
	public String updateTask(@PathVariable("id") Long id, Task task, BindingResult result, Model model) {
		if (result.hasErrors()) {
			task.setId(id);
			return "update-book";
		}

		taskService.updateTask(task);
		model.addAttribute("book", taskService.findAllTasks());
		return "redirect:/books";
	}

	@RequestMapping("/remove-book/{id}")
	public String deleteTask(@PathVariable("id") Long id, Model model) {
		taskService.deleteTask(id);

		model.addAttribute("book", taskService.findAllTasks());
		return "redirect:/books";
	}

}
