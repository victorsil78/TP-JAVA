package Dice;

import java.util.ArrayList;
import java.util.List;


public class Dice {

        private List<Integer> dice;

        public Dice(int side1, int side2, int side3, int side4, int side5, int side6) {
            dice = new ArrayList<>();
            dice.add(side1);
            dice.add(side2);
            dice.add(side3);
            dice.add(side4);
            dice.add(side5);
            dice.add(side6);
        }

        public List<Integer> getDice() {
            return dice;
        }
                //Arroja una cara random del dado y muestra su valor
        public int RandomSide(){
            return dice.get((int)(Math.random()*5));
        }

        @Override
        public String toString() {
            return "Dice{" +
                    "dice=" + dice +
                    '}';
        }
}

