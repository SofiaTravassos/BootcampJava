package com.bootcamp.controller;

import com.bootcamp.service.UsuarioService;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", service.listarUsuarios());
        return "index";
    }

    @GetMapping("/novo")
    public String mostrarFormulario() {
        return "formulario";
    }

    @PostMapping("/salvar")
    public String salvarUsuario(
            @RequestParam String nome, 
            @RequestParam String email, 
            @RequestParam String tipo, 
            @RequestParam(required = false) String cargo) {
        
        if ("FUNCIONARIO".equals(tipo)) {
            service.criarFuncionario(nome, email, cargo);
        } else {
            service.criarUsuario(nome, email);
        }
        return "redirect:/";
    }

    @GetMapping("/deletar")
    public String deletarUsuario(@RequestParam Long id) {
        service.removerUsuario(id);
        return "redirect:/";
    }

    @GetMapping("/buscar")
    public String buscarUsuario(@RequestParam Long id, Model model) {
        try{
            model.addAttribute("usuarios", List.of(service.buscarUsuario(id)));
        } catch (RuntimeException e){
            model.addAttribute("error", e.getMessage());
            model.addAttribute("usuarios", List.of());
        }
        return "index";
    }

    @GetMapping("/editar")
    public String mostrarTelaEdicao(@RequestParam Long id, Model model) {
        model.addAttribute("usuario", service.buscarUsuario(id));
        return "editar";
    }

    @PostMapping("/atualizar")
    public String atualizarUsuario(@RequestParam Long id, @RequestParam String nome, @RequestParam String email){
        service.atualizarUsuario(id, nome, email);
        return "redirect:/";
    }
}