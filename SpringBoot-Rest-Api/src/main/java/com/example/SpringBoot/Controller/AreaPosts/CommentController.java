package com.example.SpringBoot.Controller.AreaPosts;

import com.example.SpringBoot.Payload.Comment.CommentDto;
import com.example.SpringBoot.Services.Interface.ICommentServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Api/Comment")
@RequiredArgsConstructor
public class CommentController {

    private final ICommentServices _CommentRepo;

    @PostMapping("/Posts/{postId}/comment")
    public ResponseEntity<CommentDto> CreateNewComment(
            @PathVariable(value = "postId") long postId,
            @RequestBody CommentDto model
    ) {
        return new ResponseEntity<>(_CommentRepo.CreateComment(model, postId), HttpStatus.CREATED);
    }

    @PostMapping("/Posts/{postId}/comment/{CommentId}")
    public ResponseEntity<CommentDto> UpdateComment(
            @PathVariable(value = "postId") long postId,
            @PathVariable(value = "CommentId") long CommentId,
            @RequestBody CommentDto model
    ) {
        var result = _CommentRepo.UpdateComment(postId, CommentId, model);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/Posts/{postId}/comment")
    public List<CommentDto> GetCommentsPostById(
            @PathVariable(value = "postId") long postId
    ) {
        return _CommentRepo.GetCommentById(postId);
    }

    @GetMapping("/Posts/{postId}/comment/{CommentId}")
    public ResponseEntity<?> GetCommentsId(
            @PathVariable(value = "postId") long postId,
            @PathVariable(value = "CommentId") long CommentId
    ) {
        return new ResponseEntity<>(_CommentRepo.GetCommentById(postId, CommentId), HttpStatus.FOUND);
    }

    @GetMapping("/posts/{postId}/comments/{id}")
    public  ResponseEntity<?> DeleteComment(
            @PathVariable(value = "postId") long postId,
            @PathVariable(value = "id") long CommentId
    ) {
        _CommentRepo.DeleteComment(postId,CommentId);
    return  new ResponseEntity<>("",HttpStatus.OK);
    }
}
