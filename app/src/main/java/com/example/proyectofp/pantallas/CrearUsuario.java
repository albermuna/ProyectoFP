package com.example.proyectofp.pantallas;

import androidx.appcompat.app.AppCompatActivity;

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

    private String nombreObj, dniObj, contraseñaObj, especialidadObj;
    private Integer edadObj;
    private EditText nombreApellidos, dni,  contraseña, variable;
    private TextView variableTV;
    private RadioButton doctorRB, pacienteRB;
    private RadioGroup radioGroup;
    private Button añadir;
    private Boolean esDoctor = null;

    GestionBBDD gestion = new GestionBBDD(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);
        nombreApellidos = findViewById(R.id.nombreApellidosEditText);
        dni = findViewById(R.id.dniEditText);
        contraseña = findViewById(R.id.contraseñaEditText);
        doctorRB = findViewById(R.id.doctorRB);
        pacienteRB = findViewById(R.id.pacienteRB);
        variable = findViewById(R.id.variableEditText);
        variableTV = findViewById(R.id.variableTextView);
        añadir = findViewById(R.id.añadirButton);
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int item = group.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) radioGroup.findViewById(item);
                String seleccionado = (String) radioButton.getText();

                if (seleccionado.equals("Doctor")) {
                    esDoctor = true;
                    variable.setHint("Especialidad");
                    variableTV.setText("Especialidad:");
                }else {
                    esDoctor = false;
                    variable.setHint("Edad");
                    variableTV.setText("Edad:");
                }
            }
        });

        añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(comprobarDatosRellenados()) {
                    ArrayList<Citas> listaCitas = new ArrayList<Citas>();
                    nombreObj = nombreApellidos.getText().toString();
                    dniObj = dni.getText().toString();
                    contraseñaObj = contraseña.getText().toString();
                    if (esDoctor) {
                        especialidadObj = variable.getText().toString();
                        Doctores doc = new Doctores(dniObj, nombreObj, especialidadObj, listaCitas, contraseñaObj);
                        gestion.introducirDoctor(doc);
                    } else {
                        edadObj = Integer.parseInt(variable.getText().toString());
                        Pacientes pac = new Pacientes(dniObj, nombreObj, edadObj, listaCitas, contraseñaObj);
                        gestion.introducirPaciente(pac);
                    }

                }
            }
        });



    }

    private boolean comprobarDatosRellenados() {
        Boolean valido = true;
        String nombreApellidosComp, dniComp, contraseñaComp;
        nombreApellidosComp = nombreApellidos.getText().toString();
        dniComp = dni.getText().toString();
        contraseñaComp = contraseña.getText().toString();
        if (esDoctor) {
            String especialidadComp = variable.getText().toString();
            if (especialidadComp.equals("")) {
                variable.setError("Required");
                valido = false;
            }
        }else {
            Integer edadComp = Integer.parseInt(variable.getText().toString());
            if (edadComp.equals("")) {
                variable.setError("Required");
                valido = false;
            }
        }
        if (nombreApellidosComp.equals("")) {
            nombreApellidos.setError("Required");
            valido = false;
        }
        if (dniComp.equals("")) {
            dni.setError("Required");
            valido = false;
        }
        if (contraseñaComp.equals("")) {
            contraseña.setError("Required");
            valido = false;
        }
        return valido;
    }
}