package com.example.proyectofp.clasespojo;

public class Citas {

    private String uid;
    private String fechaHora;
    private Doctores doctor;
    private Pacientes paciente;
    private Centros centro;

    public Citas(String uid, String fechaHora, Doctores doctor, Pacientes paciente, Centros centro) {
        this.uid = uid;
        this.fechaHora = fechaHora;
        this.doctor = doctor;
        this.paciente = paciente;
        this.centro = centro;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Doctores getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctores doctor) {
        this.doctor = doctor;
    }

    public Pacientes getPaciente() {
        return paciente;
    }

    public void setPaciente(Pacientes paciente) {
        this.paciente = paciente;
    }

    public Centros getCentro() {
        return centro;
    }

    public void setCentro(Centros centro) {
        this.centro = centro;
    }
}
