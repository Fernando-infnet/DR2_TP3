package com.example;

public class Task {

    private final String title;
    private final String description;
    private TaskStatus status;
    private User assignee;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = TaskStatus.TODO;
    }

    public void assignUser(User user) {
        this.assignee = user;
    }

    public void changeStatus(TaskStatus newStatus) {
        this.status = newStatus;
    }

    public String showDetails() {
        return String.format(
            "Title: %s | Description: %s | Status: %s | Assignee: %s",
            title,
            description,
            status,
            assignee != null ? assignee.getName() : "Unassigned"
        );
    }

    public TaskStatus getStatus() {
        return status;
    }
}
