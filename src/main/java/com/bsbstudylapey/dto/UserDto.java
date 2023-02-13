package com.bsbstudylapey.dto;


import com.bsbstudylapey.models.Address;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class UserDto {
    @Id
    private Long id;

    @NotNull(message = "firstname shouldn't be null")
    private String firstName;

    @NotNull(message = "lastname shouldn't be null")
    private String lastName;

    @NotNull
    @Pattern(regexp = "^[0-9\\-\\+]{9,15}$", message = "invalid number entered")
    private String phoneNumber;

    @Email(message = "invalid email entered")
    private String email;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<Address> addressOfUser;
}
