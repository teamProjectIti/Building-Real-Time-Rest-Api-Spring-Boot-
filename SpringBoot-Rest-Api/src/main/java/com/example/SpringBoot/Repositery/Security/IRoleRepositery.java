package com.example.SpringBoot.Repositery.Security;

import com.example.SpringBoot.Entity.Security.AppUser;
import com.example.SpringBoot.Entity.Security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IRoleRepositery extends JpaRepository<Role,Long> {
//    @Query("SELECT r FROM Role r WHERE r.name = :Name")

    Role findByName(String Name);
}
