package com.example.proyectofp.pantallas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofp.R;
import com.example.proyectofp.clasespojo.Pacientes;
import com.example.proyectofp.pantallas.doctor.SesionDoctor;
import com.example.proyectofp.pantallas.paciente.SesionPaciente;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class InicioSesion extends AppCompatActivity {
    TextView nuevoUsuario;
    View entrarButton;
    EditText dni, contraseña;
    Button entrar;
    Button crearUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        contraseña = findViewById(R.id.contraseñaEditText);
        dni = findViewById(R.id.dniEditText);
        entrarButton = findViewById(R.id.entrarButton);
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
        entrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dniUsuario = dni.getText().toString();
                String contraseñaUsuario = contraseña.getText().toString().trim();

                dbRef.child("Pacientes").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            for(DataSnapshot ds: snapshot.getChildren()){
                                if(ds.child("dni").getValue().equals(dniUsuario)) {
                                    String contraseñaBaseDeDatos = ds.child("contraseña").getValue(String.class).trim();
                                    Log.d("InicioSesion", "Contraseña de la base de datos: " + contraseñaBaseDeDatos);
                                    Log.d("TextView", "Contraseña del usuario: " + contraseñaUsuario);
                                    Log.d("Prueba", "Comparacion"+(contraseñaBaseDeDatos.equals(contraseñaUsuario)));
                                    if(contraseñaBaseDeDatos.equals(contraseñaUsuario)) {
                                        Pacientes paciente = new Pacientes();
                                        paciente.setDni(ds.child("dni").getValue(String.class));
                                        paciente.setContraseña(ds.child("contraseña").getValue(String.class));
                                        Intent intent = new Intent(getApplicationContext(), SesionPaciente.class);
                                        //intent.putExtra("Pacientes", paciente);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                                    }
                                    break;
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }); // falta cerrar el paréntesis aquí
            }
        });
        nuevoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent a la página de crear Usuario
                Intent i = new Intent(getApplicationContext(), CrearUsuario.class);
                startActivity(i);
            }
        });

    }




}