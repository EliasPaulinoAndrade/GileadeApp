package com.example.eliaspaulino.gileade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.eliaspaulino.gileade.adaptadores.EventosAdaptador;
import com.example.eliaspaulino.gileade.adaptadores.LideresAdaptador;
import com.example.eliaspaulino.gileade.models.Evento;
import com.example.eliaspaulino.gileade.models.Lider;

import java.util.ArrayList;

public class Eventos extends AppCompatActivity {

    private TextView titulo;
    private Toolbar toolbar;
    private RecyclerView listaEventos;
    private EventosAdaptador eventosAdaptador;
    private ArrayList<Evento> eventos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);

        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        listaEventos = (RecyclerView) findViewById(R.id.listaEventos);
        toolbar.setBackgroundColor(getResources().getColor(R.color.azulescuro));
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.voltaricon));
        titulo = (TextView) findViewById(R.id.main_toolbar_title);
        titulo.setText("EVENTOS");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        eventos = new ArrayList<>();
        eventos.add(new Evento("EJC 2017 GILEADE", "Caros amigos, a valorização de fatores  subjetivos promove a alavancagem do fluxo de informações. Caros amigos, a valorização de fatores  subjetivos promove a alavancagem do fluxo de informações.", "12:30 as 13:30", "17/06/96"));
        eventos.add(new Evento("EJC 2017 GILEADE", "Caros amigos, a valorização de fatores  subjetivos promove a alavancagem do fluxo de informações. Caros amigos, a valorização de fatores  subjetivos promove a alavancagem do fluxo de informações.", "12:30 as 13:30", "17/06/96"));

        eventosAdaptador = new EventosAdaptador(eventos, this);
        listaEventos.setAdapter(eventosAdaptador);
        listaEventos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
}
