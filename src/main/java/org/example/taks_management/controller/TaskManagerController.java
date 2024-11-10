package org.example.taks_management.controller;

import lombok.RequiredArgsConstructor;
import org.example.taks_management.mapper.TaskMapper;
import org.example.taks_management.repository.TaskByUserRepository;
import org.example.taks_management.repository.TaskRepository;
import org.example.taks_management.repository.UserRepository;
import org.example.taks_management.table.Task;
import org.example.taks_management.table.TaskByUser;
import org.example.taks_management.table.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TaskManagerController {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final TaskByUserRepository taskByUserRepository;
    private final TaskMapper taskMapper;

    public void insertUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (userRepository.existsById(user.getUserId())) {
            throw new IllegalStateException("User with ID " + user.getUserId() + " already exists");
        }
        userRepository.insert(user);
    }

    public void insertTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }
        if (taskRepository.existsById(task.getTaskId())) {
            throw new IllegalStateException("Task with ID " + task.getTaskId() + " already exists");
        }

        taskRepository.insert(task);
        taskByUserRepository.insert(taskMapper.toTaskByUser(task));
    }

    public List<Task> getTasksByUserId(UUID userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        List<TaskByUser> tasks = taskByUserRepository.findByUserId(userId);
        if (tasks.isEmpty()) {
            throw new IllegalStateException("No tasks found for user ID " + userId);
        }

        return tasks.stream()
                .map(taskMapper::toTask)
                .collect(Collectors.toList());
    }

    public Task getTaskById(UUID taskId) {
        if (taskId == null) {
            throw new IllegalArgumentException("Task ID cannot be null");
        }

        return taskRepository.findByTaskId(taskId)
                .orElseThrow(() -> new IllegalStateException("Task with ID " + taskId + " not found"));
    }

    public void updateTaskStatus(UUID taskId, String status) {
        if (taskId == null || status == null) {
            throw new IllegalArgumentException("Task ID and status cannot be null");
        }

        Task existingTask = getTaskById(taskId);
        existingTask.setStatus(status);
        taskRepository.save(existingTask);

        TaskByUser taskByUser = taskMapper.toTaskByUser(existingTask);
        taskByUserRepository.save(taskByUser);
    }

    public void deleteTaskById(UUID taskId) {
        if (taskId == null) {
            throw new IllegalArgumentException("Task ID cannot be null");
        }

        if (!taskRepository.existsById(taskId)) {
            throw new IllegalStateException("Task with ID " + taskId + " does not exist");
        }

        taskRepository.deleteByTaskId(taskId);
        taskByUserRepository.deleteByTaskId(taskId);
    }
}
