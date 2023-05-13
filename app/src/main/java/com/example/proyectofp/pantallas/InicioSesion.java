package com.example.proyectofp.pantallas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.proyectofp.R;
import com.example.proyectofp.pantallas.doctor.SesionDoctor;
import com.example.proyectofp.pantallas.paciente.SesionPaciente;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class InicioSesion extends AppCompatActivity {
    TextView nuevoUsuario;
    View entrarButton;
    EditText dni, contraseña;
    Button entrar;
    Button crearUsuario;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        contraseña = findViewById(R.id.contraseñaEditText);
        dni = findViewById(R.id.dniEditText);
        entrarButton = findViewById(R.id.entrarButton);
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
                                builder.setTitle("Inicio sesión");
                                builder.setMessage("El dni o la contraseña son incorrectos, vuelva a intentarlo.");
                                builder.setPositiveButton("Aceptar", null);
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        } else {
                            // Error al comprobar las credenciales, mostrar un mensaje de error
                            builder.setTitle("Inicio sesión");
                            builder.setMessage("No se ha podido realizar el inicio de sesión, disculpe las molestias.");
                            builder.setPositiveButton("Aceptar", null);
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    }
                });
    }

    private void validarPaciente(String dniUsuario, String contraseñaUsuario) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
                                builder.setTitle("Inicio sesión");
                                builder.setMessage("La contraseña es incorrecta, vuelva a intentarlo.");
                                builder.setPositiveButton("Aceptar", null);
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        } else {
                            // Error al comprobar las credenciales, mostrar un mensaje de error
                            builder.setTitle("Inicio sesión");
                            builder.setMessage("El dni que ha introducido no pertenece a ningún usuario.");
                            builder.setPositiveButton("Aceptar", null);
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    }
                });
    }


}