package br.com.meuprojeto.cadastropessoas.Tarefas;

import br.com.meuprojeto.cadastropessoas.Tarefas.TarefasDTO;
import br.com.meuprojeto.cadastropessoas.Tarefas.TarefasService;
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
@RequestMapping("/tarefas")
@Tag(name = "Tarefas", description = "API responsável pelo gerenciamento de tarefas")
public class TarefasController {

    private final TarefasService tarefaService;

    public TarefasController(TarefasService tarefaService) {
        this.tarefaService = tarefaService;
    }

    // Boas-vindas
    @GetMapping("/boasvindas")
    @Operation(
            summary = "Mensagem de boas-vindas",
            description = "Endpoint simples para verificar se a API está funcionando."
    )
    public String boasVindas(){
        return "Primeira mensagem na rota de tarefas";
    }

    // Criar tarefa
    @PostMapping("/criar")
    @Operation(
            summary = "Cria uma nova tarefa",
            description = "Recebe um objeto TarefasDTO e salva no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description ="Tarefa criada com sucesso!"),
            @ApiResponse(responseCode = "400", description ="Erro na criação da tarefa")
    })
    public ResponseEntity<String> criarTarefa(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados da tarefa a ser criada",
                    required = true
            )
            @RequestBody TarefasDTO tarefa
    ){
        TarefasDTO novaTarefa = tarefaService.criarTarefa(tarefa);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Tarefa criada com sucesso: " + novaTarefa.getNome() +
                        " de ID: " + novaTarefa.getId());
    }

    // Listar todas
    @GetMapping("/listar")
    @Operation(
            summary = "Lista todas as tarefas",
            description = "Retorna uma lista com todas as tarefas cadastradas."
    )
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public ResponseEntity<List<TarefasDTO>> listarTarefas(){
        return ResponseEntity.ok(tarefaService.listarTarefas());
    }

    // Buscar por ID
    @GetMapping("/listar/{id}")
    @Operation(
            summary = "Busca tarefa por ID",
            description = "Retorna os dados de uma tarefa específica com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description ="Tarefa encontrada com sucesso!"),
            @ApiResponse(responseCode = "404", description ="Tarefa não encontrada.")
    })
    public ResponseEntity<?> listarTarefaPorID(
            @Parameter(
                    description = "ID da tarefa a ser buscada",
                    example = "1",
                    required = true
            )
            @PathVariable Long id){

        if (tarefaService.listarTarefaPorId(id) != null){
            return ResponseEntity.ok(tarefaService.listarTarefaPorId(id));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("A tarefa com o ID " + id + " não foi encontrada!");
        }
    }

    // Atualizar tarefa
    @PutMapping("/alterar/{id}")
    @Operation(
            summary = "Atualiza uma tarefa",
            description = "Atualiza os dados de uma tarefa existente com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description ="Tarefa alterada com sucesso!"),
            @ApiResponse(responseCode = "404", description ="Tarefa não encontrada.")
    })
    public ResponseEntity<?> alterarTarefaPorID(
            @Parameter(
                    description = "ID da tarefa que será atualizada",
                    example = "1",
                    required = true
            )
            @PathVariable Long id,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Novos dados da tarefa",
                    required = true
            )
            @RequestBody TarefasDTO tarefaAtualizada){

        if (tarefaService.listarTarefaPorId(id) != null){
            return ResponseEntity.ok(tarefaService.atualizarTarefa(id, tarefaAtualizada));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("A tarefa com o ID " + id + " não foi encontrada!");
        }
    }

    @PatchMapping("/alterar/{id}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long id,
                                              @RequestBody TarefasDTO dto) {

        TarefasDTO tarefa = tarefaService.atualizarParcialTarefa(id, dto);

        if (tarefa != null) return ResponseEntity.ok(tarefa);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrada");
    }


    // Deletar tarefa
    @DeleteMapping("/deletar/{id}")
    @Operation(
            summary = "Remove uma tarefa",
            description = "Deleta uma tarefa do sistema com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description ="Tarefa deletada com sucesso!"),
            @ApiResponse(responseCode = "404", description ="Tarefa não encontrada.")
    })
    public ResponseEntity<String> deletarTarefaPorID(
            @Parameter(
                    description = "ID da tarefa que será deletada",
                    example = "1",
                    required = true
            )
            @PathVariable Long id){

        if(tarefaService.listarTarefaPorId(id) != null) {
            tarefaService.deletarTarefaPorId(id);
            return ResponseEntity.ok("Tarefa com ID " + id + " deletada com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("A tarefa com o ID " + id + " não foi encontrada!");
        }
    }
}