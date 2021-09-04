package com.gallagher.task_gestion.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppUserDTO {
    private String username;
    private String password;
    private String repassword;

}
