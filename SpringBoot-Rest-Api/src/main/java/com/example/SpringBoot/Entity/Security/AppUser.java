package com.example.SpringBoot.Entity.Security;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="ApplicationUser")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AppUser {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long Id;
    @Column(name = "FullName", nullable = false)

    private String FullName;
    @Column(name = "UserName", nullable = false)

    private String UserName;
    @Column(name = "Password", nullable = false)

    private String Password;
    @Column(name = "Phone", nullable = false)

    private String Phone;
    @Column(name = "Email", nullable = false)

    private String Email;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "Security_User_Role",
            joinColumns = @JoinColumn(name = "UserId"),
            inverseJoinColumns = @JoinColumn(name = "RoleId"))
    @OrderColumn(name = "id")
    private Set<Role> roles = new HashSet<>();
}
