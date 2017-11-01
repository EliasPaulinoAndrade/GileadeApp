package com.example.eliaspaulino.gileade.models;

/**
 * Created by Elias on 01/11/2017.
 */

public class HorarioDia {
    private String inicio;
    private String fim;
    private String titulo;
    private String descricao;

    public HorarioDia(String inicio, String fim, String titulo, String descricao) {
        this.inicio = inicio;
        this.fim = fim;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFim() {
        return fim;
    }

    public void setFim(String fim) {
        this.fim = fim;
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
}
