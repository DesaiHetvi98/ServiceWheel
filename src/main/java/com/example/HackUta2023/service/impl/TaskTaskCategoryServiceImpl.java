package com.example.HackUta2023.service.impl;

import java.util.List;

import com.example.HackUta2023.entity.TaskCategory;
import com.example.HackUta2023.repository.TaskCategoryRepository;
import com.example.HackUta2023.service.TaskCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.HackUta2023.exception.NotFoundException;

@Service
public class TaskTaskCategoryServiceImpl implements TaskCategoryService {

	private final TaskCategoryRepository taskCategoryRepository;

	public TaskTaskCategoryServiceImpl(TaskCategoryRepository taskCategoryRepository) {
		this.taskCategoryRepository = taskCategoryRepository;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<TaskCategory> findAllCategories() {
		return taskCategoryRepository.findAll();
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public TaskCategory findCategoryById(Long id) {
		return taskCategoryRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Category not found  with ID %d", id)));
	}

	@Override
	public void createCategory(TaskCategory taskCategory) {
		taskCategoryRepository.save(taskCategory);
	}

	@Override
	public void updateCategory(TaskCategory taskCategory) {
		taskCategoryRepository.save(taskCategory);
	}

	@Override
	public void deleteCategory(Long id) {
		final TaskCategory taskCategory = taskCategoryRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Category not found  with ID %d", id)));

		taskCategoryRepository.deleteById(taskCategory.getId());
	}

}
