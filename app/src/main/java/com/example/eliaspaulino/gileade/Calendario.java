package com.example.eliaspaulino.gileade;

import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eliaspaulino.gileade.fragmentos.FragmentoCalSemanaExpandido;
import com.example.eliaspaulino.gileade.fragmentos.FragmentoCalendario;
import com.example.eliaspaulino.gileade.fragmentos.FragmentoCalendarioSemana;

public class Calendario extends AppCompatActivity {
    private TextView titulo;
    private Toolbar toolbar;
    private Boolean calendarioExpandido;

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
        //trocarDia(0);
        calendarioExpandido = false;
    }
    private void trocarDia(Integer dia){
        FragmentoCalendario fragment = new FragmentoCalendario();
        Bundle bundle = new Bundle();
        bundle.putInt("dia", dia);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.fragmentoCalendario, fragment);
        fragmentTransaction.commit();
    }
    private void mudarCalendario(android.support.v4.app.Fragment fragment){
        trocarDia(0);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentoSemanas, fragment);
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        transaction.commit();
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
        trocarDia(grupo.indexOfChild(view));
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
        trocarDia(index);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.expandir){
            android.support.v4.app.Fragment fragment;
            if(calendarioExpandido){
                item.setIcon(R.mipmap.expandir);
                fragment = new FragmentoCalendarioSemana();
            }
            else{
                item.setIcon(R.mipmap.diminuir);
                fragment = new FragmentoCalSemanaExpandido();
            }
            calendarioExpandido = !calendarioExpandido;
            mudarCalendario(fragment);
        }
        return super.onOptionsItemSelected(item);
    }
}
