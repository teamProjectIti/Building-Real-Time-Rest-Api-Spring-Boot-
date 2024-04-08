package com.example.SpringBoot.Config;

import com.example.SpringBoot.Config.Enums.UserRoleEnum;
import com.example.SpringBoot.Payload.Security.AppUserDto;
import com.example.SpringBoot.Payload.Security.RoleDto;
import com.example.SpringBoot.Services.Interface.Security.IRoleServices;
import com.example.SpringBoot.Services.Interface.Security.IUserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@Component
@RequiredArgsConstructor
public class starterApp  implements CommandLineRunner {

    private final IUserServices _userServices;
    private final IRoleServices _roleServices;

    @Override
    public void run(String... args) throws Exception {
        if (_roleServices.FindAll().isEmpty()) {
            _roleServices.CreateNewRole(new RoleDto(UserRoleEnum.Admin.name()));
            _roleServices.CreateNewRole(new RoleDto(UserRoleEnum.Publisher.name()));
            _roleServices.CreateNewRole(new RoleDto(UserRoleEnum.Default.name()));
        }

        Set<RoleDto> admainRole = new HashSet<>();
        admainRole.add(_roleServices.GetByName("Admin"));

        Set<RoleDto> UserRole = new HashSet<>();
        UserRole.add(_roleServices.GetByName("User"));

        Set<RoleDto> EmployeeRole = new HashSet<>();
        EmployeeRole.add(_roleServices.GetByName("Employee"));

        if (_userServices.FindAll().isEmpty()) {
            {
                _userServices.CreateNewUser(new AppUserDto("test 1","test test","123","0123","test@gmail.com"));
                _userServices.CreateNewUser(new AppUserDto("test 2","test test","123","0123","test@gmail.com"));
                _userServices.CreateNewUser(new AppUserDto("test 3","test test","123","0123","test@gmail.com"));
                _userServices.CreateNewUser(new AppUserDto("test 4","test test","123","0123","test@gmail.com"));
            }
        }
    }
}
