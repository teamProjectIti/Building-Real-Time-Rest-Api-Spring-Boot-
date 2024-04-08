package com.example.SpringBoot.Repositery;

import com.example.SpringBoot.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostRepositery extends JpaRepository<Post,Long>
{

}
