package com.example.eliaspaulino.gileade.fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eliaspaulino.gileade.R;
import com.example.eliaspaulino.gileade.adaptadores.LideresAdaptador;
import com.example.eliaspaulino.gileade.models.Lider;

import java.util.ArrayList;


public class FragmentoLideres extends Fragment {

    private RecyclerView listaLideres;
    private LideresAdaptador lideresAdaptador;
    private ArrayList<Lider> liders;
    private SnapHelper snapHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View minhaView = inflater.inflate(R.layout.fragment_fragmento_lideres, container, false);
        listaLideres = (RecyclerView) minhaView.findViewById(R.id.listaLideres);
        liders = new ArrayList<Lider>();
        liders.add(new Lider("elias", "(85) 987813811"));
        liders.add(new Lider("ze", "(85) 987813811"));

        liders.add(new Lider("elias", "(85) 987813811"));
        liders.add(new Lider("maria", "(85) 987813811"));

        liders.add(new Lider("elias", "(85) 987813811"));

        lideresAdaptador = new LideresAdaptador(liders, getContext());
        listaLideres.setAdapter(lideresAdaptador);

        listaLideres.setHasFixedSize(true);
        listaLideres.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(listaLideres);
        listaLideres.setOnFlingListener(snapHelper);

        return minhaView;
    }
}
