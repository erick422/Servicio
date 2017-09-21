package com.system.gestion.Utils;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class Respuesta {
    @JsonProperty
    private String dato;
    @JsonProperty
    private String msj;
    @JsonProperty
    private List<String> listado;
    @JsonProperty
    private Object objeto;

    public Respuesta() {
    }
    
    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public String getMsj() {
        return msj;
    }

    public void setMsj(String msj) {
        this.msj = msj;
    }

    public List<String> getListado() {
        return listado;
    }

    public void setListado(List<String> listado) {
        this.listado = listado;
    }

    public Object getObjeto() {
        return objeto;
    }

    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }
}