package org.facultymanagementsystem.facultymanagementsystem.registration;

import lombok.Data;
import org.facultymanagementsystem.facultymanagementsystem.model.Role;

import java.util.List;

@Data
public class RegistrationRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<Role> roles;
}

