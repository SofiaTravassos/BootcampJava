package com.bootcamp.model;

public class Funcionario extends Usuario{
    private String cargo;

    public Funcionario(Long id, String nome, String email, String cargo) {
        super(id, nome, email);
        this.cargo = cargo;
    }

    public Funcionario(String nome, String email, String cargo) {
        super(nome, email);
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    @Override
    public String toString() {
        return super.toString() + " | Cargo: " + cargo;
    }
}
