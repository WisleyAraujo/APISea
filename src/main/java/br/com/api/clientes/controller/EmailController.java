package br.com.api.clientes.controller;
import org.springframework.web.bind.annotation.RestController;
import br.com.api.clientes.model.EmailModel;
import br.com.api.clientes.repository.EmailRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/email")
public class EmailController {
  @Autowired
  EmailRepository emailRepository;

  @GetMapping()
  public List<EmailModel> listarEmail() {
    return emailRepository.findAll();
  }

  @PostMapping()
  public ResponseEntity<EmailModel> cadastrarEmail(@RequestBody EmailModel
  emailModel) {
  @SuppressWarnings("null")
  EmailModel novoEmail = emailRepository.save(emailModel);
  return ResponseEntity.status(HttpStatus.CREATED).body(novoEmail);
  }

}
