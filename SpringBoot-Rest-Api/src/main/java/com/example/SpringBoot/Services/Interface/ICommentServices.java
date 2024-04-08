package com.example.SpringBoot.Services.Interface;

import com.example.SpringBoot.Payload.Comment.CommentDto;

import java.util.List;

public interface ICommentServices {
    CommentDto CreateComment(CommentDto commentDto,long PostId);
    List<CommentDto> GetCommentById(long id);
    CommentDto GetCommentById(long PostId,long commendId);
    CommentDto UpdateComment(long PostId, long commendId, CommentDto commendRequest);

    void DeleteComment(long postId, long commentId);
//    PagnationResult GetAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
//    PostDto GetPostById(Long Id);
//
//    void DeletePost(Long id);
}
