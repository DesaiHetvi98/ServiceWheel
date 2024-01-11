package com.example.HackUta2023.service;

import java.util.List;

import com.example.HackUta2023.entity.TaskCategory;

public interface TaskCategoryService {

	public List<TaskCategory> findAllCategories();

	public TaskCategory findCategoryById(Long id);

	public void createCategory(TaskCategory taskCategory);

	public void updateCategory(TaskCategory taskCategory);

	public void deleteCategory(Long id);

}
