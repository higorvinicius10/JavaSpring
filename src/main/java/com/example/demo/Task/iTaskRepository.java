package com.example.demo.Task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface iTaskRepository extends JpaRepository<TaskModel, UUID> {
}
