package br.com.api.clientes.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.api.clientes.repository.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.api.clientes.model.TelefoneModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/telefones")
public class TelefoneController {
  @Autowired
  private TelefoneRepository telefoneRepository;

  @GetMapping
  public List<TelefoneModel> litarTelefones() {
    return telefoneRepository.findAll();
  }

  @PostMapping("/cadastro")
  public ResponseEntity<TelefoneModel> casdastrarTelefone(@RequestBody TelefoneModel telefoneModel) {
    @SuppressWarnings("null")
    TelefoneModel novoTelefone = telefoneRepository.save(telefoneModel);
    return ResponseEntity.status(HttpStatus.CREATED).body(novoTelefone);
  }
}
