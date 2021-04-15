package com.zigix.todoapp.service.management;

import com.zigix.todoapp.model.TaskGroup;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskGroupManagementService {

    List<TaskGroup> getAllTaskGroups(Pageable pageable);

    List<TaskGroup> getTaskGroupsByUserId(Long userId);

    TaskGroup getSingleTaskGroup(Long groupId);

    TaskGroup addTaskGroupToUser(Long userId, TaskGroup taskGroup);

    void deleteTaskGroupFromUser(Long groupId);

}
