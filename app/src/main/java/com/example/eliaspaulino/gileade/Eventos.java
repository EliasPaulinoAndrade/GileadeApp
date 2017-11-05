package com.example.eliaspaulino.gileade;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.eliaspaulino.gileade.adaptadores.EventosAdaptador;
import com.example.eliaspaulino.gileade.adaptadores.LideresAdaptador;
import com.example.eliaspaulino.gileade.models.Evento;
import com.example.eliaspaulino.gileade.models.Lider;
import com.example.eliaspaulino.gileade.utilitarios.Buscador;
import com.example.eliaspaulino.gileade.utilitarios.Mapeador;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Eventos extends AppCompatActivity implements Response.ErrorListener, Response.Listener<String> {

    private TextView titulo;
    private Toolbar toolbar;
    private RecyclerView listaEventos;
    private EventosAdaptador eventosAdaptador;
    private ArrayList<Evento> eventos;
    private RequestQueue queue;
    private ObjectMapper mapper;
    private View imagemCarregamento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);

        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        listaEventos = (RecyclerView) findViewById(R.id.listaEventos);
        imagemCarregamento = findViewById(R.id.imagemCarregamento);
        toolbar.setBackgroundColor(getResources().getColor(R.color.azulescuro));
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.voltaricon));
        titulo = (TextView) findViewById(R.id.main_toolbar_title);
        titulo.setText("EVENTOS");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        queue = Volley.newRequestQueue(this);
        mapper = new Mapeador();

        buscarEventos();
    }
    private void inicializarLista(ArrayList<Evento> eventos){
        this.eventos = eventos;
        eventosAdaptador = new EventosAdaptador(eventos, this);
        listaEventos.setAdapter(eventosAdaptador);
        listaEventos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
    private void buscarEventos(){
        imagemCarregamento.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new Buscador(Request.Method.GET, "eventos", this, this );
        queue.add(stringRequest);
    }
    public void onResponse(String response){
        imagemCarregamento.setVisibility(View.INVISIBLE);
        try {
            ArrayList<Evento> responseEventos =  new ArrayList<>(Arrays.asList(mapper.readValue(response, Evento[].class)));
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
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Problemas De Conexão", Snackbar.LENGTH_INDEFINITE);
        snackbar.getView().setBackgroundColor(getResources().getColor(R.color.marrom));
        snackbar.setAction("recarregar", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarEventos();
            }
        })
        .setActionTextColor(getResources().getColor(R.color.azulclaro))
        .show();
    }
}
