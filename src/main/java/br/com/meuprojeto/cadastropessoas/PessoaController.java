package br.com.meuprojeto.cadastropessoas;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class PessoaController {

    @GetMapping("/boasvindas")
    public String boasVindas(){
        return "Primeira mensagem nessa rota";
    }

}
