package br.com.meuprojeto.cadastropessoas.Pessoas.Controller.Service;

import br.com.meuprojeto.cadastropessoas.Tarefas.MissoesModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //Transforma uma classe em uma entidade do BD
@Table(name = "tb_cadastro")
//Cria um allargs constructor e o atualiza automaticamente caso haja adicao de novos argumentos
@AllArgsConstructor
@NoArgsConstructor
//Cria todos os getters e setters
@Data
public class PessoaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    private int idade;

    //uma pessoa tem uma unica tarefa
    @ManyToOne
    @JoinColumn(name = "tarefas_id") //Chave estrangeira
    private MissoesModel tarefas;



}
