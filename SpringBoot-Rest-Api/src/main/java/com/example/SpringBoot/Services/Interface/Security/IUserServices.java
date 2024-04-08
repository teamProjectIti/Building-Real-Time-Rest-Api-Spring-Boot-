package com.example.SpringBoot.Services.Interface.Security;

import com.example.SpringBoot.Entity.Security.AppUser;
import com.example.SpringBoot.Entity.Security.Role;
import com.example.SpringBoot.Payload.ResultResponse.PagnationResult;
import com.example.SpringBoot.Payload.Security.AppUserDto;

import java.util.List;

public interface IUserServices {
    AppUserDto CreateNewUser(AppUserDto model);
    String UpdateUser(Long UserId,AppUserDto model);
    boolean DeleteUser(Long UserId);
    AppUserDto GetByIdUser(Long UserId);
    PagnationResult GetAllUsers(int pageNo, int pageSize, String sortBy, String sortDir);
    List<AppUser> FindAll();

}
