package com.example.eliaspaulino.gileade.fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eliaspaulino.gileade.R;
import com.example.eliaspaulino.gileade.adaptadores.CalendarioAdaptador;
import com.example.eliaspaulino.gileade.models.HorarioDia;

import java.util.ArrayList;


public class FragmentoCalendario extends Fragment {

    private RecyclerView listaCalendario;
    private CalendarioAdaptador calendarioAdaptador;
    private ArrayList<HorarioDia> horarioDias;
    private View minhaView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        minhaView =  inflater.inflate(R.layout.fragment_fragmento_calendario, container, false);

        listaCalendario = (RecyclerView) minhaView.findViewById(R.id.listaHoras);
        horarioDias = new ArrayList<>();
        horarioDias.add(new HorarioDia("12:30", "12:30", "Titulo", "Percebemos, cada vez mais, que o desenvolvimento contínuo de distintas formas de atuação causa impacto indireto na reavaliação das condições financeiras e administrativas exigidas."));
        horarioDias.add(new HorarioDia("12:30", "12:30", "Titulo", "Descricao"));
        horarioDias.add(new HorarioDia("12:30", "12:30", "Titulo", "Descricao"));
        horarioDias.add(new HorarioDia("12:30", "12:30", "Titulo", "Descricao"));
        horarioDias.add(new HorarioDia("12:30", "12:30", "Titulo", "A prática cotidiana prova que o desenvolvimento contínuo de distintas formas de atuação aponta para a melhoria dos paradigmas corporativos."));
        horarioDias.add(new HorarioDia("12:30", "12:30", "Titulo", "Descricao"));
        horarioDias.add(new HorarioDia("12:30", "12:30", "Titulo", "Descricao"));
        horarioDias.add(new HorarioDia("12:30", "12:30", "Titulo", "Descricao"));

        calendarioAdaptador = new CalendarioAdaptador(horarioDias, getActivity());
        listaCalendario.setAdapter(calendarioAdaptador);
        listaCalendario.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));


        return minhaView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle argumentos = getArguments();
        if(argumentos == null)
            return ;
        Log.d("TESTE", "onCreateView: " + getArguments().getInt("dia"));
    }
}
