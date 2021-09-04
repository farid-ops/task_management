package com.gallagher.task_gestion;

import com.gallagher.task_gestion.model.AppUser;
import com.gallagher.task_gestion.model.Role;
import com.gallagher.task_gestion.model.Task;
import com.gallagher.task_gestion.service.AccountService;
import com.gallagher.task_gestion.service.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.Stream;

@SpringBootApplication
public class TaskGestionApplication implements CommandLineRunner {

    private final AccountService accountService;
    private final TaskService taskService;

    public TaskGestionApplication(TaskService taskService,
                                  AccountService accountService) {
        this.taskService = taskService;
        this.accountService = accountService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TaskGestionApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        this.accountService.saveRole(new Role(null, "ADMIN"));
        this.accountService.saveRole(new Role(null, "USER"));
        this.accountService.saveUser(new AppUser(null, "farid", "1234"));
        this.accountService.saveUser(new AppUser(null, "viking", "1234"));
        this.accountService.addRoleToUser("farid", "USER");
        this.accountService.addRoleToUser("viking", "ADMIN");

        Stream.of("task one", "task two", "task three").forEach(task -> {
            this.taskService.addTask(new Task(null, task));
        });
        this.taskService.findAllTasks().forEach(task -> {
            System.out.println(task.getTask_name());
        });
    }
}
