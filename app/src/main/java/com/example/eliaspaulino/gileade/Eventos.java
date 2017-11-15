package com.example.eliaspaulino.gileade;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.eliaspaulino.gileade.adaptadores.EventosAdaptador;
import com.example.eliaspaulino.gileade.models.Evento;
import com.example.eliaspaulino.gileade.utilitarios.Buscador;

import java.io.IOException;
import java.util.ArrayList;

public class Eventos extends AppCompatActivity implements Response.ErrorListener, Response.Listener<String> {
    private static final String SERVER_END_POINT = "eventos";
    private Buscador<Evento> eventoBuscador;

    private TextView titulo;
    private Toolbar toolbar;
    private RecyclerView listaEventos;
    private EventosAdaptador eventosAdaptador;
    private ArrayList<Evento> eventos;
    private View imagemCarregamento;
    private View informeVazio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);

        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        listaEventos = (RecyclerView) findViewById(R.id.listaEventos);
        imagemCarregamento = findViewById(R.id.imagemCarregamento);
        informeVazio = findViewById(R.id.informeVazio);
        toolbar.setBackgroundColor(getResources().getColor(R.color.azulescuro));
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.voltaricon));
        titulo = (TextView) findViewById(R.id.main_toolbar_title);
        titulo.setText(getResources().getString(R.string.titulo_eventos_actionbar));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        listaEventos.setItemViewCacheSize(20);
        listaEventos.setDrawingCacheEnabled(true);
        listaEventos.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        eventoBuscador = new Buscador<>(this, SERVER_END_POINT);

        eventoBuscador.find(this, this);
        imagemCarregamento.setVisibility(View.VISIBLE);
    }
    private void inicializarLista(ArrayList<Evento> eventos){
        this.eventos = eventos;
        eventosAdaptador = new EventosAdaptador(eventos, this);
        listaEventos.setAdapter(eventosAdaptador);
        listaEventos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        if(eventos.size() == 0){
            informeVazio.setVisibility(View.VISIBLE);
        }
    }
    public void onResponse(String response){
        imagemCarregamento.setVisibility(View.INVISIBLE);
        try {
            ArrayList<Evento> responseEventos = eventoBuscador.translate(response, new Evento[0]);
            inicializarLista(responseEventos);
        } catch (IOException e) {
            mostrarErros();
            e.printStackTrace();
        }
    }
    public void onErrorResponse(VolleyError error){
        imagemCarregamento.setVisibility(View.INVISIBLE);
        mostrarErros();
    }
    private void mostrarErros(){
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), getResources().getString(R.string.conexao_erro), Snackbar.LENGTH_INDEFINITE);
        snackbar.getView().setBackgroundColor(getResources().getColor(R.color.marrom));
        snackbar.setAction(getResources().getString(R.string.conexao_erro_action), new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                imagemCarregamento.setVisibility(View.VISIBLE);
                eventoBuscador.find();
            }
        })
        .setActionTextColor(getResources().getColor(R.color.azulclaro))
        .show();
    }
}
