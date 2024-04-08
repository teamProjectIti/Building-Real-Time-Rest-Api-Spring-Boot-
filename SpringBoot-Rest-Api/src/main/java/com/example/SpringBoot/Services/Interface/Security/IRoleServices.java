package com.example.SpringBoot.Services.Interface.Security;

import com.example.SpringBoot.Entity.Security.Role;
import com.example.SpringBoot.Payload.ResultResponse.PagnationResult;
import com.example.SpringBoot.Payload.Security.AppUserDto;
import com.example.SpringBoot.Payload.Security.RoleDto;

import java.util.List;

public interface IRoleServices {
    RoleDto CreateNewRole(RoleDto model);
    String UpdateRole(Long UserId,RoleDto model);
    boolean DeleteRole(Long RoleId);
    RoleDto GetByIdUser(Long RoleId);
    RoleDto GetByName(String name);
    RoleDto GetByIdRole();
    PagnationResult GetAllRole(int pageNo, int pageSize, String sortBy, String sortDir);
    List<Role> FindAll();
}
