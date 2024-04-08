package com.example.SpringBoot.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Comment")
public class Comment {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long Id;
   private String name;
   private String email;
   private String body;

   @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Post_Id",nullable = false)
    private Post post;
}
