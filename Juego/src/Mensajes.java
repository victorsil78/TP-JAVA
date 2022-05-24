public enum Mensajes {

    SALDO_INSUF ("su saldo es insuficiente"),
    TURNO ("Es el turno de");


    private String s;

    Mensajes(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }
}
