package Dados;

import java.util.ArrayList;
import java.util.List;

public class Dado {
    private List<Integer> dado;

    public Dado(int cara1, int cara2, int cara3, int cara4, int cara5, int cara6) {
        dado = new ArrayList<>();
        dado.add(cara1);
        dado.add(cara2);
        dado.add(cara3);
        dado.add(cara4);
        dado.add(cara5);
        dado.add(cara6);
    }

    public List<Integer> getDado() {
        return dado;
    }

    public int caraRandom (){
        int cara = dado.get((int)(Math.random()*5));
        return cara;
    }
}