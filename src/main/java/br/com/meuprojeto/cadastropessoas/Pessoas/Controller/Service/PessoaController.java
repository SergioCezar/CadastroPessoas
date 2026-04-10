package br.com.meuprojeto.cadastropessoas.Pessoas.Controller.Service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
@Tag(name = "Pessoas", description = "API responsável pelo gerenciamento de pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping("/boasvindas")
    @Operation(
            summary = "Mensagem de boas-vindas",
            description = "Endpoint simples para verificar se a API está funcionando corretamente."
    )
    public String boasVindas(){
        return "Primeira mensagem nessa rota";
    }

    //Adicionar pessoa
    @PostMapping("/criar")
    @Operation(
            summary = "Cria uma nova pessoa",
            description = "Recebe um objeto PessoaDTO e salva no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description ="Pessoa criada com sucesso!"),
            @ApiResponse(responseCode = "400", description ="Erro na criação da pessoa")
    })
    public ResponseEntity<String> criarPessoa(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados da pessoa a ser criada",
                    required = true
            )
            @RequestBody PessoaDTO pessoa
    ){
        PessoaDTO novaPessoa = pessoaService.criarPessoa(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Pessoa criada com sucesso: " + novaPessoa.getNome() + "de ID: " + novaPessoa.getId());
    }

    //Mostrar todas as pessoas
    @GetMapping("/listar")
    @Operation(
            summary = "Lista todas as pessoas",
            description = "Retorna uma lista com todas as pessoas cadastradas no sistema."
    )
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public ResponseEntity<List<PessoaDTO>> listarPessoas(){
        return ResponseEntity.ok(pessoaService.listarPessoas());
    }

    //Mostrar pessoa por ID
    @GetMapping("/listar/{id}")
    @Operation(
            summary = "Busca pessoa por ID",
            description = "Retorna os dados de uma pessoa específica com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description ="Pessoa encontrada com sucesso!"),
            @ApiResponse(responseCode = "404", description ="Pessoa não encontrada.")
    })
    public ResponseEntity<?> listarPessoasPorID(
            @Parameter(
                    description = "ID da pessoa a ser buscada",
                    example = "1",
                    required = true
            )
            @PathVariable Long id){

        if (pessoaService.listarPessoaPorId(id) != null){
            return ResponseEntity.ok(pessoaService.listarPessoaPorId(id));
        } else  {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("A pessoa com o ID " + id + " não foi encontrada!");
        }
    }

    //Alterar dados das pessoas
    @PutMapping("/alterar/{id}")
    @Operation(
            summary = "Atualiza uma pessoa",
            description = "Atualiza os dados de uma pessoa existente com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description ="Pessoa alterada com sucesso!"),
            @ApiResponse(responseCode = "404", description ="Pessoa não encontrada, não foi possível alterar.")
    })
    public ResponseEntity<?> alterarPessoasPorID(
            @Parameter(
                    description = "ID da pessoa que será atualizada",
                    example = "1",
                    required = true
            )
            @PathVariable Long id,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Novos dados da pessoa",
                    required = true
            )
            @RequestBody PessoaDTO pessoaAtualizada){

        if (pessoaService.listarPessoaPorId(id) != null){
            return ResponseEntity.ok(pessoaService.atualizarPessoa(id, pessoaAtualizada));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("A pessoa com o ID " + id + " não foi encontrada!");
        }
    }

    //Deletar pessoa
    @DeleteMapping("/deletar/{id}")
    @Operation(
            summary = "Remove uma pessoa",
            description = "Deleta uma pessoa do sistema com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description ="Pessoa deletada com sucesso!"),
            @ApiResponse(responseCode = "404", description ="Pessoa não encontrada.")
    })
    public ResponseEntity<String> deletarPessoaPorID(
            @Parameter(
                    description = "ID da pessoa que será deletada",
                    example = "1",
                    required = true
            )
            @PathVariable Long id){

        if(pessoaService.listarPessoaPorId(id) != null) {
            pessoaService.deletarPessoaPorId(id);
            return ResponseEntity.ok("Pessoa com ID " + id + " deletada com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("A pessoa com o ID " + id + " não foi encontrada!");
        }
    }

    @PatchMapping("/alterar/{id}")
    @Operation(summary = "Atualiza parcialmente uma pessoa")
    public ResponseEntity<?> atualizarParcialPessoa(
            @PathVariable Long id,
            @RequestBody PessoaDTO pessoaDTO) {

        PessoaDTO pessoaAtualizada = pessoaService.atualizarParcialPessoa(id, pessoaDTO);

        if (pessoaAtualizada != null) {
            return ResponseEntity.ok(pessoaAtualizada);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Pessoa não encontrada");
        }
    }
}