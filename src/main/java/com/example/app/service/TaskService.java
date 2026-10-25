package com.example.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.jspecify.annotations.Nullable;

import com.example.app.dto.TaskRequest;
import com.example.app.dto.TaskResponse;
import com.example.app.exception.TaskNotFoundException;
import com.example.app.model.Task;

import jakarta.validation.Valid;

public class TaskService {
	
	private final List<Task> tasks = new ArrayList<>();
	private final AtomicLong idGenerator = new AtomicLong(1);

//	public TaskResponse createTask(@Valid TaskRequest request) {
//		Long id = idGenerator.incrementAndGet();
//		TaskResponse task = new TaskResponse(id, request.getTitle(), request.getDescription(), request.getPriority());
//		tasks.add(task);
//		return task;
//	}
	
	public TaskResponse createTask(TaskRequest request) {

        Task task = new Task(
                idGenerator.getAndIncrement(),
                request.getTitle(),
                request.getDescription(),
                request.getPriority()
        );

        tasks.add(task);

        return convertToResponse(task);
    }

	public List<TaskResponse> getAllTasks() {

        return tasks.stream()
                .map(this::convertToResponse)
                .toList();
    }

	public TaskResponse getTaskById(Long id) {

        Task task = tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElseThrow(() ->
                        new TaskNotFoundException("Task not found with id: " + id)
                );

        return convertToResponse(task);
    }

	public TaskResponse updateTask(Long id, TaskRequest request) {

        Task task = tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElseThrow(() ->
                        new TaskNotFoundException("Task not found with id: " + id)
                );

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());

        return convertToResponse(task);
    }

	public void deleteTask(Long id) {

        Task task = tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElseThrow(() ->
                        new TaskNotFoundException("Task not found with id: " + id)
                );

        tasks.remove(task);
    }
	
	private TaskResponse convertToResponse(Task task) {

        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getPriority()
        );
    }

}