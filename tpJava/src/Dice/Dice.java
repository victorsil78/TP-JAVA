package Dice;

import java.util.ArrayList;
import java.util.List;


public class Dice {

        private List<Integer> dice;

        public Dice(int cara1, int cara2, int cara3, int cara4, int cara5, int cara6) {
            dice = new ArrayList<>();
            dice.add(cara1);
            dice.add(cara2);
            dice.add(cara3);
            dice.add(cara4);
            dice.add(cara5);
            dice.add(cara6);
        }

        public List<Integer> getDice() {
            return dice;
        }

        public int caraRandom (){
            int cara = dice.get((int)(Math.random()*5));
            return cara;
        }

        @Override
        public String toString() {
            return "Dice{" +
                    "dice=" + dice +
                    '}';
        }
}

