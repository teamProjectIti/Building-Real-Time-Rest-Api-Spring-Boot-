package com.example.SpringBoot.Controller.AreaPosts;

import com.example.SpringBoot.Payload.Post.PostDto;
import com.example.SpringBoot.Payload.ResultResponse.PagnationResult;
import com.example.SpringBoot.Services.Interface.IPostServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Api/Posts")
@RequiredArgsConstructor
public class PostController {

    private final IPostServices _postRepo;

    @PostMapping("CreatePost")
    @PreAuthorize("hasRole('Admin')") // Requires ROLE_USER role for authorization

    public ResponseEntity<PostDto> CreatePost(@Valid @RequestBody PostDto model) {
        return new ResponseEntity<>(_postRepo.CreatePost(model), HttpStatus.CREATED);
    }

    @GetMapping("GetAllPosts")
    public PagnationResult GetAllPosts(
            @RequestParam(value = "PageNo", defaultValue = "0", required = false) int PageNo,
            @RequestParam(value = "PageSize", defaultValue = "10", required = false) int PageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "SortDir", defaultValue = "asc", required = false) String sortDir
    ) {
        return _postRepo.GetAllPosts(PageNo, PageSize, sortBy, sortDir);
    }

    @GetMapping("GetPostById")
    public ResponseEntity<PostDto> GetPostById(Long Id) {
        return ResponseEntity.ok(_postRepo.GetPostById(Id));
    }

    @PutMapping("UpdatePost/{id}")
    public ResponseEntity<PostDto> UpdatePost(@Valid @RequestBody PostDto post, @PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(_postRepo.UpdatePost(id, post));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeletePost(@PathVariable(name = "id") Long id) {
        _postRepo.DeletePost(id);
        return new ResponseEntity<>("Success Delete Post", HttpStatus.OK);
    }
}
