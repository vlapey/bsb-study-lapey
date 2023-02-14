package com.bsbstudylapey.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "document", schema = "public")
@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class Document {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "document_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    private Long id;

    @Column(name = "document_name")
    private String documentName;

    @ManyToOne(cascade = CascadeType.ALL)
    private Client client;
}
