package com.example.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.dto.TaskRequest;
import com.example.app.dto.TaskResponse;
import com.example.app.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	private static final Logger log = LoggerFactory.getLogger(TaskController.class);
	private final TaskService taskService;
	
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	
	@PostMapping
	public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest request) {
		log.info("Creating task with title: {}", request.getTitle());
		
		//Call the service layer to create the task
		TaskResponse response = taskService.createTask(request);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(response);
	}
	
	@GetMapping
	public ResponseEntity<List<TaskResponse>> getAllTasks() {
		log.info("Fetching all tasks");
		return ResponseEntity.ok(taskService.getAllTasks());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TaskResponse> getTaskbyId(@PathVariable Long id) {
		log.info("Fetching task with id: {}", id);
		return ResponseEntity.ok(taskService.getTaskById(id));
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable Long id, @Valid @RequestBody TaskRequest request) {

        log.info("Updating task with id: {}", id);

        return ResponseEntity.ok(
                taskService.updateTask(id, request)
        );
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {

        log.info("Deleting task with id: {}", id);

        taskService.deleteTask(id);

        return ResponseEntity.noContent().build();
    }
}
