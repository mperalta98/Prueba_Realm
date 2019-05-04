package com.example.alex.prueba;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;

public class ModPersona extends AppCompatActivity {
    EditText nombre, apellido, edad;
    TextView dni;
    Button btn_del;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod_persona);

        dni = findViewById(R.id.set_dni);
        nombre = findViewById(R.id.new_nombre);
        apellido = findViewById(R.id.new_apellido);
        edad = findViewById(R.id.new_edad);
        btn_del = findViewById(R.id.btn_modify);

        Intent intent = getIntent();
        Integer age = intent.getIntExtra("edad",2000);
        final String str_dni = intent.getStringExtra("dni");
        dni.setText(str_dni);
        nombre.setText(intent.getStringExtra("nombre"));
        apellido.setText(intent.getStringExtra("apellido"));
        edad.setText(String.valueOf(age));

        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Realm realm = Realm.getDefaultInstance();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        RealmResults<Persona> personas = realm.where(Persona.class).equalTo("dni", str_dni).findAll();
                        personas.setValue("nombre", nombre.getText().toString());
                        personas.setValue("apellido", apellido.getText().toString());
                        personas.setValue("edad",Integer.parseInt(edad.getText().toString()));
                    }
                });
                finish();
            }
        });
    }
}