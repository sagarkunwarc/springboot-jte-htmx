package com.practice.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TaskController {

    private static final Logger log = LoggerFactory.getLogger(TaskController.class);

    private final TaskRepository taskRepository;


    public TaskController(TaskRepository taskRepository) {

        this.taskRepository = taskRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("tasks", taskRepository.findAll());
        return "index";
    }

    @PostMapping("/add-task")
    public String addTask(@RequestParam String description, Model model) {
        Task newTask = new Task(description);
        taskRepository.create(newTask);
        model.addAttribute("task", newTask);
        return "task-row";
    }

    @DeleteMapping("/f/{id}")
    @ResponseBody
    public void deleteTask(@PathVariable String id){
        boolean removeTask = taskRepository.remove(id);
        log.info("This {} was deleted", id);
    }
}
