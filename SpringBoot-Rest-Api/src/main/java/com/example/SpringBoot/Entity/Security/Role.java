package com.example.SpringBoot.Entity.Security;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Role")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class Role
{
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long Id;
    private String name;
}
