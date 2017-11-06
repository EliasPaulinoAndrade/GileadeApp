package com.example.eliaspaulino.gileade;

import android.support.v4.app.FragmentTransaction;
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
import com.example.eliaspaulino.gileade.fragmentos.FragmentoLideres;
import com.example.eliaspaulino.gileade.models.Lider;

import java.util.ArrayList;
import java.util.List;

public class Lideres extends AppCompatActivity {
    private TextView titulo;
    private Toolbar toolbar;
    private List<FragmentoLideres> fragmentoLidereslist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lideres);

        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.azulescuro));
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.voltaricon));
        titulo = (TextView) findViewById(R.id.main_toolbar_title);
        titulo.setText("LIDERES");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        fragmentoLidereslist = new ArrayList<>();
        adicionarFragmento();
        adicionarFragmento();
        adicionarFragmento();
    }
    private void adicionarFragmento(){
        FragmentoLideres fragmentoLideres = new FragmentoLideres();
        fragmentoLidereslist.add(fragmentoLideres);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.listafragmentlideres, fragmentoLideres, Integer.toString(fragmentoLidereslist.size()));
        transaction.commit();
    }
}
