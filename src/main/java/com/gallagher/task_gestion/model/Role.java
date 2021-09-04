package com.gallagher.task_gestion.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "role")
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String rolename;

    public Role(){
        super();
    }

    public Role(Long id, String rolename){
       this.id = id;
       this.rolename = rolename;
    }
}
