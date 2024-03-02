package br.com.api.clientes.controller;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/clientes")
public class ClientesController {
  @GetMapping()
  public String listarClientes() {
    return "Você está na lista de clientes.";
  }
}