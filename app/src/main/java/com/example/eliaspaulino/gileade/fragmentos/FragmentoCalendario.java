package com.example.eliaspaulino.gileade.fragmentos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.signature.ObjectKey;
import com.example.eliaspaulino.gileade.R;
import com.example.eliaspaulino.gileade.adaptadores.CalendarioAdaptador;
import com.example.eliaspaulino.gileade.adaptadores.EventosAdaptador;
import com.example.eliaspaulino.gileade.models.Evento;
import com.example.eliaspaulino.gileade.models.EventoSemanal;
import com.example.eliaspaulino.gileade.utilitarios.Buscador;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class FragmentoCalendario extends Fragment implements Response.ErrorListener, Response.Listener<String> {

    private RecyclerView listaCalendario;
    private CalendarioAdaptador calendarioAdaptador;
    private ArrayList<EventoSemanal> eventosSemanais;
    private TextView diaSemana;
    private View minhaView;
    private RequestQueue queue;
    private ObjectMapper mapper;
    private Integer nDiaSemana;
    private View imagemCarregamento;

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

        return minhaView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        nDiaSemana = getArguments().getInt("dia");
        diaSemana = (TextView) getActivity().findViewById(R.id.diaSemanaTopo);
        listaCalendario = (RecyclerView) getActivity().findViewById(R.id.listaHoras);
        imagemCarregamento = getActivity().findViewById(R.id.imagemCarregamento);

        setarDiaSemana(nDiaSemana);

        queue = Volley.newRequestQueue(getContext());
        mapper = new ObjectMapper();

        buscarEventosSemanais();
    }

    private void inicializarLista(ArrayList<EventoSemanal> eventosSemanais){
        this.eventosSemanais = eventosSemanais;
        calendarioAdaptador = new CalendarioAdaptador(eventosSemanais, getActivity());
        listaCalendario.setAdapter(calendarioAdaptador);
        listaCalendario.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }
    private void buscarEventosSemanais(){
        imagemCarregamento.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new Buscador(Request.Method.GET, "eventos_semanais/" + nDiaSemana, this, this );
        queue.add(stringRequest);
    }
    @Override
    public void onErrorResponse(VolleyError error) {
        imagemCarregamento.setVisibility(View.INVISIBLE);
        mostrarErros();
    }
    @Override
    public void onResponse(String response) {
        imagemCarregamento.setVisibility(View.GONE);
        try {
            ArrayList<EventoSemanal> responseEventosSemanais =  new ArrayList<>(Arrays.asList(mapper.readValue(response, EventoSemanal[].class)));
            inicializarLista(responseEventosSemanais);
        } catch (IOException e) {
            mostrarErros();
            e.printStackTrace();
        }
    }
    private void mostrarErros(){
        Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content), "Problemas De Conexão", Snackbar.LENGTH_INDEFINITE);
        snackbar.getView().setBackgroundColor(getResources().getColor(R.color.marrom));
            snackbar.setAction("recarregar", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    buscarEventosSemanais();
                }
            })
            .setActionTextColor(getResources().getColor(R.color.azulclaro))
            .show();
    }
}
