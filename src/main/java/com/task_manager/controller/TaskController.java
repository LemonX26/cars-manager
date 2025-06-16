package com.task_manager.controller;

import com.task_manager.model.Task;
import com.task_manager.model.User;
import com.task_manager.service.TaskService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController //for JSON responses
@RequestMapping("/api/tasks") //base URL for all endpoints in this controller
public class TaskController
{
    private final TaskService  taskService;
    public TaskController(TaskService taskService)
    {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getTasksForUser(@AuthenticationPrincipal User user) // Authentication Principal is a Spring Security annotation that allows you to access the currently authenticated user e.g only for logged in users
    {
        return taskService.getTasksForUser(user);
    }
    @PostMapping
    public Task addTask(@RequestBody Task task, @AuthenticationPrincipal User user) //RequestBody is a Spring annotation which mapping the HTTP request body to a Java object.// It is used to bind the incoming JSON data to the Task object.
    {
        return taskService.addTask(task, user);
    }

    @DeleteMapping("/{taskId}") //This annotation maps HTTP DELETE requests to the specified URL pattern.
    public void deleteTask(@PathVariable int taskId, @AuthenticationPrincipal User user) //PathVariable is a Spring annotation that indicates that a method parameter should be bound to a URI template variable.
    {                      //.../delete/{taskId} e.g /delete/1
        taskService.deleteTask(taskId, user); //It deletes the task with the specified ID for the authenticated user.
    }

    @GetMapping("/all") // it is REST API endpoint to get all tasks. You need full path (/api/tasks/all)to get access to this endpoint.
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/descriptions") // it is REST API endpoint to get all tasks description. You need full path (/api/tasks/descriptions)to get access to this endpoint.
    public List<String> getAllDescription(Task task) {
        return taskService.getAllTasksDescription(task);
    }


    @GetMapping("/search-form")
    public String showSearchForm() {
        return "task-search";
    }


}
