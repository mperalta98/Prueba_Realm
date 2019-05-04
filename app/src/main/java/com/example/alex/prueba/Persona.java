package com.example.alex.prueba;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Persona extends RealmObject {
    @PrimaryKey
    private String numDni;

    private String nombre;
    private String apellidos;
    private int edad;

    //Constructor
//    public Persona (){
//        nombre = "";
//        apellidos = "";
//        edad = 0;
//        numDni = "";
//    }

    public String getnombre (){ return nombre; }
    public String getapellidos () { return apellidos; }
    public int getedad () { return edad; }
    public String getNumDni () { return numDni; }

    public void setnombre (String valornombre) {
        this.nombre = valornombre;
    }
    public void setapellidos (String valorapellidos){
        this.apellidos = valorapellidos;
    }
    public void setedad (int valoredad){
        this.edad = valoredad;
    }
    public void setnumDni(String valorDni){ this.numDni = valorDni;
    }

}