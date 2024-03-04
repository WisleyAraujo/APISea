package br.com.api.clientes.controller;

import br.com.api.clientes.model.UserRole;

public record RegisterDTO(String login, String senha, UserRole role) {
}
