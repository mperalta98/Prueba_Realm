package com.example.alex.prueba;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;


import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;

public class ListPersonaAdapter extends RealmBaseAdapter<Persona> implements ListAdapter {

    public ListPersonaAdapter(@Nullable OrderedRealmCollection<Persona> data) {
        super(data);
    }

    private static class ViewHolder{
        TextView dni, nombre, apellidos, edad;
        ImageButton btn_delete;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_persona,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.dni = convertView.findViewById(R.id.persona_dni);
            viewHolder.nombre = convertView.findViewById(R.id.persona_nombre);
            viewHolder.apellidos = convertView.findViewById(R.id.persona_apellido);
            viewHolder.edad = convertView.findViewById(R.id.persona_edad);
            viewHolder.btn_delete = convertView.findViewById(R.id.btn_delete);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Persona item = adapterData.get(position);
        viewHolder.dni.setText(item.getNumDni());
        viewHolder.nombre.setText(item.getnombre());
        viewHolder.apellidos.setText(item.getapellidos());
        viewHolder.edad.setText(String.valueOf(item.getedad()));
        viewHolder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(item);
            }
        });
        final View finalConvertView = convertView;
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(finalConvertView.getContext(),ModPersona.class);
                intent.putExtra("dni",item.getNumDni());
                intent.putExtra("nombre",item.getnombre());
                intent.putExtra("apellidos",item.getapellidos());
                intent.putExtra("edad",item.getedad());
                finalConvertView.getContext().startActivity(intent);
            }
        });
        return convertView;
    }

    public void delete(Persona item){
        Realm realm = io.realm.Realm.getDefaultInstance();
        final RealmResults<Persona> results = realm.where(Persona.class).equalTo("numDni",item.getNumDni()).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                results.deleteAllFromRealm();
            }
        });
        notifyDataSetChanged();
    }
}