package com.bsbstudylapey.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address", schema = "public")
@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class Address {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "address_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    private Long id;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "street")
    private String street;
}
