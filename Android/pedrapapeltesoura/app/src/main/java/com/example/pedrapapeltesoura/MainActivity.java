package com.example.pedrapapeltesoura;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void selecionarPapel(View view){
        this.opcaoSelecionada("Papel");

    }

    public void selecionarPedra(View view){
        this.opcaoSelecionada("Pedra");
    }

    public void selecionarTesoura(View view){
        this.opcaoSelecionada("Tesoura");
    }

    public void opcaoSelecionada(String escolhaUsario){
        //System.out.println(escolhaUsario);

        ImageView imageResultado = (ImageView) findViewById(R.id.imageResultado);
        TextView textoResultado = (TextView) findViewById(R.id.textResultado);
        //opcoes
        String[] opcoes = {"Papel", "Pedra", "Tesoura"};
        //gerar numero randomico como selecao da maquina
        int numero = new Random().nextInt(3);
        String escolhaApp = opcoes[numero];

        System.out.println(escolhaApp);
        switch (escolhaApp){
            case "Papel":
                imageResultado.setImageResource(R.drawable.papel);
                break;
            case "Pedra":
                imageResultado.setImageResource(R.drawable.pedra);
                break;
            case "Tesoura":
                imageResultado.setImageResource(R.drawable.tesoura);
                break;
        }

        if((escolhaApp=="Pedra" && escolhaUsario=="Tesoura") ||
                (escolhaApp=="Papel" && escolhaUsario=="Pedra") ||
                (escolhaApp=="Tesoura" && escolhaUsario=="Papel")){
            textoResultado.setText("Voçê Perdeu :( ");
        }else if((escolhaUsario=="Pedra" && escolhaApp=="Tesoura") ||
                (escolhaUsario=="Papel" && escolhaApp=="Pedra") ||
                (escolhaUsario=="Tesoura" && escolhaApp=="Papel")){
            textoResultado.setText("Voçê Ganhou :) ");
        }else{
            textoResultado.setText("Empatamos :| ");
        }


    }
}
