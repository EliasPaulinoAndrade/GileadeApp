package com.example.eliaspaulino.gileade.utilitarios;

import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by Elias on 01/11/2017.
 */

public class Buscador  extends StringRequest{
    private static final String SERVER_HOST = "10.1.100.111";
    private static final String HOST_PORT = "8000";
    public Buscador(int method, String route, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, "http://" + SERVER_HOST + ":" + HOST_PORT + "/" + route, listener, errorListener);
    }
}
