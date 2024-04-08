package com.example.SpringBoot.Services.Implement.Security;

import com.example.SpringBoot.Entity.Security.Role;
import com.example.SpringBoot.Payload.ResultResponse.PagnationResult;
import com.example.SpringBoot.Payload.Security.RoleDto;
import com.example.SpringBoot.Repositery.Security.IRoleRepositery;
import com.example.SpringBoot.Services.Interface.Security.IRoleServices;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServices implements IRoleServices {
    private final IRoleRepositery roleRepositery;
    private final ModelMapper _mapper;
    @Override
    public RoleDto CreateNewRole(RoleDto model) {
        var res= roleRepositery.save(_mapper.map(model,Role.class));
        return _mapper.map(res,RoleDto.class);
    }

    @Override
    public String UpdateRole(Long UserId, RoleDto model) {
        return null;
    }

    @Override
    public boolean DeleteRole(Long RoleId) {
        return false;
    }

    @Override
    public RoleDto GetByIdUser(Long RoleId) {

        var res = roleRepositery.findById(RoleId);
        return _mapper.map(res,RoleDto.class);
    }

    @Override
    public RoleDto GetByName(String name) {
        var res=roleRepositery.findByName(name);
        if (res==null) return new RoleDto();
        return _mapper.map(res,RoleDto.class);
    }

    @Override
    public RoleDto GetByIdRole() {
        return null;
    }

    @Override
    public PagnationResult GetAllRole(int pageNo, int pageSize, String sortBy, String sortDir) {
        return null;
    }

    @Override
    public List<Role> FindAll() {
       return roleRepositery.findAll();
    }
}
