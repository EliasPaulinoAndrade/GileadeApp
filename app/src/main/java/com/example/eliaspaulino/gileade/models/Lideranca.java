package com.example.eliaspaulino.gileade.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elias on 06/11/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Lideranca {
    private Integer id;
    private ArrayList<Lider> lideres;
    private String nome;

    public Lideranca() {
    }

    public Lideranca(Integer id, ArrayList<Lider> lideres, String nome) {
        this.id = id;
        this.lideres = lideres;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<Lider> getLideres() {
        return lideres;
    }

    public void setLideres(ArrayList<Lider> lideres) {
        this.lideres = lideres;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
