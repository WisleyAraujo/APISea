// Repository e responsavel por
package br.com.api.clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.clientes.model.ClientesModel;

public interface ClientesRepository extends JpaRepository<ClientesModel, Long> {
}
