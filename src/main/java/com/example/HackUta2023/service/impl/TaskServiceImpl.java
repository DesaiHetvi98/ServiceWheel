package com.example.HackUta2023.service.impl;

import java.util.List;

import com.example.HackUta2023.entity.Task;
import com.example.HackUta2023.repository.TaskRepository;
import com.example.HackUta2023.service.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.HackUta2023.exception.NotFoundException;

@Service
public class TaskServiceImpl implements TaskService {

	private final TaskRepository taskRepository;

	public TaskServiceImpl(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<Task> findAllTasks() {
		return taskRepository.findAll();
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<Task> searchTasks(String keyword) {
		if (keyword != null) {
			return taskRepository.search(keyword);
		}
		return taskRepository.findAll();
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public Task findTaskById(Long id) {
		return taskRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Book not found with ID %d", id)));
	}

	@Override
	public void createTask(Task task) {
		taskRepository.save(task);
	}

	@Override
	public void updateTask(Task task) {
		taskRepository.save(task);
	}

	@Override
	public void deleteTask(Long id) {
		final Task task = taskRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Book not found with ID %d", id)));

		taskRepository.deleteById(task.getId());
	}

}
