package br.com.meuprojeto.cadastropessoas.Pessoas.Controller.Service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> criarPessoa(@RequestBody PessoaDTO pessoa){
        PessoaDTO novaPessoa = pessoaService.criarPessoa(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Pessoa criada com sucesso: " + novaPessoa.getNome() + "de ID: " + novaPessoa.getId());
    }

    //Mostrar todas as pessoas
    @GetMapping("/listar")
    public ResponseEntity<List<PessoaDTO>> listarPessoas(){
        return ResponseEntity.ok(pessoaService.listarPessoas());
    }

    //Mostrar pessoa por ID
    @GetMapping("/listar/{id}")
    //PathVariable: usa quando a variavel inserida pelo user será parte do caminho. Ex: localhost:8080/listar/1
    public ResponseEntity<?> listarPessoasPorID(@PathVariable Long id){

        if (pessoaService.listarPessoaPorId(id) != null){
           return ResponseEntity.ok(pessoaService.listarPessoaPorId(id));
        } else  {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("A pessoa com o ID " + id + " não foi encontrada!");
        }
    }

    //Alterar dados das pessoas
    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarPessoasPorID(@PathVariable Long id, @RequestBody PessoaDTO pessoaAtualizada){

        if (pessoaService.listarPessoaPorId(id) != null){
            return ResponseEntity.ok(pessoaService.atualizarPessoa(id, pessoaAtualizada));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("A pessoa com o ID " + id + " não foi encontrada!");
        }
    }

    //Deletar pessoa
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarPessoaPorID(@PathVariable Long id){

        if(pessoaService.listarPessoaPorId(id) != null) {
            pessoaService.deletarPessoaPorId(id);
            return ResponseEntity.ok("Pessoa com ID " + id + " deletada com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("A pessoa com o ID " + id + " não foi encontrada!");
        }
    }
}
