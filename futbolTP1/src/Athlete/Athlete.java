package Athlete;


import Dice.Dice;

public abstract class Athlete {
    private String name;
    private String lastName;
    private int age;
    private int marketValue;
    private int goalCount;
    private Dice dice;

    //region Constructor

    public Athlete(String name, String lastName, int age, int marketValue, Dice dice) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.marketValue = marketValue;
        this.dice = dice;
    }

    public Athlete() {
    }

    //endregion
    //region Getters

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public int getMarketValue() {
        return marketValue;
    }

    public int getGoalCount() {
        return goalCount;
    }

    public Dice getDice() {
        return dice;
    }
    //endregion
    //region Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMarketValue(int marketValue) {
        this.marketValue = marketValue;
    }

    public void setGoalCount(int goalCount) {
        this.goalCount = goalCount;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }
//endregion
    //region ToString

    @Override
    public String toString() {
        return "Athlete{" + "\n" +
                "name='" + name + '\'' + "\n" +
                "lastName='" + lastName + '\'' + "\n" +
                "age=" + age + "\n" +
                "marketValue=" + marketValue + "\n" +
                "goalCount=" + goalCount + "\n" +
                "dice=" + dice +
                '}'+ "\n" ;
    }


    //endregion
    //region Methods
           //Arroja dados y setea atributos de atleta
    public int throwDices (){
        int diceSide = dice.RandomSide();
        setGoalCount(getGoalCount() + diceSide);
        setMarketValue(getMarketValue()+((getMarketValue()*(diceSide*5)/100)));
        return diceSide;
    }
    //endregion
}


