package com.example.HackUta2023.service;

import java.util.List;

import com.example.HackUta2023.entity.Task;

public interface TaskService {

	public List<Task> findAllTasks();
	
	public List<Task> searchTasks(String keyword);

	public Task findTaskById(Long id);

	public void createTask(Task task);

	public void updateTask(Task task);

	public void deleteTask(Long id);

}
