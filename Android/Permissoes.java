package br.com.sutel.mymaps;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Permissoes {
    public static boolean validarPermissoes(String[] permissoes, Activity activity, int requestCode){

        if (Build.VERSION.SDK_INT >= 23){
            List<String> listaPermissoes = new ArrayList<>();

            /*
            * Percorre as permissoes paddasas, verificando se já há permissão ou não
            * */

            //ValidarPermissoes
            for (String permissao:permissoes){
                Boolean temPermissao = ContextCompat.checkSelfPermission(activity, permissao) == PackageManager.PERMISSION_GRANTED;
                if (!temPermissao) listaPermissoes.add(permissao);
            }

            //Caso a lista esteja vazia, não é necessário solicitar permissão
            if (listaPermissoes.isEmpty() ) return true;

            //criamos um array de strings
            String[] novasPErmissoes = new  String[listaPermissoes.size()];
            listaPermissoes.toArray(novasPErmissoes);
            //Solicita permissao
            ActivityCompat.requestPermissions(activity, novasPErmissoes, requestCode);

        }

        return  true;
    }
}