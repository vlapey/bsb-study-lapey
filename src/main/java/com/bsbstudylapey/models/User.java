package com.bsbstudylapey.models;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "user", schema = "public")
@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class User {


    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "email")
    private String email;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToMany
    @JoinTable(
            name = "address_user",
            joinColumns = { @JoinColumn(name = "address_user_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_address_id")}
    )
    private List<Address> addressUser;

}
