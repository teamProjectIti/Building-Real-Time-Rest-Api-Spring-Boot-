package com.example.SpringBoot.Services.Interface;

import com.example.SpringBoot.Payload.Post.PostDto;
import com.example.SpringBoot.Payload.ResultResponse.PagnationResult;

public interface IPostServices {
    PostDto CreatePost(PostDto PostDto);
    PagnationResult GetAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
    PostDto GetPostById(Long Id);
    PostDto UpdatePost(Long Id,PostDto model);

    void DeletePost(Long id);
}
