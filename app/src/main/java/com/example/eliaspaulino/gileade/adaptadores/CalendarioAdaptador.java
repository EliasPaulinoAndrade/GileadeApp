package com.example.eliaspaulino.gileade.adaptadores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eliaspaulino.gileade.R;
import com.example.eliaspaulino.gileade.models.EventoSemanal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Elias on 01/11/2017.
 */

public class CalendarioAdaptador extends RecyclerView.Adapter<CalendarioAdaptador.Segurador>  {
    private ArrayList<EventoSemanal> dados;
    private Context ctx;

    public CalendarioAdaptador(ArrayList<EventoSemanal> dados, Context ctx) {
        this.dados = dados;
        this.ctx = ctx;
    }

    @Override
    public CalendarioAdaptador.Segurador onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_item_calendario, parent, false);
        return new CalendarioAdaptador.Segurador(view);
    }

    @Override
    public void onBindViewHolder(Segurador holder, int position) {
        View view = holder.view;
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        TextView titulo = (TextView) view.findViewById(R.id.titulo);
        TextView descricao = (TextView) view.findViewById(R.id.descricao);
        TextView inicio = (TextView) view.findViewById(R.id.horaInicio);
        TextView fim = (TextView) view.findViewById(R.id.horafim);
        titulo.setText(dados.get(position).getTitulo());
        descricao.setText(dados.get(position).getDescricao());
        inicio.setText(dateFormat.format(dados.get(position).getHorainicio()));
        fim.setText(dateFormat.format(dados.get(position).getHorafim()));
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }
    static class Segurador extends RecyclerView.ViewHolder{
        View view;
        public Segurador(View itemView) {
            super(itemView);
            this.view = itemView;
        }
    }
}
