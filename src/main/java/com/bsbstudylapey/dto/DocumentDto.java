package com.bsbstudylapey.dto;

import com.bsbstudylapey.models.User;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class DocumentDto {

    @Id
    private Long id;

    @NotNull(message = "document_name shouldn't be null")
    private String documentName;

    private User user;
}
