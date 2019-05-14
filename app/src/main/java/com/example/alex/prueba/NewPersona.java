package com.example.alex.prueba;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;

public class NewPersona extends AppCompatActivity {
    EditText dni, nombre, apellidos, edad, genero;
    Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_persona);

        dni = findViewById(R.id.new_dni);
        nombre = findViewById(R.id.new_nombre);
        apellidos = findViewById(R.id.new_apellido);
        edad = findViewById(R.id.new_edad);
        genero = findViewById(R.id.new_genero);
        btn_add = findViewById(R.id.btn_add);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dni.getText().toString().isEmpty() && !apellidos.getText().toString().isEmpty() && !nombre.getText().toString().isEmpty()){
                    Realm realm = Realm.getDefaultInstance();
                    realm.beginTransaction();

                    Persona persona = realm.createObject(Persona.class, dni.getText().toString());

                    persona.setnombreCompleto(nombre.getText().toString(), apellidos.getText().toString());
                    persona.setedad(Integer.parseInt(edad.getText().toString()));
                    persona.setgenero(genero.getText().toString());
                    realm.commitTransaction();
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"Introduce un ID válido.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}