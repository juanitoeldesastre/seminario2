package com.juanitodev.tarea01;

import java.io.Serializable;

public class Visita implements Serializable {
    private String nombre;
    private String empresa;
    private String proposito;
    private String horaEntrada;
    private String horaSalida;

    public Visita(String nombre, String empresa, String proposito, String horaEntrada, String horaSalida) {
        this.nombre = nombre;
        this.empresa = empresa;
        this.proposito = proposito;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getProposito() {
        return proposito;
    }

    public void setProposito(String proposito) {
        this.proposito = proposito;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public boolean estaActiva() {
        return horaSalida == null;
    }

    @Override
    public String toString() {
        String estado = horaSalida == null ? "Activa" : "Finalizada";
        return nombre + " - " + empresa + " (" + estado + ")";
    }
}