package com.example.trabajofinal;

import com.google.gson.annotations.SerializedName;

public class Producto {
    @SerializedName("noproducto")
    String noproducto;
    @SerializedName("precio")
    String precio;

    public String getNoproducto(){
        return noproducto;
    }

    public String getPrecio(){
        return precio;
    }
}
