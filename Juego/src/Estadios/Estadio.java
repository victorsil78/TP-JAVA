package Estadios;

public class Estadio {
    private String nombre;
    private int capacidad;
    private int valorEntrada;
    private int nivel;
    private int costoProxNivel;

    public Estadio() {
    }

    public Estadio(String nombre, int capacidad, int valorEntrada) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.valorEntrada = valorEntrada;
        this.nivel = 1;
        this.costoProxNivel = 1500000;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getValorEntrada() {
        return valorEntrada;
    }

    public void setValorEntrada(int valorEntrada) {
        this.valorEntrada = valorEntrada;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getCostoProxNivel() {
        return costoProxNivel;
    }

    public void setCostoProxNivel(int costoProxNivel) {
        this.costoProxNivel = costoProxNivel;
    }

    public int recaudacionEntradas(){
        int total = this.capacidad * this.valorEntrada;
        return total;
   }

   public void mejorarEstadio(){
       if (nivel < 3) {
           setNivel(getNivel() + 1);
           setCapacidad(getCapacidad() + 15000);
           setCostoProxNivel(getCostoProxNivel() + 1500000);
       }else{
           System.out.println("Tu estadio ya obtuvo su maxima capacidad");
       }
   }
}
