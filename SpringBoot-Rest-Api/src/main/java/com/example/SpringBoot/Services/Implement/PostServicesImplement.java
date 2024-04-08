package com.example.SpringBoot.Services.Implement;

import com.example.SpringBoot.Entity.Post;
import com.example.SpringBoot.Exception.ResourceNotFoundException;
import com.example.SpringBoot.Payload.Post.PostDto;
import com.example.SpringBoot.Payload.ResultResponse.PagnationResult;
import com.example.SpringBoot.Repositery.IPostRepositery;
import com.example.SpringBoot.Services.Interface.IPostServices;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServicesImplement implements IPostServices {

    private final ModelMapper _modelMapper;
    private final IPostRepositery repo;

    @Override
    public PostDto CreatePost(PostDto postDto) {

        //convert Dto

        var model = new Post();
        model.setContent(postDto.getContent());
        model.setDescription(postDto.getDescription());
        model.setTitle(postDto.getTitle());

        Post NewPost = repo.save(model);

        postDto.setId(NewPost.getId());
        postDto.setContent(NewPost.getContent());
        postDto.setDescription(NewPost.getDescription());
        postDto.setTitle(NewPost.getTitle());

        return postDto;
    }
    @Override
    public PagnationResult GetAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        // sort by and order by
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
        Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        var pageable = PageRequest.of(pageNo, pageSize,sort); // filter
        Page<Post> Posts = repo.findAll(pageable);

        var listPosts = Posts.getContent(); // get data after filter
        var content = listPosts.stream().map(x -> MapToPostDto(x)).collect(Collectors.toList());

        // Maping Result
        PagnationResult model = new PagnationResult();
        model.setContent(content);
        model.setPageNo(Posts.getNumber());
        model.setPageSiz(Posts.getSize());
        model.setTotalElement(Posts.getTotalElements());
        model.setTotalPages(Posts.getTotalPages());
        model.setLast(Posts.isLast());
        return model;

//        var result = repo.findAll();
//        return result.stream().map(post -> MapToPostDto(post)).collect(Collectors.toList());
    }
    @Override
    public PostDto GetPostById(Long id) {
        var post = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return MapToPostDto(post);
    }

    @Override
    public PostDto UpdatePost(Long Id,PostDto model) {

        var Result=repo.findById(Id).orElseThrow(()->new ResourceNotFoundException("Post","Id",Id));
        Result.setTitle(model.getTitle());
        Result.setDescription(model.getDescription());
        Result.setContent(model.getContent());

       var Data= repo.save(Result);
        return  MapToPostDto(Data);
    }

    @Override
    public void DeletePost(Long Id) {
        var Result=repo.findById(Id).orElseThrow(()->new ResourceNotFoundException("Post","Id",Id));
        repo.delete(Result);
    }

    private PostDto MapToPostDto(Post model) {
        return _modelMapper.map(model,PostDto.class);
//        var res = new PostDto();
//        res.setTitle(model.getTitle());
//        res.setId(model.getId());
//        res.setContent(model.getContent());
//        res.setDescription(model.getDescription());
//        return res;
    }
    private Post MapToPost(PostDto model) {
       return _modelMapper.map(model,Post.class);

//        var res = new Post();
//        res.setTitle(model.getTitle());
//        res.setId(model.getId());
//        res.setContent(model.getContent());
//        res.setDescription(model.getDescription());
//        return res;
//        return res;
//        return res;
    }
}
