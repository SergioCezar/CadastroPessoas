package br.com.meuprojeto.cadastropessoas.Pessoas.Controller.Service;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping("/boasvindas")
    public String boasVindas(){
        return "Primeira mensagem nessa rota";
    }

    //Adicionar pessoa
    @PostMapping("/criar")
    public PessoaModel criarPessoa(@RequestBody PessoaModel pessoa){
        return pessoaService.criarPessoa(pessoa);
    }

    //Mostrar todas as pessoas
    @GetMapping("/listar")
    public List<PessoaModel> listarPessoas(){
        return pessoaService.listarPessoas();
    }

    //Mostrar pessoa por ID
    @GetMapping("/listar/{id}")
    //PathVariable: usa quando a variavel inserida pelo user será parte do caminho. Ex: localhost:8080/listar/1
    public PessoaModel listarPessoasPorID(@PathVariable Long id){
        return pessoaService.listarPessoaPorId(id);
    }

    //Alterar dados das pessoas
    @PutMapping("/alterar/{id}")
    public PessoaModel alterarPessoasPorID(@PathVariable Long id, @RequestBody PessoaModel pessoaAtualizada){
        return pessoaService.atualizarPessoa(id, pessoaAtualizada);
    }

    //Deletar pessoa
    @DeleteMapping("/deletar/{id}")
    public void deletarPessoaPorID(@PathVariable Long id){
        pessoaService.deletarPessoaPorId(id);
    }
}
