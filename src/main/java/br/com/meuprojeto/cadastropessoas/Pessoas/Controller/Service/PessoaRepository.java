package br.com.meuprojeto.cadastropessoas.Pessoas.Controller.Service;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<PessoaModel, Long> {
}
