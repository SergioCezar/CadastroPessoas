package br.com.meuprojeto.cadastropessoas.Pessoas.Controller.Service;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class PessoaController {

    @GetMapping("/boasvindas")
    public String boasVindas(){
        return "Primeira mensagem nessa rota";
    }

    //Adicionar pessoa
    @PostMapping("/criar")
    public String criarPessoa(){
        return "Pessoa criada com sucesso";
    }

    //Mostrar todas as pessoas
    @GetMapping("/todos")
    public String mostrarTodasAsPessoas(){
        return "Mostrar pessoas";
    }

    //Mostrar pessoa por ID
    @GetMapping("/todosID")
    public String mostrarTodasAsPessoasPorID(){
        return "Mostrar pessoas por id";
    }

    //Alterar dados das pessoas
    @PutMapping("/alterarID")
    public String alterarPessoasPorID(){
        return "Alterar pessoas por id";
    }

    //Deletar pessoa
    @DeleteMapping("/deletarID")
    public String deletarPessoaPorID(){
        return "Ninja deletado por ID ";
    }
}
