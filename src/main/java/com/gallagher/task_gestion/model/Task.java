package com.gallagher.task_gestion.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "task")
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String task_name;

    public Task(){
        super();
    }

    public Task(Long id, String task_name){
        this.id = id;
        this.task_name = task_name;
    }
}
