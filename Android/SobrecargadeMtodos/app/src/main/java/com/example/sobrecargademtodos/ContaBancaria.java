package com.example.sobrecargademtodos;

public class ContaBancaria {
    private int numeroConta;
    private double saldo;


    public ContaBancaria(){
        System.out.println("Construtor instanciado");
    }
    //Para haver sobrecarga de métodos cada método deve possuir uma unica assinatura
    //Assinatura dé metodos = {nome do método + parâmetros}
    public ContaBancaria(int numeroConta){
        System.out.println("Construtor instanciado");
    }
    //terceiro construtor com uma assinatura pessindo dois parametros
    public ContaBancaria(int numeroConta, double saldo){
        System.out.println("Construtor instanciado");
    }


}
