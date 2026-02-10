package com.bootcamp;

import com.bootcamp.model.Usuario;
import com.bootcamp.service.UsuarioService;
import java.util.Scanner;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        UsuarioService service = new UsuarioService();
        Scanner sc = new Scanner(System.in);
        int opcao = 0;

        while(opcao!=6){
            System.out.println("\nXX GERENCIAMENTO DE USUÁRIOS XX");
            System.out.println("-- Escolha uma opção: --");
            System.out.println("1. Criar usuário");
            System.out.println("2. Listar todos os usuários");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Atualizar usuário");
            System.out.println("5. Remover usuario");
            System.out.println("0. Sair");

            try{
                String input = sc.nextLine();
                opcao = Integer.parseInt(input);

                switch (opcao){
                    case 1 ->{
                        System.out.println("\nDigite o nome: ");
                        String nome = sc.nextLine();
                        System.out.println("Digite o email: ");
                        String email = sc.nextLine();
                        service.criarUsuario(nome, email);
                        System.out.println("Usuario criado!");
                    }
                    case 2 ->{
                        System.out.println("\nXX LISTA DE USUÁRIOS XX");
                        List<Usuario> usuarios = service.listarUsuarios();
                        if (usuarios.isEmpty()){
                            System.out.println("Nenhum usuário cadastrado.");
                        } else{

                        }
                    }
                }
            }
        }
    }
}