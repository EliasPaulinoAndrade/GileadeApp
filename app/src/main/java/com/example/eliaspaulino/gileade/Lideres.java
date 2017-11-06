package com.example.eliaspaulino.gileade;

import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.eliaspaulino.gileade.R;
import com.example.eliaspaulino.gileade.adaptadores.LideresAdaptador;
import com.example.eliaspaulino.gileade.fragmentos.FragmentoLideres;
import com.example.eliaspaulino.gileade.models.Evento;
import com.example.eliaspaulino.gileade.models.Lider;
import com.example.eliaspaulino.gileade.models.Lideranca;
import com.example.eliaspaulino.gileade.singletons.LiderancaSingleton;
import com.example.eliaspaulino.gileade.utilitarios.Buscador;
import com.example.eliaspaulino.gileade.utilitarios.Mapeador;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lideres extends AppCompatActivity implements Response.ErrorListener, Response.Listener<String> {
    private TextView titulo;
    private Toolbar toolbar;
    private List<FragmentoLideres> fragmentoLidereslist;
    private RequestQueue queue;
    private ObjectMapper mapper;
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
        titulo.setText("LIDERES");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        queue = Volley.newRequestQueue(this);
        mapper = new Mapeador();

        fragmentoLidereslist = new ArrayList<>();

        buscarLiderancas();
    }
    private void buscarLiderancas(){
        imagemCarregamento.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new Buscador(Request.Method.GET, "liderancas", this, this );
        queue.add(stringRequest);
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
            ArrayList<Lideranca> responseLiderancas =  new ArrayList<>(Arrays.asList(mapper.readValue(response, Lideranca[].class)));
            separarLiderancas(responseLiderancas);
        } catch (IOException e) {
            mostrarErros();
            e.printStackTrace();
        }
    }
    private void mostrarErros(){
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Problemas De Conexão", Snackbar.LENGTH_INDEFINITE);
        snackbar.getView().setBackgroundColor(getResources().getColor(R.color.marrom));
        snackbar.setAction("recarregar", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarLiderancas();
            }
        })
                .setActionTextColor(getResources().getColor(R.color.azulclaro))
                .show();
    }
}
