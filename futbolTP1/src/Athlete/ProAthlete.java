package Athlete;

import Dice.Dice;

public final class ProAthlete extends Athlete {
    private final String athlete = "Professional";


    //region Constructor

    public ProAthlete(String name, String lastName, int age) {
        super(name, lastName, age, 300, new Dice(0,1,2,0,1,2));

    }



    //endregion
    //region Getters

    public String getAthlete() {
        return athlete;
    }



    //endregion
    //region Setters



    //endregion
    //region ToString
    @Override
    public String toString() {
        return super.toString() +
                "Professional Athlete{" +
                "athlete='" + athlete + '\'' +

                '}';
    }
    //endregion

}
//agregar 2 futbolistas mas