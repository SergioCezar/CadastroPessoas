package br.com.meuprojeto.cadastropessoas.Tarefas;

import br.com.meuprojeto.cadastropessoas.Pessoas.Controller.Service.PessoaModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table (name = "tb_tarefas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TarefasModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String dificuldade;

    //Uma tarefa pode ter várias pessoas
    @OneToMany(mappedBy = "tarefas")
    //Evita loop de serialização
    @JsonIgnore
    private List<PessoaModel> pessoa;
}
