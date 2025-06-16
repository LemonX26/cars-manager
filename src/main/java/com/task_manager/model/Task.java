package com.task_manager.model;

import jakarta.persistence.*;

@Entity
public class Task
{
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;

    private String title;
    private String description;

    public Task() {
    }
    public Task(String title, String description, User user) {
        this.title = title;
        this.description = description;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne // Many tasks can be assigned to one user
    @JoinColumn(name = "user_id") // Foreign key to User table
    private User user;

}
