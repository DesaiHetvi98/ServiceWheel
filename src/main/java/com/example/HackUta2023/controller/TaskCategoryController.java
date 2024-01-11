package com.example.HackUta2023.controller;

import java.util.List;

import com.example.HackUta2023.entity.TaskCategory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.HackUta2023.service.TaskCategoryService;

@Controller
public class TaskCategoryController {

	private final TaskCategoryService taskCategoryService;

	public TaskCategoryController(TaskCategoryService taskCategoryService) {
		this.taskCategoryService = taskCategoryService;
	}

	@RequestMapping("/categories")
	public String findAllCategories(Model model) {
		final List<TaskCategory> categories = taskCategoryService.findAllCategories();

		model.addAttribute("categories", categories);
		return "list-categories";
	}

	@RequestMapping("/category/{id}")
	public String findCategoryById(@PathVariable("id") Long id, Model model) {
		final TaskCategory taskCategory = taskCategoryService.findCategoryById(id);

		model.addAttribute("category", taskCategory);
		return "list-category";
	}

	@GetMapping("/addCategory")
	public String showCreateForm(TaskCategory taskCategory) {
		return "add-category";
	}

	@RequestMapping("/add-category")
	public String createCategory(TaskCategory taskCategory, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-category";
		}

		taskCategoryService.createCategory(taskCategory);
		model.addAttribute("category", taskCategoryService.findAllCategories());
		return "redirect:/categories";
	}

	@GetMapping("/updateCategory/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		final TaskCategory taskCategory = taskCategoryService.findCategoryById(id);

		model.addAttribute("category", taskCategory);
		return "update-category";
	}

	@RequestMapping("/update-category/{id}")
	public String updateCategory(@PathVariable("id") Long id, TaskCategory taskCategory, BindingResult result, Model model) {
		if (result.hasErrors()) {
			taskCategory.setId(id);
			return "update-category";
		}

		taskCategoryService.updateCategory(taskCategory);
		model.addAttribute("category", taskCategoryService.findAllCategories());
		return "redirect:/categories";
	}

	@RequestMapping("/remove-category/{id}")
	public String deleteCategory(@PathVariable("id") Long id, Model model) {
		taskCategoryService.deleteCategory(id);

		model.addAttribute("category", taskCategoryService.findAllCategories());
		return "redirect:/categories";
	}

}
