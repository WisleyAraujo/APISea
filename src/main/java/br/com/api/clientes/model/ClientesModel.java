package br.com.api.clientes.model;

import java.util.List;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clientes")
@Getter
@Setter
public class ClientesModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @NonNull
  private Long id;

  @NonNull
  private String nome;
  @NonNull
  private String cpf;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "endereco_id", referencedColumnName = "id")
  private EnderecoModel endereco;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "telefone_id")
  private List<TelefoneModel> telefones;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "email_id")
  private List<EmailModel> emails;

}
