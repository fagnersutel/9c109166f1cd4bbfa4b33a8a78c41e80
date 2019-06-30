package com.example.sobrecargademtodos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Pessoa pessoa = new Pessoa();
        ContaBancaria conta = new ContaBancaria();


        /*
        ##### INTERFACE ####
        *  Interface é o contrato que é assumido pela classe  que implementa um método
        *  Interface é um recurso útil quando possuimos muitos objetos (classes) que
        *  podem possuir a mesma ação, mas as executando de maneiras diferentes
        * */
        Obama presidente = new Obama();
        presidente.mensagem();
    }

}
