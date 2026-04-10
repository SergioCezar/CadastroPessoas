package br.com.meuprojeto.cadastropessoas.Pessoas.Controller.Service;

import br.com.meuprojeto.cadastropessoas.Tarefas.TarefasModel;
import br.com.meuprojeto.cadastropessoas.Tarefas.TarefasService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pessoas/ui")
public class PessoaControllerUI {

    private final PessoaService pessoaService;
    private final TarefasService tarefaService;

    public PessoaControllerUI(PessoaService pessoaService, TarefasService tarefaService) {
        this.pessoaService = pessoaService;
        this.tarefaService = tarefaService;
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
        PessoaDTO pessoa = new PessoaDTO();
        pessoa.setTarefas(new TarefasModel());

        model.addAttribute("pessoa", pessoa);
        model.addAttribute("tarefas", tarefaService.listarTarefas());
        return "adicionarPessoa";
    }

    @PostMapping("/salvar")
    public String salvarPessoa(@ModelAttribute PessoaDTO pessoa, RedirectAttributes redirectAttributes) {
        pessoaService.criarPessoa(pessoa);
        redirectAttributes.addFlashAttribute("msg", "Pessoa adicionada com sucesso!");
        return "redirect:/pessoas/ui/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarPessoa(@PathVariable Long id, Model model) {

        PessoaDTO pessoa = pessoaService.listarPessoaPorId(id);

        model.addAttribute("pessoa", pessoa);
        model.addAttribute("tarefas", tarefaService.listarTarefas());

        return "editarPessoa";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarPessoa(@PathVariable Long id,
                                  @ModelAttribute PessoaDTO pessoa) {

        pessoaService.atualizarPessoa(id, pessoa);

        return "redirect:/pessoas/ui/listar";
    }

}
