package br.com.meuprojeto.cadastropessoas.Pessoas.Controller.Service;

import br.com.meuprojeto.cadastropessoas.Tarefas.TarefasModel;
import br.com.meuprojeto.cadastropessoas.Tarefas.TarefasRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final PessoaMapper pessoaMapper;
    private final TarefasRepository tarefaRepository;

    public PessoaService(PessoaRepository pessoaRepository, PessoaMapper pessoaMapper, TarefasRepository tarefaRepository) {
        this.pessoaRepository = pessoaRepository;
        this.pessoaMapper = pessoaMapper;
        this.tarefaRepository = tarefaRepository;
    }

    //Listar todas as pessoas usando JPA (transforma query de DB em métodos).
    public List<PessoaDTO> listarPessoas() {
        List<PessoaModel> pessoas = pessoaRepository.findAll();
        return pessoas.stream()
                .map(pessoaMapper::map)
                .collect(Collectors.toList());
    }

    //Lista todas as pessoas por ID
    public PessoaDTO listarPessoaPorId(Long id) {
        //Optional por que pode ser que não exista o ID procurado.
        Optional<PessoaModel> pessoaPorId = pessoaRepository.findById(id);
        return pessoaPorId.map(pessoaMapper::map).orElse(null);
    }

    //Criar uma nova pessoa (tudo que o usuário terá que fornecer para criar uma nova pessoa está em pessoa model).
    //Precisa re-serializar de JSON (entrada do usuario) para uma linha na tabela do BD
    public PessoaDTO criarPessoa(PessoaDTO pessoaDTO) {

        PessoaModel pessoa = pessoaMapper.map(pessoaDTO);

        if (pessoaDTO.getTarefas() != null && pessoaDTO.getTarefas().getId() != null) {
            TarefasModel tarefa = tarefaRepository
                    .findById(pessoaDTO.getTarefas().getId())
                    .orElse(null);

            pessoa.setTarefas(tarefa);
        } else {
            pessoa.setTarefas(null);
        }

        pessoa = pessoaRepository.save(pessoa);
        return pessoaMapper.map(pessoa);
    }

    //Deleta uma pessoa - TEM que ser VOID
    public void deletarPessoaPorId(Long id) {
        pessoaRepository.deleteById(id);
    }

    //Atualiza dados de uma pessoa
    //Precisa do ID da pessoa que é pra atualizar e, se existir, passa os detalhes da pessoa requerida.
    public PessoaDTO atualizarPessoa(Long id, PessoaDTO pessoaDTO) {
        Optional<PessoaModel> pessoaExistente = pessoaRepository.findById(id);
        if  (pessoaExistente.isPresent()) {
            PessoaModel pessoaAtualizada = pessoaMapper.map(pessoaDTO);
            pessoaAtualizada.setId(id);
            PessoaModel pessoaSalva = pessoaRepository.save(pessoaAtualizada);
            return pessoaMapper.map(pessoaSalva);
        }
        return null;
    }

    }
