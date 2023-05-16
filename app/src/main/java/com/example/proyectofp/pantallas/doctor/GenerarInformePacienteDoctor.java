package com.example.proyectofp.pantallas.doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.proyectofp.R;

public class GenerarInformePacienteDoctor extends AppCompatActivity {

    ImageView home;
    ImageView settings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generar_informe_paciente_doctor);
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