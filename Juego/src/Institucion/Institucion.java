package Institucion;

import Estadios.Estadio;
import Futbolistas.Futbolista;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Institucion {
    private String club;
    private int puntos;
    List<Futbolista> equipo;
    private Estadio estadio;
    private int finanzas;

    public Institucion(String club, Estadio estadio, int finanzas) {
        this.club = club;
        this.estadio = estadio;
        this.finanzas = finanzas;
        this.equipo = new ArrayList<>();
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public List <Futbolista> getEquipo() {
        return equipo;
    }

    public void setEquipo(List<Futbolista> equipo) {
        this.equipo = equipo;
    }

    public Estadio getEstadio() {
        return estadio;
    }

    public void setEstadio(Estadio estadio) {
        this.estadio = estadio;
    }

    public int getFinanzas() {
        return finanzas;
    }

    public void setFinanzas(int finanzas) {
        this.finanzas = finanzas;
    }

    public void subirNivelEstadio(){
        if(this.finanzas>=this.estadio.getCostoProxNivel()){
           setFinanzas(getFinanzas()-this.estadio.getCostoProxNivel());
           this.estadio.mejorarEstadio();
        }else{
            System.out.println("El dinero de la institucion es insufuciente");
        }
    }
    public void agregarFutbolista(Futbolista futbolista){
        equipo.add(futbolista);
    }


    public int turnoJugador (Futbolista futbolista){
        int resultado;
        System.out.println("\nTurno de " + getClub() + "\n");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        resultado = futbolista.tirarDado();
        System.out.println("El futbolista: " + futbolista.getApellido() + " metio " + resultado + " goles\n");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return resultado;
    }
}
