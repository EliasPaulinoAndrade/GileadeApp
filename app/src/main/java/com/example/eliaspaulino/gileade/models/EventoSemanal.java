package com.example.eliaspaulino.gileade.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Time;

/**
 * Created by Elias on 01/11/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventoSemanal {
    private Integer id;
    private Time horainicio;
    private Time horafim;
    private String titulo;
    private String descricao;
    private Integer diasemana;

    public EventoSemanal() {
    }

    public EventoSemanal(Integer id, Time horainicio, Time horafim, String titulo, String descricao, Integer diasemana) {
        this.id = id;
        this.horainicio = horainicio;
        this.horafim = horafim;
        this.titulo = titulo;
        this.descricao = descricao;
        this.diasemana = diasemana;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Time getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(Time horainicio) {
        this.horainicio = horainicio;
    }

    public Time getHorafim() {
        return horafim;
    }

    public void setHorafim(Time horafim) {
        this.horafim = horafim;
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

    public Integer getDiasemana() {
        return diasemana;
    }

    public void setDiasemana(Integer diasemana) {
        this.diasemana = diasemana;
    }
}
