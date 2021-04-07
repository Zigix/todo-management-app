package com.zigix.todoapp.adapter;

import com.zigix.todoapp.model.TaskGroup;
import com.zigix.todoapp.repository.TaskGroupRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskGroupRepositoryAdapter extends TaskGroupRepository, JpaRepository<TaskGroup, Long> {
}
