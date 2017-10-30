package com.example.eliaspaulino.gileade.adaptadores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ceylonlabs.imageviewpopup.ImagePopup;
import com.example.eliaspaulino.gileade.R;

import java.util.ArrayList;

/**
 * Created by Elias on 30/10/2017.
 */

public class PgAdaptador extends RecyclerView.Adapter<PgAdaptador.Segurador> implements View.OnClickListener {
    private ArrayList<String> dados;
    private Context ctx;
    private ImagePopup imagePopup;

    public PgAdaptador(ArrayList<String> dados, Context ctx) {
        this.dados = dados;
        this.ctx = ctx;
        this.imagePopup = new ImagePopup(ctx);
    }
    @Override
    public Segurador onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflador = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflador.inflate(R.layout.layout_item_pgs, parent, false);
        view.setOnClickListener(this);
        return new Segurador(view);
    }
    @Override
    public void onBindViewHolder(Segurador holder, int position) {
        final ImageView imageView = (ImageView) holder.view.findViewById(R.id.imagemPG);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePopup.initiatePopup(imageView.getDrawable());
                imagePopup.setHideCloseIcon(true);
                imagePopup.setWindowWidth(ctx.getResources().getDisplayMetrics().widthPixels);
                imagePopup.setWindowHeight(ctx.getResources().getDisplayMetrics().heightPixels);
                imagePopup.setBackgroundColor(ctx.getResources().getColor(R.color.trans));
                imagePopup.setImageOnClickClose(true);
                imagePopup.viewPopup();
            }
        });
    }
    @Override
    public int getItemCount() {
        return dados.size();
    }

    @Override
    public void onClick(View v) {
        Log.d("TESTE", "onClick: ");
        ImageView imageView = (ImageView) v.findViewById(R.id.imagemPG);
        imagePopup.initiatePopup(imageView.getDrawable());
    }

    public static class Segurador extends RecyclerView.ViewHolder{
        public View view;
        public Segurador(View view) {
            super(view);
            this.view = view;
        }
    }
}
