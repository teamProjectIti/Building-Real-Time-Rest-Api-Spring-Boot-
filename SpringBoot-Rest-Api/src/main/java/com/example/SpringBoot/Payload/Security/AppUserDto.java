package com.example.SpringBoot.Payload.Security;

import com.example.SpringBoot.Entity.Security.Role;
import com.example.SpringBoot.Payload.Post.PostDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data

public class AppUserDto {
    private Long Id;
    @NotEmpty
    private String FullName;
    @NotEmpty
    private String UserName;

    public AppUserDto(String fullName, String userName, String password, String phone, String email) {
        FullName = fullName;
        UserName = userName;
        Password = password;
        Phone = phone;
        Email = email;
    }

    @NotEmpty
    private String Password;
    @NotEmpty
    private String Phone;
    @NotEmpty
    private String Email;
    private Set<RoleDto> Roles;
}
