package com.car_manager.controller;

import com.car_manager.service.TaskService;
import com.car_manager.model.Task;
import com.car_manager.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskUIController {
    private final TaskService taskService;

    public TaskUIController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getTasks(@AuthenticationPrincipal User user, Model model) // model allows send data to UI
    {
        List<Task> tasks = taskService.getTasksForUser(user);
        model.addAttribute("tasks", tasks); // add attribute to model
        model.addAttribute("newTask", new Task()); // add empty task to model for form
        return "tasks"; // Returns (tasks.html)
    }

    @PostMapping("/add")
    public String addTask(@ModelAttribute("newTask") Task task, @AuthenticationPrincipal User user) {
        taskService.addTask(task, user);
        return "redirect:/tasks"; // Refresh the page after adding a task
    }

    @PostMapping("/delete/{taskId}") //Post dlatego, że nie można wysłać delete z formularza
    public String deleteTask(@PathVariable int taskId, @AuthenticationPrincipal User user) {
        taskService.deleteTask(taskId, user);
        return "redirect:/tasks"; // Redirect to the tasks page after deleting a task
    }

    @GetMapping("/search")
    public String showSearchForm(@RequestParam(required = false) String title, Model model) {
        if (title != null && !title.isEmpty()) {
            taskService.findTaskByTitle(title).ifPresentOrElse(
                    task -> model.addAttribute("task", task),
                    () -> model.addAttribute("notFound", true)
            );
        }
        return "task-search"; // renderuje task-search.html
    }

}