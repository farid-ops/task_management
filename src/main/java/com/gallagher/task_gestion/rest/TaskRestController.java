package com.gallagher.task_gestion.rest;

import com.gallagher.task_gestion.model.Task;
import com.gallagher.task_gestion.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/tasks")
public class TaskRestController {

    private final TaskService taskService;

    public TaskRestController(TaskService taskService){
        super();
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTask(){
        return this.taskService.findAllTasks();
    }

    @GetMapping("/{id}")
    public Task taskById(@PathVariable Long id){
        return this.taskService.findTaskById(id);
    }

    @PostMapping
    public Task saveTask(@RequestBody Task task){
        return this.taskService.addTask(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task){
        return this.taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteTask(@PathVariable Long id){
        Map<String, Boolean> response = new HashMap<>();
        this.taskService.deleteTask(id);
        response.put("Deleted", Boolean.TRUE);
        return response;
    }
}
