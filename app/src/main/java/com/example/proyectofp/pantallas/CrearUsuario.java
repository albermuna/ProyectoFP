package com.example.proyectofp.pantallas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.proyectofp.GestionBBDD;
import com.example.proyectofp.R;
import com.example.proyectofp.clasespojo.Citas;
import com.example.proyectofp.clasespojo.Doctores;
import com.example.proyectofp.clasespojo.Pacientes;

import java.util.ArrayList;

public class CrearUsuario extends AppCompatActivity {

    private String nombreObj, dniObj, contraseñaObj, confirmarContraseña;

    private EditText nombreApellidos, dni,  contraseña, confirmaContraseña;


    private View crearUsuarioButton;


    GestionBBDD gestion = new GestionBBDD(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        nombreApellidos = findViewById(R.id.nombreApellidosEditText);
        dni = findViewById(R.id.dniEditText);
        contraseña = findViewById(R.id.contraseñaEditText);
        confirmaContraseña = findViewById(R.id.confirmaContraseñaEditText);

        crearUsuarioButton = findViewById(R.id.crearUsuarioButton);


        crearUsuarioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nombreObj = nombreApellidos.getText().toString();
                dniObj = dni.getText().toString();
                contraseñaObj = contraseña.getText().toString();
                if(nombreObj.equals("") || dniObj.equals("") || contraseñaObj.equals("")) {
                    comprobarDatosRellenados();
                } else {


                    builder.setTitle("Añadir usuario");
                    builder.setMessage("El usuario se ha añadido correctamente");
                    builder.setPositiveButton("Aceptar", null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    Intent i = new Intent(getApplicationContext(), InicioSesion.class);
                    startActivity(i);
                }
            }
        });
    }

    private void comprobarDatosRellenados() {
        String nombreApellidosComp, dniComp, contraseñaComp, variableComp;
        nombreApellidosComp = nombreApellidos.getText().toString();
        dniComp = dni.getText().toString();
        contraseñaComp = contraseña.getText().toString();

        if (nombreApellidosComp.equals("")) {
            nombreApellidos.setError("Required");
        }
        if (dniComp.equals("")) {
            dni.setError("Required");
        }
        if (contraseñaComp.equals("")) {
            contraseña.setError("Required");
        }
    }
}