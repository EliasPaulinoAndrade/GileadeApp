package com.example.eliaspaulino.gileade.models;

/**
 * Created by Elias on 31/10/2017.
 */

public class Lider {
    private String nome;
    private String numero;

    public Lider(String nome, String numero) {
        this.nome = nome;
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
