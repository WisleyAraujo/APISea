package br.com.api.clientes.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.api.clientes.model.ClientesModel;
import br.com.api.clientes.repository.ClientesRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/clientes")
public class ClientesController {
  @Autowired
  ClientesRepository clientesRepository;

  @GetMapping()
  public List<ClientesModel> listarClientes() {
    return clientesRepository.findAll();
  }

  @SuppressWarnings("null")
  @GetMapping("/{id}")
  public ClientesModel listarClientesId(@PathVariable Long id) {
    return clientesRepository.findById(id).get();
  }

  @PostMapping()
  public ResponseEntity<ClientesModel> cadastrarCliente(@RequestBody ClientesModel clientesModel) {
    @SuppressWarnings("null")
    ClientesModel novoCliente = clientesRepository.save(clientesModel);
    return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);

  }

}