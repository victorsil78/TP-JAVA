package Futbolistas;
import Dados.Dado;

import java.util.ArrayList;

public class Futbolista {

    private String nombre;
    private String apellido;
    private int edad;
    private int valorDeMercado;
    private Dado dado;
    private int cantGoles;
    private int hatTrick;

    public Futbolista() {
    }

    public Futbolista(String nombre, String apellido, int edad, int valorDeMercado, Dado dado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.valorDeMercado = valorDeMercado;
        this.dado = dado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getValorDeMercado() {
        return valorDeMercado;
    }

    public void setValorDeMercado(int valorDeMercado) {
        this.valorDeMercado = valorDeMercado;
    }

    public Dado getDado() {
        return dado;
    }

    public void setDado(Dado dado) {
        this.dado = dado;
    }

    public int getCantGoles() {
        return cantGoles;
    }

    public void setCantGoles(int cantGoles) {
        this.cantGoles = cantGoles;
    }

    public int getHatTrick() {
        return hatTrick;
    }

    public void setHatTrick(int hatTrick) {
        this.hatTrick = hatTrick;
    }

    public int tirarDado(){

        int caraObtenida = dado.caraRandom();
        setCantGoles(getCantGoles()+caraObtenida);

        if (caraObtenida==3){
            setHatTrick(getHatTrick()+1);
        }
        if (caraObtenida==1){
            setValorDeMercado(getValorDeMercado()+((getValorDeMercado()*5)/100));
        }else if (caraObtenida==2){
            setValorDeMercado(getValorDeMercado()+((getValorDeMercado()*10)/100));
        }else if (caraObtenida==3){
            setValorDeMercado(getValorDeMercado()+((getValorDeMercado()*15)/100));
        }

        return caraObtenida;
    }
}
