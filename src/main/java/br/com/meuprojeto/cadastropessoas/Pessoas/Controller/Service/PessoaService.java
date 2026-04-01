package br.com.meuprojeto.cadastropessoas.Pessoas.Controller.Service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    private PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    //Listar todas as pessoas usando JPA (transforma query de DB em métodos)
    public List<PessoaModel> listarPessoas() {
        return pessoaRepository.findAll();
    }
}
