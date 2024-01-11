package com.example.HackUta2023.controller;

import java.util.List;

import com.example.HackUta2023.entity.VehicleTodo;
import com.example.HackUta2023.service.VehicleTodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VehicleTodoController {

	private final VehicleTodoService vehicleTodoService;

	public VehicleTodoController(VehicleTodoService vehicleTodoService) {
		this.vehicleTodoService = vehicleTodoService;
	}

	@RequestMapping("/authors")
	public String findAllAuthors(Model model) {
		final List<VehicleTodo> vehicleTodos = vehicleTodoService.findAllAuthors();

		model.addAttribute("authors", vehicleTodos);
		return "list-authors";
	}

	@RequestMapping("/author/{id}")
	public String findAuthorById(@PathVariable("id") Long id, Model model) {
		final VehicleTodo vehicleTodo = vehicleTodoService.findAuthorById(id);

		model.addAttribute("author", vehicleTodo);
		return "list-author";
	}

	@GetMapping("/addAuthor")
	public String showCreateForm(VehicleTodo vehicleTodo) {
		return "add-author";
	}

	@RequestMapping("/add-author")
	public String createAuthor(VehicleTodo vehicleTodo, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-author";
		}

		vehicleTodoService.createAuthor(vehicleTodo);
		model.addAttribute("author", vehicleTodoService.findAllAuthors());
		return "redirect:/authors";
	}

	@GetMapping("/updateAuthor/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		final VehicleTodo vehicleTodo = vehicleTodoService.findAuthorById(id);

		model.addAttribute("author", vehicleTodo);
		return "update-author";
	}

	@RequestMapping("/update-author/{id}")
	public String updateAuthor(@PathVariable("id") Long id, VehicleTodo vehicleTodo, BindingResult result, Model model) {
		if (result.hasErrors()) {
			vehicleTodo.setId(id);
			return "update-author";
		}

		vehicleTodoService.updateAuthor(vehicleTodo);
		model.addAttribute("author", vehicleTodoService.findAllAuthors());
		return "redirect:/authors";
	}

	@RequestMapping("/remove-author/{id}")
	public String deleteAuthor(@PathVariable("id") Long id, Model model) {
		vehicleTodoService.deleteAuthor(id);

		model.addAttribute("author", vehicleTodoService.findAllAuthors());
		return "redirect:/authors";
	}

}
