package br.com.meuprojeto.cadastropessoas.Pessoas.Controller.Service;

import org.springframework.stereotype.Component;

//Relaciona o DTO com o Model (id do DTO --> id do Model) e vice-versa.
@Component
public class PessoaMapper {

    public PessoaModel map(PessoaDTO pessoaDTO) {

        PessoaModel pessoa = new PessoaModel();
        pessoa.setId(pessoaDTO.getId());
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setEmail(pessoaDTO.getEmail());
        pessoa.setIdade(pessoaDTO.getIdade());
        pessoa.setImgUrl(pessoaDTO.getImgUrl());
        pessoa.setTelefone(pessoaDTO.getTelefone());
        pessoa.setTarefas(pessoaDTO.getTarefas());

        return pessoa;
    }

    public PessoaDTO map(PessoaModel pessoaModel) {

        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setId(pessoaModel.getId());
        pessoaDTO.setNome(pessoaModel.getNome());
        pessoaDTO.setEmail(pessoaModel.getEmail());
        pessoaDTO.setIdade(pessoaModel.getIdade());
        pessoaDTO.setImgUrl(pessoaModel.getImgUrl());
        pessoaDTO.setTelefone(pessoaModel.getTelefone());
        pessoaDTO.setTarefas(pessoaModel.getTarefas());

        return pessoaDTO;
    }

}
