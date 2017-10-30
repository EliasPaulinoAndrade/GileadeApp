package com.example.eliaspaulino.gileade;

import android.animation.Animator;
import android.animation.LayoutTransition;
import android.app.Activity;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Inicio extends Activity {

    private LinearLayout titulo;
    private LinearLayout conteinerPagina;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        titulo = (LinearLayout) findViewById(R.id.titulo);
        conteinerPagina = (LinearLayout) findViewById(R.id.conteinerPagina);
    }
    public void tituloPaginaClique(View v){
        titulo.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;
        ViewCompat.setElevation(titulo, 5);
        titulo.requestLayout();
        Log.d("eiman", "tituloPaginaClique: ");
    }
    private void animacaoCircular(View view, int centerx, int centery){
        float finalRadius = Math.max(view.getWidth(), view.getHeight());
        Animator circularReveal = io.codetail.animation.ViewAnimationUtils.createCircularReveal(view, centerx, centery, 0, finalRadius * 1.1f);
        circularReveal.setDuration(300);
        view.setVisibility(View.VISIBLE);
        circularReveal.start();
    }
    public void calendarioClique(View view){
        int[] local = new int[2];
        view.getLocationInWindow(local);
        animacaoCircular(conteinerPagina, local[0] + view.getWidth()/2, local[1] + view.getHeight()/2);
    }
}
