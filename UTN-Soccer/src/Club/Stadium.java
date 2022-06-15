package Club;

import Game.Messages;

public class Stadium {
    private String name;
    private int capacity;
    private double ticketValue;
    private int level;
    private int upgradeCost;

    //region Constructors

    public Stadium(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.ticketValue = 0.025;
        this.level = 1;
        this.upgradeCost = 1000;
    }

    //endregion
    //region Getters

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getTicketValue() {
        return ticketValue;
    }

    public int getLevel() {
        return level;
    }

    public int getUpgradeCost() {
        return upgradeCost;
    }

    //endregion
    //region Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setTicketValue(int ticketValue) {
        this.ticketValue = ticketValue;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setUpgradeCost(int upgradeCost) {
        this.upgradeCost = upgradeCost;
    }

    //endregion
    //region ToString

    @Override
    public String toString() {
        return "Club.Stadium{" +
                "name='" + name + '\'' + "\n" +
                ", capacity=" + capacity + "\n" +
                ", ticketValue=" + ticketValue + "\n" +
                ", level=" + level + "\n" +
                ", upgradeCost=" + upgradeCost + "\n" +
                '}';
    }

    //endregion
    //region Methods
            //Devuelve monto de tickets*capacidad
    public double ticketsIncome(){
        return this.capacity*this.ticketValue;
    }
            //Setea atributos del estadio
    public boolean stadiumUpgrade(){
        if(level < 3){
            setLevel(getLevel() + 1);
            setCapacity(getCapacity() + 10000);
            setUpgradeCost(getUpgradeCost() + 10000);
            return true;
        }else{

            return false;
        }
    }
    //endregion
}
