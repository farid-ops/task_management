package com.gallagher.task_gestion.service;

import com.gallagher.task_gestion.model.AppUser;
import com.gallagher.task_gestion.model.Role;

public interface AccountService {

    AppUser saveUser(AppUser user);

    Role saveRole(Role role);

    void addRoleToUser(String username, String rolename);

    AppUser findByUsername(String username);
}
