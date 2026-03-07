package br.com.meuprojeto.cadastropessoas.Tarefas;

import br.com.meuprojeto.cadastropessoas.Pessoas.Controller.Service.PessoaModel;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "tb_tarefas")
public class MissoesModel {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String dificuldade;

    //Uma tarefa pode ter várias pessoas
    @OneToMany(mappedBy = "tarefas")
    private List<PessoaModel> pessoa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }

    public List<PessoaModel> getPessoa() {
        return pessoa;
    }

    public void setPessoa(List<PessoaModel> pessoa) {
        this.pessoa = pessoa;
    }
}
