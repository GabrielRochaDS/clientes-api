package com.gabrielrochads.cliente_api.service;

import com.gabrielrochads.cliente_api.dto.ClienteCreateDTO;
import com.gabrielrochads.cliente_api.dto.ClienteUpdateDTO;
import com.gabrielrochads.cliente_api.dto.ClienteResponseDTO;
import com.gabrielrochads.cliente_api.exception.DomainConflictException;
import com.gabrielrochads.cliente_api.exception.NotFoundException;
import com.gabrielrochads.cliente_api.model.Cliente;

import com.gabrielrochads.cliente_api.repository.ClienteRepository;
import com.gabrielrochads.cliente_api.utils.ClienteSpecs;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    @Transactional
    public ClienteResponseDTO criar(ClienteCreateDTO dto) {
        Cliente entity = new Cliente();
        entity.setNome(dto.getNome());
        entity.setCpf(dto.getCpf());
        entity.setDataNascimento(dto.getDataNascimento());
        entity.setTelefone(dto.getTelefone());

        return ClienteResponseDTO.from(salvarComTratativa(entity));
    }

    @Transactional
    public ClienteResponseDTO atualizar(Long id, ClienteUpdateDTO dto) {
        Cliente entity = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente " + id + " não encontrado"));

        entity.setNome(dto.getNome());
        entity.setDataNascimento(dto.getDataNascimento());
        entity.setTelefone(dto.getTelefone());

        return ClienteResponseDTO.from(salvarComTratativa(entity));
    }

    @Transactional
    public void excluir(Long id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("Cliente " + id + " não encontrado");
        }
        repo.deleteById(id);
    }

    @Transactional
    public ClienteResponseDTO buscar(Long id) {
        Cliente cliente = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente " + id + " não encontrado"));
        return ClienteResponseDTO.from(cliente);
    }

    @Transactional
    public Page<ClienteResponseDTO> listarPaginado(
            int page, int size, String sort,
            String nome, String cpf
    ) {
        Specification<Cliente> spec = Specification
                .allOf(ClienteSpecs.nomeLike(nome))
                .and(ClienteSpecs.cpfEquals(cpf));

        Pageable pageable = PageRequest.of(page, size, parseSort(sort));

        return repo.findAll(spec, pageable).map(ClienteResponseDTO::from);
    }

    private Sort parseSort(String sort) {
        if (sort == null || sort.isBlank()) {
            return Sort.by(Sort.Order.desc("id"));
        }

        return Sort.by(
                Arrays.stream(sort.split(","))
                        .map(s -> {
                            String[] parts = s.split(":");
                            if (parts.length == 2 && parts[1].equalsIgnoreCase("asc")) {
                                return Sort.Order.asc(parts[0]);
                            } else {
                                return Sort.Order.desc(parts[0]);
                            }
                        })
                        .toList()
        );
    }


    private Cliente salvarComTratativa(Cliente entity) {
        try {
            return repo.save(entity);
        } catch (DataIntegrityViolationException e) {
            throw new DomainConflictException("CPF já cadastrado");
        }
    }
}
