import Futbolistas.Futbolista;
import Institucion.Institucion;

public class Jugador {
    private String nombre;
    private Institucion institucion;
    private int victorias;

    public Jugador() {
    }

    public Jugador(String nombre, Institucion institucion) {
        this.nombre = nombre;
        this.institucion = institucion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Institucion  getInstitucion() {
        return institucion;
    }

    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
    }

    public int getVictorias() {
        return victorias;
    }

    public void setVictorias(int victorias) {
        this.victorias = victorias;
    }

    public int jugar(int index){
        int goles= institucion.turnoJugador(institucion.getEquipo().get(index));
        return goles;
    }
}
