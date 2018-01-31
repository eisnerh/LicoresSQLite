package com.example.eisne.licoresql.entidades;

import java.io.Serializable;

/**
 * Created by eisne on 25/1/2018.
 */

public class Licores implements Serializable {
    public String IdUsuario;
    public String Material;
    public String DescripcionBreve;
    public int Unidades;

    public Licores(String idUsuario, String material, String descripcionBreve, int unidades) {
        IdUsuario = idUsuario;
        Material = material;
        DescripcionBreve = descripcionBreve;
        Unidades = unidades;
    }

    public Licores(){

    }

    public String getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getMaterial() {
        return Material;
    }

    public void setMaterial(String material) {
        Material = material;
    }

    public String getDescripcionBreve() {
        return DescripcionBreve;
    }

    public void setDescripcionBreve(String descripcionBreve) {
        DescripcionBreve = descripcionBreve;
    }

    public int getUnidades() {
        return Unidades;
    }

    public void setUnidades(int unidades) {
        Unidades = unidades;
    }
}
