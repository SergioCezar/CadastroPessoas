package br.com.meuprojeto.cadastropessoas.Tarefas;

import br.com.meuprojeto.cadastropessoas.Pessoas.Controller.Service.PessoaModel;
import br.com.meuprojeto.cadastropessoas.Pessoas.Controller.Service.PessoaRepository;
import br.com.meuprojeto.cadastropessoas.Tarefas.TarefasDTO;
import br.com.meuprojeto.cadastropessoas.Tarefas.TarefasModel;
import br.com.meuprojeto.cadastropessoas.Tarefas.TarefasRepository;
import br.com.meuprojeto.cadastropessoas.Tarefas.TarefasMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TarefasService {

    private final TarefasRepository tarefaRepository;
    private final TarefasMapper tarefaMapper;
    private final PessoaRepository pessoaRepository;

    public TarefasService(TarefasRepository tarefaRepository, TarefasMapper tarefaMapper, PessoaRepository pessoaRepository) {
        this.tarefaRepository = tarefaRepository;
        this.tarefaMapper = tarefaMapper;
        this.pessoaRepository = pessoaRepository;
    }

    // Listar todas as tarefas
    public List<TarefasDTO> listarTarefas() {
        List<TarefasModel> tarefas = tarefaRepository.findAll();
        return tarefas.stream()
                .map(tarefaMapper::map)
                .collect(Collectors.toList());
    }

    // Listar tarefa por ID
    public TarefasDTO listarTarefaPorId(Long id) {
        Optional<TarefasModel> tarefaPorId = tarefaRepository.findById(id);
        return tarefaPorId.map(tarefaMapper::map).orElse(null);
    }

    // Criar nova tarefa
    public TarefasDTO criarTarefa(TarefasDTO tarefaDTO) {
        TarefasModel tarefa = tarefaMapper.map(tarefaDTO);

        // Buscar pessoas pelos IDs
        if (tarefaDTO.getPessoasIds() != null) {
            List<PessoaModel> pessoas = tarefaDTO.getPessoasIds().stream()
                    .map(id -> pessoaRepository.findById(id).orElse(null))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            tarefa.setPessoa(pessoas);
        }

        tarefa = tarefaRepository.save(tarefa);
        return tarefaMapper.map(tarefa);
    }

    // Deletar tarefa
    public void deletarTarefaPorId(Long id) {
        tarefaRepository.deleteById(id);
    }

    // Atualizar tarefa
    public TarefasDTO atualizarTarefa(Long id, TarefasDTO tarefaDTO) {
        Optional<TarefasModel> tarefaExistente = tarefaRepository.findById(id);
        if (tarefaExistente.isPresent()) {
            TarefasModel tarefaAtualizada = tarefaMapper.map(tarefaDTO);
            tarefaAtualizada.setId(id);
            TarefasModel tarefaSalva = tarefaRepository.save(tarefaAtualizada);
            return tarefaMapper.map(tarefaSalva);
        }
        return null;
    }
}