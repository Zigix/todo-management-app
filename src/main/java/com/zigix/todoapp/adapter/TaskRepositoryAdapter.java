package com.zigix.todoapp.adapter;

import com.zigix.todoapp.model.Task;
import com.zigix.todoapp.repository.TaskRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepositoryAdapter extends TaskRepository, JpaRepository<Task, Long> {
}
