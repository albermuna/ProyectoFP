package com.example.proyectofp.pantallas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.proyectofp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class InicioSesion extends AppCompatActivity {
    TextView nuevoUsuario;
    EditText dni, contraseña;
    Button entrar;
    Button crearUsuario;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
        nuevoUsuario = findViewById(R.id.crearUsuarioTextView);
        contraseña = findViewById(R.id.contraseñaEditText);
        dni = findViewById(R.id.dniEditText);
        entrar = findViewById(R.id.entrarButton);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dniUsuario = dni.getText().toString();
                String contraseñaUsuario = contraseña.getText().toString();
                validarPaciente(dniUsuario, contraseñaUsuario);
                validarDoctor(dniUsuario, contraseñaUsuario);
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

    private void validarDoctor(String dniUsuario, String contraseñaUsuario) {
        db.collection("Doctores")
                .whereEqualTo("dni", dniUsuario)
                .whereEqualTo("contraseña", contraseñaUsuario)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().size() > 0) {
                                // Las credenciales son correctas, iniciar sesión como doctor
                                Intent i = new Intent(getApplicationContext(), SesionDoctor.class);
                                startActivity(i);
                            } else {
                                // Las credenciales son incorrectas, mostrar un mensaje de error
                                // ...
                            }
                        } else {
                            // Error al comprobar las credenciales, mostrar un mensaje de error
                            // ...
                        }
                    }
                });
    }

    private void validarPaciente(String dniUsuario, String contraseñaUsuario) {
        db.collection("Pacientes")
                .whereEqualTo("dni", dniUsuario)
                .whereEqualTo("contraseña", contraseñaUsuario)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().size() > 0) {
                                // Las credenciales son correctas, iniciar sesión como paciente
                                Intent i = new Intent(getApplicationContext(), SesionPaciente.class);
                                startActivity(i);
                            } else {
                                // Las credenciales son incorrectas, mostrar un mensaje de error
                                // ...
                            }
                        } else {
                            // Error al comprobar las credenciales, mostrar un mensaje de error
                            // ...
                        }
                    }
                });
    }


}