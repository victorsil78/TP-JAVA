import java.util.Scanner;

public class MenuGeneral {
    private Jugador jugador1;
    private Jugador jugador2;
    private Scanner scanner;
    private int respuesta;

    public MenuGeneral(Jugador jugador1, Jugador jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.scanner = new Scanner(System.in);
    }


    public int menuJugador(Jugador jugador) {
        int respuesta;
        do {
            System.out.println("-----------------------------------");
            System.out.println("1- Jugar");
            System.out.println("2- Opciones");
            System.out.println("0- Abandonar Juego");

            respuesta = scanner.nextInt();

            switch (respuesta) {
                case 1:
                    break;
                case 2:
                    do {
                        System.out.println("-----------------------------------");
                        System.out.println("1- Ver Finanzas");
                        System.out.println("2- Ver Equipo");
                        System.out.println("3- Mejorar Estadio");
                        System.out.println("4- Volver al menu principal");

                        respuesta = scanner.nextInt();
                        Fuente: https://www.iteramos.com/pregunta/25225/Java-Limpiar-la-consola
                        switch (respuesta) {
                            case 1:
                                System.out.println("LA INSTITUCION DISPONE DE:");
                                System.out.println(jugador.getInstitucion().getFinanzas() + " DOLARES");
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 2:
                                break;
                            case 3:
                                jugador.getInstitucion().subirNivelEstadio();
                                break;
                        }
                    } while (respuesta < 3);
                    break;
                case 0:
                    break;
            }
        } while (respuesta != 0 && respuesta != 1);
        return respuesta;
    }

    public void menuGeneral(Jugador jugador1, Jugador jugador2) {
        System.out.println(jugador1.getNombre());
        int respuesta = menuJugador(jugador1);
        if (respuesta == 0) {
            System.out.println("El jugador " + jugador1.getNombre() + " abandono la partida");
            System.out.println("Gana jugador " + jugador2.getNombre());
            jugador2.setVictorias(jugador2.getVictorias() + 1);
        } else {
            System.out.println("ESPERANDO AL RIVAL \n");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (respuesta != 0) {
            System.out.println(jugador2.getNombre());
            int respuesta2 = menuJugador(jugador2);
            if (respuesta2 == 0) {
                System.out.println("El jugador " + jugador2.getNombre() + " abandono la partida");
                System.out.println("Gana jugador " + jugador1.getNombre());
                jugador1.setVictorias(jugador1.getVictorias() + 1);
            }
            if (respuesta == 1 && respuesta2 == 1) {
                System.out.println("INICIA EL PARTIDO");
                Partido partido = new Partido(jugador1, jugador2);
                partido.jugarPartido();
            }
        }
    }
}
