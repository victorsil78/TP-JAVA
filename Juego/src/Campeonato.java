import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Campeonato {

    private int cantidadDeFechas;
    private int cantidadEquipos;

    public void jugadorXcuatro (Jugador uno, Jugador dos, Jugador tres, Jugador cuatro, int cantidadDeFechas){

        for (int i = 0; i == cantidadDeFechas; i++){
            Partido p1 = new Partido(uno, dos);
            Partido p2 = new Partido(tres, cuatro);
            Partido p3 = new Partido(uno, tres);
            Partido p4 = new Partido(dos, cuatro);
            Partido p5 = new Partido(uno, cuatro);
            Partido p6 = new Partido(dos, tres);
            Partido p7 = new Partido(cuatro, uno);
            Partido p8 = new Partido(tres, dos);
            Partido p9 = new Partido(cuatro, dos);
            Partido p10 = new Partido(tres, uno);
            Partido p11 = new Partido(dos, uno);
            Partido p12 = new Partido(cuatro, tres);
        }
    }


}
