package com.example.proyectofp;

import android.content.Context;


import com.example.proyectofp.clasespojo.Centros;
import com.example.proyectofp.clasespojo.Citas;
import com.example.proyectofp.clasespojo.Doctores;
import com.example.proyectofp.clasespojo.Pacientes;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;


public class GestionBBDD {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public GestionBBDD(Context context) {
        FirebaseApp.initializeApp(context);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void introducirDoctor (Doctores doc) {
        databaseReference.child("Doctores").child(doc.getDni()).setValue(doc);
    }

    public void introducirPaciente (Pacientes pac) {
        databaseReference.child("Pacientes").child(pac.getDni()).setValue(pac);
    }

    public void introducirCentro (Centros cen) {
        databaseReference.child("Centros").child(cen.getUid()).setValue(cen);
    }

    public void introducirCita (Citas cit) {
        databaseReference.child("Citas").child(cit.getUid()).setValue(cit);
    }

    public void modificarDatoSimple (String tipoDato, String referencia, String datoModificar, String datoModificado) {
        databaseReference.child(tipoDato).child(referencia).child(datoModificar).setValue(datoModificado);
    }

    public void modificarDatoObjeto (String tipoDato, String referencia, String datoModificar, List<Object> listaObjeto) {
        databaseReference.child(tipoDato).child(referencia).child(datoModificar).setValue(listaObjeto);
    }









}
