import Dados.Dado;
import Estadios.Estadio;
import Futbolistas.Futbolista;
import Institucion.Institucion;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       Dado dadoAmateur = new Dado(0,1,0,1,0,1);
       Dado dadoProfesional = new Dado(0,1,2,0,1,2);
       Dado dadoEstrella = new Dado(0,1,2,3,0,1);

       Futbolista futAmateur = new Futbolista("Carlos","Zapata",28,500000,dadoAmateur);
       Futbolista futAmateur1 = new Futbolista("Matias","Gonzales",2,500000,dadoAmateur);
       Futbolista futEstrella = new Futbolista("Lionel","Messi",3,1500000,dadoEstrella);
       Futbolista futEstrella1 = new Futbolista("Cristiano","Ronaldo",36,1500000,dadoEstrella);
       Estadio estadio = new Estadio("estadio",30000,200);
       Institucion independiente = new Institucion("Independiente", estadio, 1000000);
       independiente.agregarFutbolista(futAmateur);
       independiente.agregarFutbolista(futAmateur1);
       independiente.agregarFutbolista(futEstrella);
       Institucion boca = new Institucion("Boca", estadio, 1000000);
       boca.agregarFutbolista(futAmateur);
       boca.agregarFutbolista(futAmateur1);
       boca.agregarFutbolista(futEstrella1);
       Jugador jugador1 = new Jugador("Marcos", independiente);
       Jugador jugador2 = new Jugador("Carlos" ,boca);
       Partido partido = new Partido(jugador1,jugador2);
       MenuGeneral menu = new MenuGeneral(jugador1,jugador2);
      // menu.menuGeneral(jugador1,jugador2);
       //System.out.println(independiente.getEquipo().get(2).getCantGoles());
       //System.out.println(independiente.getEquipo().get(2).getHatTrick());
       //System.out.println(independiente.getEquipo().get(2).getValorDeMercado());
    }
}

