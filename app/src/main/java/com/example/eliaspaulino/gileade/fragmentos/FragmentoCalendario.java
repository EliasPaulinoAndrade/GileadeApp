package com.example.eliaspaulino.gileade.fragmentos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.eliaspaulino.gileade.R;
import com.example.eliaspaulino.gileade.adaptadores.CalendarioAdaptador;
import com.example.eliaspaulino.gileade.models.EventoSemanal;
import com.example.eliaspaulino.gileade.utilitarios.Buscador;

import java.io.IOException;
import java.util.ArrayList;


public class FragmentoCalendario extends Fragment implements Response.ErrorListener, Response.Listener<String> {
    private static final String SERVER_END_POINT = "eventos_semanais/showByDay";
    private Buscador<EventoSemanal> buscador;

    private RecyclerView listaCalendario;
    private CalendarioAdaptador calendarioAdaptador;
    private ArrayList<EventoSemanal> eventosSemanais;
    private TextView diaSemana;
    private View minhaView;
    private Integer nDiaSemana;
    private View imagemCarregamento;
    private View informeVazio;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private void setarDiaSemana(Integer dia){
        diaSemana.setText(getResources().getStringArray(R.array.dias_semana)[dia]);
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
        informeVazio = getActivity().findViewById(R.id.informeVazio);

        setarDiaSemana(nDiaSemana);

        buscador = new Buscador<>(getContext(), SERVER_END_POINT + "/" + nDiaSemana);
        buscador.find(this, this);
    }

    private void inicializarLista(ArrayList<EventoSemanal> eventosSemanais) {
        this.eventosSemanais = eventosSemanais;
        calendarioAdaptador = new CalendarioAdaptador(eventosSemanais, getActivity());
        listaCalendario.setAdapter(calendarioAdaptador);
        listaCalendario.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        if (eventosSemanais.size() == 0) {
            informeVazio.setVisibility(View.VISIBLE);
        }
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
            ArrayList<EventoSemanal> responseEventosSemanais =  buscador.translate(response, new EventoSemanal[0]);
            inicializarLista(responseEventosSemanais);
        } catch (IOException e) {
            mostrarErros();
            e.printStackTrace();
        }
    }
    private void mostrarErros(){
        try {
            Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content), getResources().getString(R.string.conexao_erro), Snackbar.LENGTH_INDEFINITE);
            snackbar.getView().setBackgroundColor(getResources().getColor(R.color.marrom));
            snackbar.setAction(getResources().getString(R.string.conexao_erro_action), new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    buscador.find();
                }
            })
            .setActionTextColor(getResources().getColor(R.color.azulclaro))
            .show();
        }catch (Exception e){
            //fragment foi trocado
        }
    }
}
