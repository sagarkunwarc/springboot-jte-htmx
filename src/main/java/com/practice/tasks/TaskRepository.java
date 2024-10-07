package com.practice.tasks;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {

    private final List<Task> tasks = new ArrayList<>();

    public List<Task> findAll() {
        return tasks;
    }

    public void create(Task task) {
        tasks.add(task);
    }

    public boolean remove(String id) {
        return tasks.removeIf(tasks -> tasks.getId().equals(id));
    }

    @PostConstruct
    private void init() {
        tasks.addAll(List.of(
                new Task("Complete project proposal"),
                new Task("Review code changes"),
                new Task("Update documentation"),
                new Task("Fix reported bugs"),
                new Task("Run unit tests"),
                new Task("plan next sprint")
        ));
    }
}
