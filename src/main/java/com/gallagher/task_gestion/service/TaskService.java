package com.gallagher.task_gestion.service;

import com.gallagher.task_gestion.model.Task;

import java.util.List;

public interface TaskService {

    List<Task> findAllTasks();

    Task findTaskById(Long id);

    Task addTask(Task task);

    Task updateTask(Long id, Task task);

    void deleteTask(Long id);
}
