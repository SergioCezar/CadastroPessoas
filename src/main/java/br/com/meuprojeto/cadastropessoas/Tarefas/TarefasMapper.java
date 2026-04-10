package br.com.meuprojeto.cadastropessoas.Tarefas;

import br.com.meuprojeto.cadastropessoas.Tarefas.TarefasDTO;
import br.com.meuprojeto.cadastropessoas.Tarefas.TarefasModel;
import org.springframework.stereotype.Component;

// Relaciona o DTO com o Model e vice-versa
@Component
public class TarefasMapper {

    public TarefasModel map(TarefasDTO tarefaDTO) {

        TarefasModel tarefa = new TarefasModel();
        tarefa.setId(tarefaDTO.getId());
        tarefa.setNome(tarefaDTO.getNome());
        tarefa.setDificuldade(tarefaDTO.getDificuldade());
        tarefa.setPessoa(tarefaDTO.getPessoa());

        return tarefa;
    }

    public TarefasDTO map(TarefasModel tarefaModel) {

        TarefasDTO tarefaDTO = new TarefasDTO();
        tarefaDTO.setId(tarefaModel.getId());
        tarefaDTO.setNome(tarefaModel.getNome());
        tarefaDTO.setDificuldade(tarefaModel.getDificuldade());
        tarefaDTO.setPessoa(tarefaModel.getPessoa());

        return tarefaDTO;
    }
}