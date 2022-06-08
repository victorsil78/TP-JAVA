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

    public int getHattrickCount() {
        return hattrickCount;
    }

    //endregions
    //region Setters

    public void setHattrickCount(int hattrickCount) {
        this.hattrickCount = hattrickCount;
    }


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
    //region Methods

    @Override
    public int throwDices (){
        int diceSide = super.getDice().RandomSide();
        setGoalCount(getGoalCount() + diceSide);
        setMarketValue(getMarketValue()+((getMarketValue()*(diceSide*5)/100)));
        if(diceSide==3){
            setHattrickCount(getHattrickCount() + 1);
        }
        return diceSide;
    }

    //endregion
}
//agregar 2 futbolistas mas