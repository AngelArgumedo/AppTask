package com.Api.AppTask.service;

import com.Api.AppTask.entity.TaskEntity;
import com.Api.AppTask.entity.UserEntity;
import com.Api.AppTask.repository.TaskRepository;
import com.Api.AppTask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public TaskEntity createTask(TaskEntity task) {
        return taskRepository.save(task);
    }

    public List<TaskEntity> getAllTasks() {
        return taskRepository.findAll();
    }

    public TaskEntity updateTask(Long id, TaskEntity taskDetails) {
        TaskEntity task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setName(taskDetails.getName());
        task.setDescription(taskDetails.getDescription());
        task.setCompleted(taskDetails.isCompleted());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public TaskEntity assignUsersToTask(Long taskId, List<Long> userIds) {
        TaskEntity task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        List<UserEntity> users = userRepository.findAllById(userIds);
        if (users.isEmpty()) {
            throw new RuntimeException("No valid users found for the given IDs");
        }
        task.setUsers(users);
        return taskRepository.save(task);
    }

    public List<TaskEntity> getTasksWithUsers() {
        return taskRepository.findAll();
    }
    public List<TaskEntity> searchTasksByName(String name) {
        return taskRepository.searchByName(name);
    }
}
