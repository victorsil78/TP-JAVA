public class Partido {
    private Jugador jugador1;
    private Jugador jugador2;

    public Partido(Jugador jugador1, Jugador jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
    }

    public void jugarPartido(){
    int golesJ1;
    int golesJ2;
    int resultJ1 = 0;
    int resultJ2 = 0;
    golesJ1 = jugador1.jugar(0);
    resultJ1 += golesJ1;
    golesJ2 = jugador2.jugar(0);
    resultJ2 += golesJ2;
    golesJ1 = jugador1.jugar(1);
    resultJ1 += golesJ1;
    golesJ2 =  jugador2.jugar(1);
    resultJ2 += golesJ2;
    golesJ1 = jugador1.jugar(2);
    resultJ1 += golesJ1;
    golesJ2 = jugador2.jugar(2);
    resultJ2 += golesJ2;
        System.out.println("RESULTADO \n");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    if (resultJ1 > resultJ2){
        System.out.println("Gano " + jugador1.getInstitucion().getClub() + " por " + resultJ1  + " a " + resultJ2);
    }else if (resultJ2 > resultJ1){
        System.out.println("Gano " + jugador2.getInstitucion().getClub() + " por " + resultJ2  + " a " + resultJ1);
    }else{
        System.out.println("El partido termino en empate" + resultJ1 + resultJ2);
    }
    }

}
