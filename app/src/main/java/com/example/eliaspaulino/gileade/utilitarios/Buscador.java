package com.example.eliaspaulino.gileade.utilitarios;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.eliaspaulino.gileade.models.Evento;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Elias on 07/11/2017.
 */

public class Buscador<MODEL> {
    private Context context;
    private Mapeador mapper;
    private String endpoint;
    private Response.Listener listener;
    private Response.ErrorListener errorListener;
    private static final String SERVER_HOST = "10.1.100.111";
    private static final String HOST_PORT = "8000";

    public Buscador(Context context, String endpoint) {
        this.context = context;
        this.endpoint = endpoint;
        mapper = new Mapeador();
    }

    private String constructUrl(){
        return "http://" + SERVER_HOST + ":" + HOST_PORT + "/" + endpoint;
    }
    private String constructImgUrl(String imageName){
        return "http://" + SERVER_HOST + ":" + HOST_PORT + "/images/" + endpoint + "/" + imageName;
    }
    public ArrayList<MODEL> translate(String response, Object[] objects) throws IOException {
        ArrayList<MODEL> responseEventos;
        responseEventos = (ArrayList<MODEL>) new ArrayList<>(Arrays.asList(mapper.readValue(response, objects.getClass())));
        Log.d("TESTANDO", "translate: " + responseEventos.size());
        return responseEventos;
    }
    public void find(Response.Listener listener, Response.ErrorListener errorListener){
        this.listener = listener;
        this.errorListener = errorListener;

        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(constructUrl(), listener, errorListener);
        queue.add(stringRequest);
    }
    public void find(){
        if (listener==null || errorListener==null)
            return ;
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(constructUrl(), listener, errorListener);
        queue.add(stringRequest);
    }
    public void findImage(String imgName, ImageView imagem){
        Picasso.with(context).load(constructImgUrl(imgName)).into(imagem);
    }
}
