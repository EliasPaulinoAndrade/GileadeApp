package com.example.eliaspaulino.gileade;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eliaspaulino.gileade.adaptadores.CalendarioAdaptador;
import com.example.eliaspaulino.gileade.adaptadores.LideresAdaptador;
import com.example.eliaspaulino.gileade.fragmentos.FragmentoCalendario;
import com.example.eliaspaulino.gileade.models.HorarioDia;

import java.util.ArrayList;
import java.util.List;

public class Calendario extends AppCompatActivity {
    private TextView titulo;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.azulescuro));
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.voltaricon));
        titulo = (TextView) findViewById(R.id.main_toolbar_title);
        titulo.setText("CALENDÁRIO");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        trocarFragmento(1);
    }
    private void trocarFragmento(Integer dia){
        FragmentoCalendario fragment = new FragmentoCalendario();
        Bundle bundle = new Bundle();
        bundle.putInt("dia", dia);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentoCalendario, fragment);
        fragmentTransaction.commit();
    }
    public void diaClique(View view){
        ViewGroup grupo = (ViewGroup) view.getParent();
        ViewGroup frame;
        for(int i = 0; i < grupo.getChildCount(); i++){
            frame = (ViewGroup)grupo.getChildAt(i);
            for(int j = 0; j < frame.getChildCount(); j++){
                if (frame.getChildAt(j) instanceof  FloatingActionButton){
                    frame.getChildAt(j).setVisibility(View.INVISIBLE);
                }
                if (frame.getChildAt(j) instanceof TextView){
                    ((TextView) frame.getChildAt(j)).setTextColor(getResources().getColor(R.color.branco));
                }
            }
        }
        frame = (ViewGroup) view;
        for(int i = 0; i < frame.getChildCount(); i++){
            if (frame.getChildAt(i) instanceof FloatingActionButton){
                frame.getChildAt(i).setVisibility(View.VISIBLE);
            }
            if(frame.getChildAt(i) instanceof TextView){
                ((TextView) frame.getChildAt(i)).setTextColor(getResources().getColor(R.color.azulescuro));
            }
        }
        trocarFragmento(grupo.indexOfChild(view));
    }
    public void diaCliqueExpand(View view){
        ViewGroup linearPai = (ViewGroup) view.getParent();
        ViewGroup pai = (ViewGroup) linearPai.getParent();
        Integer index = pai.indexOfChild(linearPai)*2 + linearPai.indexOfChild(view);
        for(int i = 0; i < pai.getChildCount(); i++){
            linearPai = (ViewGroup)pai.getChildAt(i);
            for(int j = 0; j < linearPai.getChildCount(); j++){
                linearPai.getChildAt(j).setBackgroundDrawable(getResources().getDrawable(R.drawable.fundohoradia));
                ((TextView)linearPai.getChildAt(j)).setTextColor(getResources().getColor(R.color.branco));
            }
        }
        ((TextView) view).setBackgroundDrawable(getResources().getDrawable(R.drawable.fundohoraselec));
        ((TextView) view).setTextColor(getResources().getColor(R.color.azulescuro));
        trocarFragmento(index);
    }
}
