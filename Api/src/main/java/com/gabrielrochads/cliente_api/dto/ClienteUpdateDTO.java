package com.gabrielrochads.cliente_api.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class ClienteUpdateDTO {

    @NotBlank
    private String nome;

    @NotNull
    private LocalDate dataNascimento;

    private String telefone;

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }


    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
}
