package com.example.eliaspaulino.gileade;

import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.eliaspaulino.gileade.fragmentos.FragmentoLideres;
import com.example.eliaspaulino.gileade.models.Lideranca;
import com.example.eliaspaulino.gileade.singletons.LiderancaSingleton;
import com.example.eliaspaulino.gileade.utilitarios.Buscador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Lideres extends AppCompatActivity implements Response.ErrorListener, Response.Listener<String> {
    private static final String SERVER_END_POINT = "liderancas";

    private Buscador<Lideranca> liderancaBuscador;
    private TextView titulo;
    private Toolbar toolbar;
    private List<FragmentoLideres> fragmentoLidereslist;
    private View imagemCarregamento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lideres);

        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.azulescuro));
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.voltaricon));
        imagemCarregamento = findViewById(R.id.imagemCarregamento);
        titulo = (TextView) findViewById(R.id.main_toolbar_title);
        titulo.setText(getResources().getString(R.string.titulo_lideres_actionbar));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        liderancaBuscador = new Buscador<>(this, SERVER_END_POINT);

        fragmentoLidereslist = new ArrayList<>();

        liderancaBuscador.find(this, this);

    }
    private void adicionarFragmento(Lideranca lideranca){
        LiderancaSingleton.getInstancia().getLiderancas().put(lideranca.getId(), lideranca);
        FragmentoLideres fragmentoLideres = new FragmentoLideres();
        Bundle bundle = new Bundle();
        bundle.putInt("lideranca_id", lideranca.getId());
        fragmentoLideres.setArguments(bundle);
        fragmentoLidereslist.add(fragmentoLideres);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.listafragmentlideres, fragmentoLideres, Integer.toString(fragmentoLidereslist.size()));
        transaction.commit();
    }

    private void separarLiderancas(ArrayList<Lideranca> liderancas){

        for(Lideranca lideranca : liderancas){
            adicionarFragmento(lideranca);
        }
    }
    @Override
    public void onErrorResponse(VolleyError error) {
        imagemCarregamento.setVisibility(View.GONE);
        mostrarErros();
    }

    @Override
    public void onResponse(String response) {
        imagemCarregamento.setVisibility(View.GONE);
        try {
            Log.d("TESTANDO", "onResponse: " + response);
            ArrayList<Lideranca> responseLiderancas =  liderancaBuscador.translate(response, new Lideranca[0]);
            separarLiderancas(responseLiderancas);
        } catch (IOException e) {
            mostrarErros();
            e.printStackTrace();
        }
    }
    private void mostrarErros(){
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), getResources().getString(R.string.conexao_erro), Snackbar.LENGTH_INDEFINITE);
        snackbar.getView().setBackgroundColor(getResources().getColor(R.color.marrom));
        snackbar.setAction(getResources().getString(R.string.conexao_erro_action), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                liderancaBuscador.find();
            }
        })
        .setActionTextColor(getResources().getColor(R.color.azulclaro))
        .show();
    }
}
