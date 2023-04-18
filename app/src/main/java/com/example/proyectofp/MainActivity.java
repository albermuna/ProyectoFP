package com.example.proyectofp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.proyectofp.clasespojo.Centros;
import com.example.proyectofp.clasespojo.Citas;
import com.example.proyectofp.clasespojo.Doctores;
import com.example.proyectofp.clasespojo.Pacientes;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    GestionBBDD gestion;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conectarBBDD();
        gestion = new GestionBBDD(this);


        Doctores doctor = new Doctores("12345678A", "Juan",  "Cardiología", new ArrayList<Citas>(), "contraseña");
        databaseReference.child("Doctores").child(doctor.getDni()).setValue(doctor);

// Escribe un objeto Paciente en la ubicación de la base de datos correspondiente
        Pacientes paciente = new Pacientes("12345678B", "María", 18, new ArrayList<Citas>(), "contraseña");
        databaseReference.child("Pacientes").child(paciente.getDni()).setValue(paciente);

// Escribe un objeto Centro en la ubicación de la base de datos correspondiente
        Centros centro = new Centros("1", "Hospital Central", "Calle Falsa 123", "Madrid", new ArrayList<Doctores>(), new ArrayList<Pacientes>(), new ArrayList<Citas>());
        databaseReference.child("Centros").child(centro.getUid()).setValue(centro);

// Escribe un objeto Cita en la ubicación de la base de datos correspondiente
        Citas cita = new Citas("1", "19/05/2023 18:00", doctor, paciente, centro);
        databaseReference.child("Citas").child(cita.getUid()).setValue(cita);
    }

    private void conectarBBDD() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}