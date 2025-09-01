package com.gabrielrochads.cliente_api.dto;

import java.time.LocalDate;
import java.time.Period;

import com.gabrielrochads.cliente_api.model.Cliente;

public class ClienteResponseDTO {

    private final Long id;
    private final String nome;
    private final String cpf;
    private final LocalDate dataNascimento;
    private final Integer idade;
    private final String telefone;

    public ClienteResponseDTO(Long id, String nome, String cpf, LocalDate dataNascimento,
                              Integer idade, String telefone) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.idade = idade;
        this.telefone = telefone;
    }

    // Factory method to create DTO from entity
    public static ClienteResponseDTO from(Cliente entity) {
        return new ClienteResponseDTO(
                entity.getId(),
                entity.getNome(),
                entity.getCpf(),
                entity.getDataNascimento(),
                Period.between(entity.getDataNascimento(), LocalDate.now()).getYears(),
                entity.getTelefone()
        );
    }

    // Getters
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public Integer getIdade() { return idade; }
    public String getTelefone() { return telefone; }
}

