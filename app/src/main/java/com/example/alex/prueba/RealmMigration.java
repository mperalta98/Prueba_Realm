package com.example.alex.prueba;

import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.FieldAttribute;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

public class RealmMigration implements io.realm.RealmMigration {


    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

        RealmSchema schema = realm.getSchema();

        if (oldVersion == 0) {
            RealmObjectSchema personSchema = schema.get("Persona");

            // Combine 'firstName' and 'lastName' in a new field called 'fullName'
            personSchema
                    .addField("nombreCompleto", String.class, FieldAttribute.REQUIRED)
                    .transform(new RealmObjectSchema.Function() {
                        @Override
                        public void apply(DynamicRealmObject obj) {
                            obj.set("nombreCompleto", obj.getString("nombre") + obj.getString("apellidos"));
                        }
                    })
                    .removeField("nombre")
                    .removeField("apellidos");
            oldVersion++;
        }

        if (oldVersion == 1) {

        }
    }
}
