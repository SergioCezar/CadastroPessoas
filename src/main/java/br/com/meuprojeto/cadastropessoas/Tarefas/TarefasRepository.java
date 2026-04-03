package br.com.meuprojeto.cadastropessoas.Tarefas;

import org.springframework.data.jpa.repository.JpaRepository;

//Long é o tipo de ID em TarefasModel, que é a classe que queremos que o JPA escaneie.
public interface TarefasRepository extends JpaRepository<TarefasModel, Long> {
}
