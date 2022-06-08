package Athlete;

import Dice.Dice;

public final class AmateurAthlete extends Athlete{
    private final String athlete = "Amateur";


    //region Constructor

    public AmateurAthlete(String name, String lastName, int age) {
        super(name, lastName, age, 100, new Dice(0,1,0,1,0,1));

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
                "Amateur Athlete{" +
                "athlete='" + athlete + '\'' +
                '}';
    }
    //endregion

}
