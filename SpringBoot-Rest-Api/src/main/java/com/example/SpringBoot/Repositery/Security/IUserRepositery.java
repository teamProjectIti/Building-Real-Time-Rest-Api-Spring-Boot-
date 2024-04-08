package com.example.SpringBoot.Repositery.Security;

import com.example.SpringBoot.Entity.Security.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepositery extends JpaRepository<AppUser,Long> {
}
