package br.com.meuprojeto.cadastropessoas.Tarefas;

import br.com.meuprojeto.cadastropessoas.Pessoas.Controller.Service.PessoaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table (name = "tb_tarefas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MissoesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String dificuldade;

    //Uma tarefa pode ter várias pessoas
    @OneToMany(mappedBy = "tarefas")
    private List<PessoaModel> pessoa;
}
