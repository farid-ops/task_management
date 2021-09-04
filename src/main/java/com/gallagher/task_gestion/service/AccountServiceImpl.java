package com.gallagher.task_gestion.service;

import com.gallagher.task_gestion.model.AppUser;
import com.gallagher.task_gestion.model.Role;
import com.gallagher.task_gestion.repository.RoleRepository;
import com.gallagher.task_gestion.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AccountServiceImpl(UserRepository userRepository,
                              RoleRepository roleRepository,
                              BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUser saveUser(AppUser user) {
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        return this.userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return this.roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        AppUser user = this.userRepository.findByUsername(username);
        Role role = this.roleRepository.findByRolename(rolename);
        user.getRoles().add(role);
    }

    @Override
    public AppUser findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }
}
