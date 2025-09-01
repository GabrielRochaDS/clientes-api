package com.gabrielrochads.cliente_api.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "clientes", uniqueConstraints = {
        @UniqueConstraint(columnNames = "cpf"),
})
public class Clientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, length = 11)
    private String cpf;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    private String telefone;
}
