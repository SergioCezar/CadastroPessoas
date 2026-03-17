package br.com.meuprojeto.cadastropessoas.Tarefas;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tarefas")
public class TarefasController {

    //GET - MANDAR UMA REQUISIÇÃO PARA MOSTRAR AS TAREFAS
    @GetMapping("/listar")
    public String listarTarefas(){
        return "Tarefas listadas com sucesso";
    }

    //POST - MANDAR UMA REQUISIÇÃO PARA CRIAR AS TAREFAS
    @PostMapping("/criar")
    public String criarTarefa() {
        return "Tarefa criada com sucesso";
    }

    //PUT - MANDAR UMA REQUISIÇÃO PARA ALTERAR AS TAREFAS
    @PutMapping("/alterar")
    public String alterarTarefa() {
        return "Tarefa alterada com sucesso";
    }

    //DELETE - MANDAR UMA REQUISIÇÃO PARA DELETAR AS TAREFAS
    @DeleteMapping("/deletar")
    public String deletarTarefa() {
        return "Tarefa deletada com sucesso";
    }
}
