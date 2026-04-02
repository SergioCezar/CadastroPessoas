package br.com.meuprojeto.cadastropessoas.Pessoas.Controller.Service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    //Listar todas as pessoas usando JPA (transforma query de DB em métodos).
    public List<PessoaModel> listarPessoas() {
        return pessoaRepository.findAll();
    }

    //Lista todas as pessoas por ID
    public PessoaModel listarPessoaPorId(Long id) {
        //Optional por que pode ser que não exista o ID procurado.
        Optional<PessoaModel> pessoaPorId = pessoaRepository.findById(id);
        return pessoaPorId.orElse(null);
    }

    //Criar uma nova pessoa (tudo que o usuário terá que fornecer para criar uma nova pessoa está em pessoa model).
    //Precisa re-serializar de JSON (entrada do usuario) para uma linha na tabela do BD
    public PessoaModel criarPessoa(PessoaModel pessoa) {
        return pessoaRepository.save(pessoa);
    }
}
