package com.example.SpringBoot.Payload.Security;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
public class RoleDto {
    private Long Id;
    @NotEmpty
    private String Name;

    public RoleDto(String name) {
    setName(name);
    }
    public RoleDto( ) {
    }
}
