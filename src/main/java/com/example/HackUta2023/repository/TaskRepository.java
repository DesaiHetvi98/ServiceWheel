package com.example.HackUta2023.repository;

import java.util.List;

import com.example.HackUta2023.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskRepository extends JpaRepository<Task, Long> {

	@Query("SELECT b FROM Book b WHERE b.name LIKE %?1%" + " OR b.isbn LIKE %?1%" + " OR b.serialName LIKE %?1%")
	public List<Task> search(String keyword);
}
