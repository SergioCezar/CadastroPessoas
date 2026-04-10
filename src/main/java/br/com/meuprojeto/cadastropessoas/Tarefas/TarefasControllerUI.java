package br.com.meuprojeto.cadastropessoas.Tarefas;

import br.com.meuprojeto.cadastropessoas.Tarefas.TarefasDTO;
import br.com.meuprojeto.cadastropessoas.Tarefas.TarefasService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tarefas/ui")
public class TarefasControllerUI {

    private TarefasService tarefaService;

    public TarefasControllerUI(TarefasService tarefaService) {
        this.tarefaService = tarefaService;
    }

    // Listar tarefas
    @GetMapping("/listar")
    public String listarTarefas(Model model){
        model.addAttribute("tarefas", tarefaService.listarTarefas());
        return "listarTarefas"; // nome do HTML
    }

    // Deletar (GET para redirecionar depois)
    @GetMapping("/deletar/{id}")
    public String deletarTarefaPorID(@PathVariable Long id){
        tarefaService.deletarTarefaPorId(id);
        return "redirect:/tarefas/ui/listar";
    }

    // Detalhes por ID
    @GetMapping("/listar/{id}")
    public String listarTarefaPorID(@PathVariable Long id, Model model){

        if (tarefaService.listarTarefaPorId(id) != null){
            model.addAttribute("tarefa", tarefaService.listarTarefaPorId(id));
            return "detalhesTarefa";
        } else  {
            model.addAttribute("tarefa", "Tarefa não encontrada");
            return "listarTarefas";
        }
    }

    // Formulário de adicionar
    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionarTarefa(Model model) {
        model.addAttribute("tarefa", new TarefasDTO());
        return "adicionarTarefa";
    }

    // Salvar tarefa
    @PostMapping("/salvar")
    public String salvarTarefa(@ModelAttribute TarefasDTO tarefa, RedirectAttributes redirectAttributes) {
        tarefaService.criarTarefa(tarefa);
        redirectAttributes.addFlashAttribute("msg", "Tarefa adicionada com sucesso!");
        return "redirect:/tarefas/ui/listar";
    }
}