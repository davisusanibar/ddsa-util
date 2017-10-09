package com.susanibar.domains.configuration;

public class User {
    private int codigo;
    private String nonbre;
    private String apellido;
    private int edad;

    public User() {
    }

    public User(int codigo, String nonbre, String apellido, int edad) {
        this.codigo = codigo;
        this.nonbre = nonbre;
        this.apellido = apellido;
        this.edad = edad;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNonbre(String nonbre) {
        this.nonbre = nonbre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNonbre() {
        return nonbre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }
}
