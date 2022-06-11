package Game;

import Club.Club;

import java.util.List;

public class GamePlayer {
    private String name;
    private Club club;
    private int wins;

    //region Constructors

    public GamePlayer(String name, Club club) {
            this.name = name;
            this.club = club;
        }

    public GamePlayer(List<Club> clubList) {
                this.name = "PC";
                this.club = randomClub(clubList);
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
    public int clubCounter(List<Club> clubList){
        int total = 0;
        for (var i : clubList){
            total ++;
        }
        return total;
    }

    public Club randomClub (List<Club> clubList){
        int total = clubCounter(clubList);
        int random = (int)(Math.random()*total);
        Club club = clubList.get(random);
        clubList.remove(club);
        return club;
    }
    //endregion

}
