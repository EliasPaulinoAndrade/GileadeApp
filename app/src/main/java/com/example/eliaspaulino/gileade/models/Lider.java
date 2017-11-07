package com.example.eliaspaulino.gileade.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Elias on 31/10/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Lider {
    private Integer id;
    private String nome;
    private String telefone;
    private String urlimagem;

    public Lider() {
    }

    public Lider(Integer id, String nome, String telefone, String urlimagem) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.urlimagem = urlimagem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getUrlimagem() {
        return urlimagem;
    }

    public void setUrlimagem(String urlimagem) {
        this.urlimagem = urlimagem;
    }
}
