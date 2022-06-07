package Athlete;

import Dice.Dice;

public final class StarAthlete extends Athlete {
    private final String athlete = "Star";
    private int hattrickCount;


    //region Constructor

    public StarAthlete(String name, String lastName, int age) {
        super(name, lastName, age, 500, new Dice(0,1,2,3,0,1));

    }



    //endregion
    //region Getters

    public String getAthlete() {
        return athlete;
    }



    //endregions
    //region Setters



    //endregions
    //region ToString

    @Override
    public String toString() {
        return super.toString() +
                "StarAthlete{" +
                "athlete='" + athlete + '\'' +

                '}';
    }


    //endregion
}
