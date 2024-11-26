package com.Api.AppTask.controller;

import com.Api.AppTask.entity.TaskEntity;
import com.Api.AppTask.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public TaskEntity createTask(@RequestBody TaskEntity task) {
        return taskService.createTask(task);
    }

    @GetMapping
    public List<TaskEntity> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PutMapping("/{id}")
    public TaskEntity updateTask(@PathVariable Long id, @RequestBody TaskEntity taskDetails) {
        return taskService.updateTask(id, taskDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @PutMapping("/{taskId}/assignUsers")
    public TaskEntity assignUsersToTask(@PathVariable Long taskId, @RequestBody List<Long> userIds) {
        return taskService.assignUsersToTask(taskId, userIds);
    }

    @GetMapping("/withUsers")
    public List<TaskEntity> getTasksWithUsers() {
        return taskService.getTasksWithUsers();
    }

    @GetMapping("/search")
    public ResponseEntity<List<TaskEntity>> searchTasks(@RequestParam("name") String name) {
        List<TaskEntity> tasks = taskService.searchTasksByName(name);
        return ResponseEntity.ok(tasks);
    }
}
