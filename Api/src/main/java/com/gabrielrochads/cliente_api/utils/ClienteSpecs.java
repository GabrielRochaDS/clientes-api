package com.gabrielrochads.cliente_api.utils;

import com.gabrielrochads.cliente_api.model.Cliente;
import org.springframework.data.jpa.domain.Specification;

public class ClienteSpecs {

    public static Specification<Cliente> nomeLike(String q) {
        return (root, query, cb) -> {
            if (q == null || q.isBlank()) {
                return null; // Sem filtro
            }
            return cb.like(cb.lower(root.get("nome")), "%" + q.toLowerCase() + "%");
        };
    }

    public static Specification<Cliente> cpfEquals(String cpf) {
        return (root, query, cb) -> {
            if (cpf == null || cpf.isBlank()) {
                return null;
            }
            return cb.equal(root.get("cpf"), cpf);
        };
    }
}

