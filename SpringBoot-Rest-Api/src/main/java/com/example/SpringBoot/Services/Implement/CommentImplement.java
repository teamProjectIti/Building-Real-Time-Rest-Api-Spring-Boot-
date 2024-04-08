package com.example.SpringBoot.Services.Implement;

import com.example.SpringBoot.Entity.Comment;
import com.example.SpringBoot.Entity.Post;
import com.example.SpringBoot.Exception.BlogApiException;
import com.example.SpringBoot.Exception.ResourceNotFoundException;
import com.example.SpringBoot.Payload.Comment.CommentDto;
import com.example.SpringBoot.Repositery.ICommentRepositery;
import com.example.SpringBoot.Repositery.IPostRepositery;
import com.example.SpringBoot.Services.Interface.ICommentServices;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentImplement implements ICommentServices {
    private final ICommentRepositery _repoComment;
    private final IPostRepositery _repoPost;

    public CommentImplement(ICommentRepositery repoComment, IPostRepositery repoPost) {
        _repoComment = repoComment;
        _repoPost = repoPost;
    }

    @Override
    public CommentDto CreateComment(CommentDto commentDto, long PostId) {
        var comment = MapToEntity(commentDto);
        //retrive
        var post = _repoPost.findById(PostId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", PostId));

        comment.setPost(post);

        Comment newComment = _repoComment.save(comment);

        return MapToDto(newComment);
    }

    @Override
    public List<CommentDto> GetCommentById(long id) {

        var comments = _repoComment.findByPostId(id);
        return comments.stream().map(x -> MapToDto(x)).collect(Collectors.toList());
    }

    @Override
    public CommentDto GetCommentById(long PostId, long commendId) {

        // retrieve entity post by id
        var post = _repoPost.findById(PostId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", PostId));

        // retrieve entity commend by id
        Comment comment = _repoComment.findById(commendId).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commendId));

        if (comment.getPost().getId() != (post.getId()))
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment Not Belong this post");

        return MapToDto(comment);
    }

    @Override
    public CommentDto UpdateComment(long PostId, long commendId, CommentDto commendRequest) {
        // retrieve entity post
        Post post = _repoPost.findById(PostId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", PostId));
        // retrieve entity comment

        Comment comment = _repoComment.findById(commendId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", commendId));

        if (comment.getPost().getId() != post.getId())
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment Not Exist In Post");


        comment.setName(commendRequest.getName());
        comment.setBody(commendRequest.getBody());
        comment.setEmail(commendRequest.getEmail());
        var update = _repoComment.save(comment);
        return MapToDto(update);
    }

    @Override
    public void DeleteComment(long PostId, long commendId) {
        // retrieve entity post
        Post post = _repoPost.findById(PostId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", PostId));
        // retrieve entity comment

        Comment comment = _repoComment.findById(commendId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", commendId));

        if (comment.getPost().getId() != post.getId())
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment Not Exist In Post");


        _repoComment.delete(comment);
    }

    private CommentDto MapToDto(Comment coment) {
        var model = new CommentDto();
        model.setBody(coment.getBody());
        model.setId(coment.getId());
        model.setEmail(coment.getEmail());
        model.setName(coment.getName());

        return model;
    }

    private Comment MapToEntity(CommentDto comentDto) {
        var model = new Comment();
        model.setBody(comentDto.getBody());
        model.setId(comentDto.getId());
        model.setEmail(comentDto.getEmail());
        model.setName(comentDto.getName());

        return model;
    }
}
