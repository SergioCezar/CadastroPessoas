package br.com.meuprojeto.cadastropessoas.Pessoas.Controller.Service;

import br.com.meuprojeto.cadastropessoas.Tarefas.TarefasModel;
import br.com.meuprojeto.cadastropessoas.Tarefas.TarefasModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity //Transforma uma classe em uma entidade do BD
@Table(name = "tb_cadastro")
//Cria um allargs constructor e o atualiza automaticamente caso haja adicao de novos argumentos
@AllArgsConstructor
@NoArgsConstructor
//Cria todos os getters e setters
@Data
//Para evitar loop infinito
@ToString(exclude = "tarefas")
public class PessoaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(unique = true)
    private String email;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name= "telefone")
    private String telefone;

    @Column(name = "idade")
    private int idade;

    //uma pessoa tem uma unica tarefa
    @ManyToOne
    @JoinColumn(name = "tarefas_id") //Chave estrangeira
    private TarefasModel tarefas;



}
