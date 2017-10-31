package com.example.eliaspaulino.gileade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.widget.TextView;

import com.example.eliaspaulino.gileade.R;
import com.example.eliaspaulino.gileade.adaptadores.LideresAdaptador;
import com.example.eliaspaulino.gileade.models.Lider;

import java.util.ArrayList;

public class Lideres extends AppCompatActivity {
    private TextView titulo;
    private Toolbar toolbar;
    private RecyclerView listaLideres;
    private LideresAdaptador lideresAdaptador;
    private ArrayList<Lider> liders;
    private SnapHelper snapHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lideres);

        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        listaLideres = (RecyclerView) findViewById(R.id.listaLideres);
        toolbar.setBackgroundColor(getResources().getColor(R.color.azulescuro));
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.voltaricon));
        titulo = (TextView) findViewById(R.id.main_toolbar_title);
        titulo.setText("LIDERES");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        liders = new ArrayList<Lider>();
        liders.add(new Lider("elias", "(85) 987813811"));
        liders.add(new Lider("ze", "(85) 987813811"));

        liders.add(new Lider("elias", "(85) 987813811"));
        liders.add(new Lider("maria", "(85) 987813811"));

        liders.add(new Lider("elias", "(85) 987813811"));

        lideresAdaptador = new LideresAdaptador(liders, this);
        listaLideres.setAdapter(lideresAdaptador);

        listaLideres.setHasFixedSize(true);
        listaLideres.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(listaLideres);
        listaLideres.setOnFlingListener(snapHelper);

    }
}
