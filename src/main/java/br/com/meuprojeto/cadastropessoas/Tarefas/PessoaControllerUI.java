package br.com.meuprojeto.cadastropessoas.Tarefas;

import br.com.meuprojeto.cadastropessoas.Pessoas.Controller.Service.PessoaDTO;
import br.com.meuprojeto.cadastropessoas.Pessoas.Controller.Service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/pessoas/ui")
public class PessoaControllerUI {

    private PessoaService pessoaService;

    public PessoaControllerUI(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping("/listar")
    public String listarPessoas(Model model){
        model.addAttribute("pessoas", pessoaService.listarPessoas());
        return "listarPessoas"; //Tem que retornar o nome da página html
    }

    //Delete vira get, pois após a deleção, deve-se voltar à listagem.
    @GetMapping("/deletar/{id}")
    public String deletarPessoaPorID(@PathVariable Long id){
        pessoaService.deletarPessoaPorId(id);
        return "redirect:/pessoas/ui/listar";
    }

    @GetMapping("/listar/{id}")
    public String listarPessoasPorID(@PathVariable Long id, Model model){

        if (pessoaService.listarPessoaPorId(id) != null){
            model.addAttribute("pessoa", pessoaService.listarPessoaPorId(id));
            return "detalhesPessoa";
        } else  {
            model.addAttribute("pessoa", "Pessoa não encontrada");
            return "listarPessoas";
        }
    }

    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionarPessoa(Model model) {
        model.addAttribute("pessoa", new PessoaDTO());
        return "adicionarPessoa";
    }

    @PostMapping("/salvar")
    public String salvarPessoa(@ModelAttribute PessoaDTO pessoa, RedirectAttributes redirectAttributes) {
        pessoaService.criarPessoa(pessoa);
        redirectAttributes.addFlashAttribute("msg", "Pessoa adicionado com sucesso!");
        return "redirect:/pessoas/ui/listar";
    }

}
