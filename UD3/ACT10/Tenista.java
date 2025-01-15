package eje10;

import java.io.Serializable;

public class Tenista implements Serializable {
    private static final long serialVersionUID = 1L;

    private String apellido;
    private int altura;

    // Constructor público
    public Tenista(String apellido, int altura) {
        this.apellido = apellido;
        this.altura = altura;
    }

    // Métodos getter
    public String getApellido() {
        return apellido;
    }

    public int getAltura() {
        return altura;
    }

    // Métodos setter
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
}
