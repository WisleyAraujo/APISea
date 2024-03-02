package br.com.api.clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.clientes.model.TelefoneModel;

public interface TelefoneRepository extends JpaRepository<TelefoneModel, Long> {

  

}