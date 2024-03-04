package br.com.api.clientes.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.clientes.model.UsuarioModel;
import br.com.api.clientes.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UsuarioRepository repository;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody AuthenticationDTO data) {
    var userNamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.senha());
    @SuppressWarnings("unused")
    var auth = this.authenticationManager.authenticate(userNamePassword);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody RegisterDTO data) {
    UserDetails existingUser = this.repository.findByLogin(data.login());
    if (existingUser != null) {
      return ResponseEntity.badRequest().build();
    }

    String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
    UsuarioModel newUser = new UsuarioModel(data.login(), encryptedPassword, data.role());

    this.repository.save(newUser);

    return ResponseEntity.ok().build();
  }

}
