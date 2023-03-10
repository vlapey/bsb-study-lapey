package com.bsbstudylapey.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class AddressDto {

    @Id
    private Long id;

    @NotNull(message = "country name shouldn't be null")
    private String countryName;

    @NotNull(message = "city name shouldn't be null")
    private String cityName;

    @NotNull(message = "street shouldn't be null")
    private String street;
}
