package com.example.SpringBoot.Payload.Comment;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CommentDto {
    private long Id;
    private String name;
    private String email;
    private String body;
    private long PostId;

}
