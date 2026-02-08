package com.car_manager.service;

import com.car_manager.repository.TaskRepository;
import com.car_manager.model.Task;
import com.car_manager.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //It indicates that this class is a service component in the Spring context.
public class TaskService
{
    private final TaskRepository taskRepository; //It is a reference to the TaskRepository interface, which is used to perform CRUD operations on Task entities.
    public TaskService(TaskRepository taskRepository)
    {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasksForUser(User user)
    {
        return taskRepository.findByUser(user); //It retrieves a list of tasks associated with the specified user.
    }
    /*
    Nie widzisz algorytmu, bo Spring generuje całą logikę za Ciebie. Ty tylko deklarujesz,
    co chcesz, a Spring Data JPA robi resztę — na podstawie konwencji nazewniczej metod.
    Metoda findByUser(User user) nie ma implementacji, ale Spring analizuje nazwę metody, czyli:

    findBy → to znaczy, że chcemy coś wyszukać

    User → to wskazuje, że filtrujemy po polu user w encji Task
     */
    public Task addTask(Task task, User user)
    {
        task.setUser(user); //It sets the user for the task.
        return taskRepository.save(task); //It saves the task to the database and returns the saved task.
    }

    public void deleteTask(int taskId, User user)
    {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new IllegalArgumentException("Task not found"));

        if(!task.getUser().getUsername().equals(user.getUsername()))
            throw new IllegalArgumentException("You are not authorized to delete this task. You can only delete your own tasks.");


        taskRepository.delete(task); //It deletes the task from the database, which we found on the beginning in method.
    }
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<String> getAllTasksDescription(Task task) {
        return taskRepository.findAll()
                .stream().
                map(Task::getDescription).
                toList();
    }

    // Dodajemy metodę do wyszukiwania po nazwie
    public Optional<Task> findTaskByTitle(String title) {
        return taskRepository.findByTitleIgnoreCase(title);
    }

}
