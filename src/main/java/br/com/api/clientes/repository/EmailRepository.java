package br.com.api.clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.clientes.model.EmailModel;

public interface EmailRepository extends JpaRepository<EmailModel, Long> {

}
