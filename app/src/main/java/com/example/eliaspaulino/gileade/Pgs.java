package com.example.eliaspaulino.gileade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;


import com.example.eliaspaulino.gileade.adaptadores.PgAdaptador;

import java.util.ArrayList;

public class Pgs extends AppCompatActivity {
    private RecyclerView listaFotosPgs;
    private RecyclerView listaFotosPgs2;
    private TextView titulo;
    private Toolbar toolbar;
    private PgAdaptador pgAdaptador;
    private SnapHelper snapHelper;
    private SnapHelper snapHelper2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pgs);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.azulescuro));
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.voltaricon));
        titulo = (TextView) findViewById(R.id.main_toolbar_title);
        titulo.setText(getResources().getString(R.string.titulo_pgs_actionbar));
        listaFotosPgs = (RecyclerView) findViewById(R.id.listaFotos);
        listaFotosPgs2 = (RecyclerView) findViewById(R.id.listaFotos2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ArrayList<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("3");

        strings.add("3");

        strings.add("3");

        pgAdaptador = new PgAdaptador(strings, this);
        listaFotosPgs.setAdapter(pgAdaptador);
        listaFotosPgs2.setAdapter(pgAdaptador);
        listaFotosPgs.setHasFixedSize(true);
        listaFotosPgs.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(listaFotosPgs);


        listaFotosPgs2.setHasFixedSize(true);
        listaFotosPgs2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        snapHelper2 = new LinearSnapHelper();
        snapHelper2.attachToRecyclerView(listaFotosPgs2);
    }
}
