package com.gallagher.task_gestion.repository;

import com.gallagher.task_gestion.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    
    AppUser findByUsername(String username);
}
