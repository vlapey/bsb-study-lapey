package com.bsbstudylapey.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class UserRequest {
    @NotNull(message = "firstname shouldn't be null")
    private String firstName;

    @NotNull(message = "lastname shouldn't be null")
    private String lastName;

    @NotNull
    @Pattern(regexp = "^[0-9\\-\\+]{9,15}$", message = "invalid number entered")
    private String phoneNumber;

    @Email(message = "invalid email entered")
    private String email;

    private Date createdAt;
    private Date updatedAt;
}
