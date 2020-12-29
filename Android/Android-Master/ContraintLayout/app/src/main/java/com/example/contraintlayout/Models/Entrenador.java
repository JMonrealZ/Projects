package com.example.contraintlayout.Models;

public class Entrenador {
    private int Matricula;
    private String Nombre;
    private double Experiencia;
    private String Sucursal;
    private int Estatus; //Este servirá para el color(activo o inactivo)
    private String FotoURL;

    public Entrenador(int matricula, String nombre, double experiencia, String sucursal, int estatus, String fotoURL) {
        Matricula = matricula;
        Nombre = nombre;
        Experiencia = experiencia;
        Sucursal = sucursal;
        Estatus = estatus;
        FotoURL = fotoURL;
    }

    public int getMatricula() {
        return Matricula;
    }

    public void setMatricula(int matricula) {
        Matricula = matricula;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public double getExperiencia() {
        return Experiencia;
    }

    public void setExperiencia(double experiencia) {
        Experiencia = experiencia;
    }

    public String getSucursal() {
        return Sucursal;
    }

    public void setSucursal(String sucursal) {
        Sucursal = sucursal;
    }

    public int getEstatus() {
        return Estatus;
    }

    public void setEstatus(int estatus) {
        Estatus = estatus;
    }

    public String getFotoURL() {
        return FotoURL;
    }

    public void setFotoURL(String fotoURL) {
        FotoURL = fotoURL;
    }
}
