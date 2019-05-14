package com.example.alex.prueba;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Persona extends RealmObject {

    @PrimaryKey
    private String numDni;

//    private String nombreCompleto;
//    private String apellidos;

   @Required
    private String nombreCompleto;

    private String genero;
    private int edad;

    //Constructor
//    public Persona (){
//        nombreCompleto = "";
//        apellidos = "";
//        edad = 0;
//        numDni = "";
//    }

    public String getgenero() { return genero; }
//    public String getnombre (){ return nombreCompleto; }
//    public String getapellidos () { return apellidos; }
    public int getedad () { return edad; }
    public String getNumDni () { return numDni; }
    public String getnombreCompleto() {
        return nombreCompleto;
    }

//    public void setnombre (String valornombre) {
//        this.nombreCompleto = valornombre;
//    }
//    public void setapellidos (String valorapellidos){
//        this.apellidos = valorapellidos;
//    }
    public void setedad (int valoredad){
        this.edad = valoredad;
    }
    public void setnumDni(String valorDni){ this.numDni = valorDni; }
    public void setgenero(String genero) { this.genero = genero; }
    public void setnombreCompleto(String nombre, String apellidos) {
        this.nombreCompleto = nombre + " " + apellidos;
    }

}