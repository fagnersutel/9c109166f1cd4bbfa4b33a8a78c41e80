package com.example.sobrecargademtodos;

public class Pessoa {
    private  String nome;
    private  int idade;

    public void exibirDados(String nome){
        System.out.println("Exibir apenas nome: " + nome);

    }
    //Sobrecarga de métodos
    public void exibirDados(String nome, int idade){
        System.out.println("Exibir apenas nome: " + nome + " Idade: " + idade);
    }

}
