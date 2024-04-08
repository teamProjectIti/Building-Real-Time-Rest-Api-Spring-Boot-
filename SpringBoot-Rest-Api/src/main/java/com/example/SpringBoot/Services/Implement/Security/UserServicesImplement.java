package com.example.SpringBoot.Services.Implement.Security;

import com.example.SpringBoot.Entity.Security.AppUser;
import com.example.SpringBoot.Exception.ResourceNotFoundException;
import com.example.SpringBoot.Payload.ResultResponse.PagnationResult;
import com.example.SpringBoot.Payload.Security.AppUserDto;
import com.example.SpringBoot.Repositery.Security.IUserRepositery;
import com.example.SpringBoot.Services.Interface.Security.IUserServices;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServicesImplement implements IUserServices {

    private final IUserRepositery _userUserRepositery;
    private final ModelMapper _modelMapper;

    @Override
    public AppUserDto CreateNewUser(AppUserDto model) {

        AppUser User=_modelMapper.map(model,AppUser.class);
       var result= _userUserRepositery.save(User);
       return _modelMapper.map(result,AppUserDto.class);
    }

    @Override
    public String UpdateUser(Long UserId, AppUserDto model) {
        return null;
    }

    @Override
    public boolean DeleteUser(Long UserId) {
        return false;
    }

    @Override
    public AppUserDto GetByIdUser(Long UserId) {
        var AppUser = _userUserRepositery.findById(UserId)
                .orElseThrow(() -> new ResourceNotFoundException("AppUser", "id", UserId));
        return _modelMapper.map(AppUser,AppUserDto.class);
    }

    @Override
    public PagnationResult GetAllUsers(int pageNo, int pageSize, String sortBy, String sortDir) {
        // sort by and order by
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        var pageable = PageRequest.of(pageNo, pageSize,sort); // filter
        Page<AppUser> Posts = _userUserRepositery.findAll(pageable);

        var listPosts = Posts.getContent(); // get data after filter
        var content = listPosts.stream().map(x -> MapToAppUserDto(x)).collect(Collectors.toList());

        // Maping Result
        PagnationResult model = new PagnationResult();
        model.setContent(content);
        model.setPageNo(Posts.getNumber());
        model.setPageSiz(Posts.getSize());
        model.setTotalElement(Posts.getTotalElements());
        model.setTotalPages(Posts.getTotalPages());
        model.setLast(Posts.isLast());
        return model;
    }

    @Override
    public List<AppUser> FindAll() {
        return _userUserRepositery.findAll();
    }

    private Object MapToAppUserDto(AppUser x) {
        return _modelMapper.map(x, AppUserDto.class);
    }
}
