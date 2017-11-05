package com.example.eliaspaulino.gileade.utilitarios;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Elias Paulino on 05/11/2017.
 */

public class Mapeador extends ObjectMapper{
    private final DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public  Mapeador() {
        super();
        setDateFormat(format);
    }
}
