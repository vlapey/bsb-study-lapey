package com.bsbstudylapey.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Entity
@Table(name = "client", schema = "public")
@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class Client {


    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "client_id_seq", allocationSize = 1)
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

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToMany
    @JoinTable(
            name = "address_client",
            joinColumns = {@JoinColumn(name = "address_client_id")},
            inverseJoinColumns = {@JoinColumn(name = "client_address_id")}
    )
    private List<Address> addressOfClient;
}
