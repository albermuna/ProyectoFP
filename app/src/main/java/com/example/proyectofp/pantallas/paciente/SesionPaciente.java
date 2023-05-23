package com.example.proyectofp.pantallas.paciente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.proyectofp.R;

public class SesionPaciente extends AppCompatActivity {
    View pedirCitaButton, proximasCitasButton, informesButton;
    ImageView homeButton, settingsButton;
    String nombrePaciente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesion_paciente);
        pedirCitaButton = findViewById(R.id.pedirCitaButton);
        proximasCitasButton = findViewById(R.id.proximasCitasButton);
        informesButton = findViewById(R.id.informesButton);
        homeButton = findViewById(R.id.homeButton);
        settingsButton = findViewById(R.id.settingsButton);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SesionPaciente.class);
                //intent.putExtra("Pacientes", paciente);
                startActivity(intent);
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingsPaciente.class);
                //intent.putExtra("Pacientes", paciente);
                startActivity(intent);
            }
        });

        pedirCitaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PedirCitaPaciente.class);
                //intent.putExtra("Pacientes", paciente);
                startActivity(intent);
            }
        });

        proximasCitasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProximasCitasPaciente.class);
                //intent.putExtra("Pacientes", paciente);
                startActivity(intent);
            }
        });

        informesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InformesPaciente.class);
                //intent.putExtra("Pacientes", paciente);
                startActivity(intent);
            }
        });

    }
}