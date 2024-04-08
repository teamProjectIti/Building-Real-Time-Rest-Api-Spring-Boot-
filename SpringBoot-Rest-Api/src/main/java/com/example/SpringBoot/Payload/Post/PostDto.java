package com.example.SpringBoot.Payload.Post;

import com.example.SpringBoot.Payload.Comment.CommentDto;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class PostDto    {
    private long Id;
    @NotEmpty
    @Size(min=2,message="Post Title Should have at least 2 Character")
    private String Title;
    @Column(name = "Description",nullable = false)
    private String Description;
    private String Content;
//    private Set<CommentDto> commenets;
}
