package br.com.api.clientes.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.clientes.infra.security.TokenService;
import br.com.api.clientes.model.UsuarioModel;
import br.com.api.clientes.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "*")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  TokenService tokenService;

  @Autowired
  UsuarioRepository repository;

  @PostMapping("/login")
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  public ResponseEntity<?> login(@RequestBody AuthenticationDTO data) {
    var userNamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.senha());
    var auth = this.authenticationManager.authenticate(userNamePassword);

    var token = tokenService.generateToken((UsuarioModel) auth.getPrincipal());

    return ResponseEntity.ok(new LoginRespondeDTO(token));
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
