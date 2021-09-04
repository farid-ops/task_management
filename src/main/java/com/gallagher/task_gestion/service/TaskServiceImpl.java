package com.gallagher.task_gestion.service;

import com.gallagher.task_gestion.model.Task;
import com.gallagher.task_gestion.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    public TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskReository){
        super();
        this.taskRepository = taskReository;
    }

    @Override
    public List<Task> findAllTasks() {
        return this.taskRepository.findAll();
    }

    @Override
    public Task findTaskById(Long id) {
        return this.taskRepository.findById(id).get();
    }

    @Override
    public Task addTask(Task task) {
        return this.taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long id, Task task) {
        Task taskFound = this.findTaskById(id);
        taskFound.setTask_name(task.getTask_name());
        return taskFound;
    }

    @Override
    public void deleteTask(Long id) {
        Task taskFound = this.findTaskById(id);
        this.taskRepository.delete(taskFound);
    }
}
