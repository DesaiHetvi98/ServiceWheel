package com.example.HackUta2023.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.HackUta2023.entity.TaskCategory;

public interface TaskCategoryRepository extends JpaRepository<TaskCategory, Long> {

}
