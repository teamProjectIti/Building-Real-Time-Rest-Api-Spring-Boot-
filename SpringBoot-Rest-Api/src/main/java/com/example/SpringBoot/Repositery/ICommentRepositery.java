package com.example.SpringBoot.Repositery;

import com.example.SpringBoot.Entity.Comment;
import com.example.SpringBoot.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICommentRepositery extends JpaRepository<Comment,Long>
{
    List<Comment> findByPostId(long id);
}
