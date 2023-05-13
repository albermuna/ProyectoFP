package com.example.proyectofp.pantallas.doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.proyectofp.R;

public class IntroducirDniPacienteDoctor extends AppCompatActivity {

    ImageView home, settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introducir_dni_paciente_doctor);

        home = findViewById(R.id.homeButton);
        settings = findViewById(R.id.settingsButton);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}