package com.example.eliaspaulino.gileade.models;

/**
 * Created by Elias on 31/10/2017.
 */

public class Evento {
    private String titulo;
    private String descricao;
    private String hora;
    private String data;

    public Evento(String titulo, String descricao, String hora, String data) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.hora = hora;
        this.data = data;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
