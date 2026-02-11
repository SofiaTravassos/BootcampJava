package com.bootcamp;

import com.bootcamp.model.Usuario;
import com.bootcamp.service.UsuarioService;
import java.util.Scanner;
import java.util.List;

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
            System.out.println("6. Sair");

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
                            usuarios.forEach(System.out::println);
                        }
                    }
                    case 3 -> {
                        System.out.print("\nDigite o ID: ");
                        Long id = Long.parseLong(sc.nextLine());
                        Usuario u = service.buscarUsuario(id);
                        System.out.println("Encontrado: " + u);
                    }
                    case 4 -> {
                        System.out.print("\nID do usuário a atualizar: ");
                        Long id = Long.parseLong(sc.nextLine());
                        
                        System.out.print("Novo Nome (Enter para manter atual): ");
                        String nome = sc.nextLine();
                        
                        System.out.print("Novo Email (Enter para manter atual): ");
                        String email = sc.nextLine();
                        
                        service.atualizarUsuario(id, nome.isEmpty() ? null : nome, email.isEmpty() ? null : email);
                        System.out.println("Usuário atualizado com sucesso!");
                    }
                    case 5 -> {
                        System.out.print("\nID do usuário a remover: ");
                        Long id = Long.parseLong(sc.nextLine());
                        service.removerUsuario(id);
                        System.out.println("Usuário removido com sucesso!");
                    }
                    case 6 -> System.out.println("\nEncerrando o sistema...");
                    default -> System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, digite um número válido.");
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        sc.close();
    }
}