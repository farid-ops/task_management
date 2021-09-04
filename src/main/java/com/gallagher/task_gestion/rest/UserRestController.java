package com.gallagher.task_gestion.rest;

import com.gallagher.task_gestion.model.AppUser;
import com.gallagher.task_gestion.model.AppUserDTO;
import com.gallagher.task_gestion.repository.UserRepository;
import com.gallagher.task_gestion.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {

    private final AccountService accountService;
    @Autowired
    private UserRepository userRepository;

    public UserRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<AppUser> getAllUsers(){
        return this.userRepository.findAll();
    }

    @PostMapping
    public AppUser register(@RequestBody AppUserDTO appUserDTO){
        if (!appUserDTO.getPassword().equals(appUserDTO.getRepassword())) {
            throw new RuntimeException("you must confirm your password");
        }
        AppUser appUser;
        appUser = this.accountService.findByUsername(appUserDTO.getUsername());
        if (appUser!=null) {
            throw new RuntimeException("this user already exists");
        }else {
            appUser.setUsername(appUserDTO.getUsername());
            appUser.setPassword(appUser.getPassword());
            this.accountService.saveUser(appUser);
            this.accountService.addRoleToUser(appUserDTO.getUsername(), "USER");
        }
        return appUser;
    }
}
