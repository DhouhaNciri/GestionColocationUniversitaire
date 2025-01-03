package com.colocation.management.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.colocation.management.Model.Task;
import com.colocation.management.Model.User;
import com.colocation.management.Service.TaskService;
import com.colocation.management.Service.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    
       @Autowired
    private  UserServiceImpl userServiceImpl;
   
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestParam String description,@RequestParam String emailto) {
    	 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         if (authentication == null || !authentication.isAuthenticated()) {
             throw new RuntimeException("Authentication failed or user not authenticated");
         }
         User us= (User) authentication.getPrincipal();
        Task createdTask = taskService.createTask(new Task(description, userServiceImpl.getByEmail(emailto), us));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @GetMapping("/user/{userId}")
    public List<Task> getTasksForUser(@PathVariable Long userId) {
        User user = new User();
        user.setId(userId);
        return taskService.getTasksForUser(user);
    }
}
