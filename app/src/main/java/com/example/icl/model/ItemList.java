package com.example.icl.model;

import java.io.Serializable;

public class ItemList implements Serializable {

    private String Nombre;
    private String descripcion;
    private String img;
    private double Precio;



    public ItemList(String Nombre, String descripcion, String img, double Precio) {
        this.Nombre = Nombre;
        this.descripcion = descripcion;
        this.img = img;
        this.Precio=Precio;

    }

    public String getNombre() {
        return Nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getImg() {
        return img;
    }

    public Double getPrecio() { return Precio;}
}
