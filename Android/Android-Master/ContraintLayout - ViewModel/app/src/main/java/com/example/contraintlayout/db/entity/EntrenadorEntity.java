package com.example.contraintlayout.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "CatEntrenadores")
public class EntrenadorEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String FotoURL;
    public String Nombre;
    public String Sucursal;
    public double Experiencia;
    public int Estatus; //Este servirá para el color(activo o inactivo)
    public int Matricula;

    public EntrenadorEntity(){ }

    public EntrenadorEntity(String fotoURL, String nombre, String sucursal, double experiencia, int estatus, int matricula) {
        FotoURL = fotoURL;
        Nombre = nombre;
        Sucursal = sucursal;
        Experiencia = experiencia;
        Estatus = estatus;
        Matricula = matricula;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFotoURL() {
        return FotoURL;
    }

    public void setFotoURL(String fotoURL) {
        FotoURL = fotoURL;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getSucursal() {
        return Sucursal;
    }

    public void setSucursal(String sucursal) {
        Sucursal = sucursal;
    }

    public double getExperiencia() {
        return Experiencia;
    }

    public void setExperiencia(double experiencia) {
        Experiencia = experiencia;
    }

    public int getEstatus() {
        return Estatus;
    }

    public void setEstatus(int estatus) {
        Estatus = estatus;
    }

    public int getMatricula() {
        return Matricula;
    }

    public void setMatricula(int matricula) {
        Matricula = matricula;
    }
}
