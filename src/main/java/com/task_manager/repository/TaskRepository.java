package com.task_manager.repository;

import com.task_manager.model.Task;
import com.task_manager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Integer>
{
    List<Task> findByUser(User user); //pobierz zadania danego uzytkownika
    List<Task> findByDescription(Task task); //pobierz zadania danego uzytkownika
    Optional<Task> findByTitleIgnoreCase(String name);

}
