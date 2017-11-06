package com.example.eliaspaulino.gileade.singletons;

import com.example.eliaspaulino.gileade.models.Lideranca;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Elias on 06/11/2017.
 */

public class LiderancaSingleton {
    private static LiderancaSingleton instancia = null;
    private HashMap<Integer, Lideranca> liderancas;
    private LiderancaSingleton(){
        this.liderancas = new HashMap<>();
    }
    public static LiderancaSingleton getInstancia(){
        if(instancia == null){
            instancia = new LiderancaSingleton();
        }
        return instancia;
    }

    public HashMap<Integer, Lideranca> getLiderancas() {
        return liderancas;
    }

    public void setLiderancas(HashMap<Integer, Lideranca> liderancas) {
        this.liderancas = liderancas;
    }
}
