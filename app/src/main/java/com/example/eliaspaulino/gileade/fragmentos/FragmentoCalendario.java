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
import android.widget.TextView;

import com.example.eliaspaulino.gileade.R;
import com.example.eliaspaulino.gileade.adaptadores.CalendarioAdaptador;
import com.example.eliaspaulino.gileade.models.HorarioDia;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class FragmentoCalendario extends Fragment {

    private RecyclerView listaCalendario;
    private CalendarioAdaptador calendarioAdaptador;
    private ArrayList<HorarioDia> horarioDias;
    private TextView diaSemana;
    private View minhaView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private void setarDiaSemana(Integer dia){
        switch (dia){
            case 0:
                diaSemana.setText("Segunda-Feira");
                break;
            case 1:
                diaSemana.setText("Terça-Feira");
                break;
            case 2:
                diaSemana.setText("Quarta-Feira");
                break;
            case 3:
                diaSemana.setText("Quinta-Feira");
                break;
            case 4:
                diaSemana.setText("Sexta-Feira");
                break;
            case 5:
                diaSemana.setText("Sábado");
                break;
            case 6:
                diaSemana.setText("Domingo");
                break;
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        minhaView =  inflater.inflate(R.layout.fragment_fragmento_calendario, container, false);

        Bundle argumentos = getArguments();
        if(argumentos == null)
            return minhaView;

        diaSemana = (TextView) minhaView.findViewById(R.id.diaSemanaTopo);
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

        //setarDiaSemana(getArguments().getInt("dia"));

        return minhaView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
