package br.com.api.clientes.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.api.clientes.model.ClientesModel;
import br.com.api.clientes.repository.ClientesRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")
public class ClientesController {
  @Autowired
  private ClientesRepository clientesRepository;

  @GetMapping("/listar")
  public List<ClientesModel> listarClientes() {
    return clientesRepository.findAll();
  }

  @GetMapping("listar/{id}")
  public ClientesModel listarClientesId(@PathVariable Long id) {
    return clientesRepository.findById(id).get();
  }

  @PostMapping("/cadastrar")
  public ResponseEntity<ClientesModel> cadastrarCliente(@RequestBody ClientesModel clientesModel) {
    ClientesModel novoCliente = clientesRepository.save(clientesModel);
    return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
  }

  @DeleteMapping("deletar/{id}")
  public ResponseEntity<Void> deletarClient(@PathVariable Long id) {
    ClientesModel clientesEncontrado = clientesRepository.findById(id).get();
    if (clientesEncontrado == null) {
      return ResponseEntity.badRequest().build();
    }
    clientesRepository.delete(clientesEncontrado);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/alterar/{id}")
  public ResponseEntity<ClientesModel> alterarCliente(@PathVariable Long id,
      @RequestBody ClientesModel clienteAtualizado) {
    ClientesModel clienteExistente = clientesRepository.findById(id).orElse(null);
    if (clienteExistente == null) {
      return ResponseEntity.notFound().build();
    }
    clienteExistente.setNome(clienteAtualizado.getNome());
    clienteExistente.setCpf(clienteAtualizado.getCpf());
    clienteExistente.setEndereco(clienteAtualizado.getEndereco());
    clienteExistente.setTelefones(clienteAtualizado.getTelefones());
    clienteExistente.setEmails(clienteAtualizado.getEmails());

    ClientesModel clienteAlterado = clientesRepository.save(clienteExistente);
    return ResponseEntity.ok(clienteAlterado);
  }
}
