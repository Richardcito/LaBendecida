package com.mycompany.labendecida.model;

import java.sql.Timestamp;

public class Usuario {
    private int id;
    private String email;
    private String password;
    private String nombre;
    private String rol;
    private int medicoId;
    private int especialidadId;
    private String numeroColegiado;
    private Timestamp fechaRegistro;
    private String apellido;
    private boolean estado;
    private int rol_id;

    public Usuario(int id, String email, String password, String nombre, String rol, int medicoId, int especialidadId,
            String numeroColegiado, Timestamp fechaRegistro, String apellido, boolean estado) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.rol = rol;
        this.medicoId = medicoId;
        this.especialidadId = especialidadId;
        this.numeroColegiado = numeroColegiado;
        this.fechaRegistro = fechaRegistro;
        this.apellido = apellido;
        this.estado = estado;
    }

    // Constructor
    public Usuario() {
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(int medicoId) {
        this.medicoId = medicoId;
    }

    public int getEspecialidadId() {
        return especialidadId;
    }

    public void setEspecialidadId(int especialidadId) {
        this.especialidadId = especialidadId;
    }

    public String getNumeroColegiado() {
        return numeroColegiado;
    }

    public void setNumeroColegiado(String numeroColegiado) {
        this.numeroColegiado = numeroColegiado;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void setRol(int rol) {
        this.rol_id = rol;
    }

    public int getRol_id() {
        return rol_id;
    }

    public void setRol_id(int rol_id) {
        this.rol_id = rol_id;
    }
} 