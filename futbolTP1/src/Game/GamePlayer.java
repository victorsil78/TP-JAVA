package Game;

import Club.Club;

public class GamePlayer {
    private String name;
    private Club club;
    private int wins;

    //region Constructors

    public GamePlayer(String name, Club club, int wins) {
        this.name = name;
        this.club = club;
        this.wins = wins;
    }

    public GamePlayer() {
    }
    //endregion
    //region Getters

    public String getName() {
        return name;
    }

    public Club getClub() {
        return club;
    }

    public int getWins() {
        return wins;
    }

    //endregion
    //region Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    //endregion
    //region ToString

    @Override
    public String toString() {
        return "GamePlayer{" +
                "name='" + name + '\'' + "\n" +
                ", club=" + club + "\n" +
                ", wins=" + wins + "\n" +
                '}' + "\n";
    }

    //endregion
    //region Methods
    //endregion

}
