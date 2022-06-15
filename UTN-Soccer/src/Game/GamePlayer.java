package Game;

import Club.Club;

import java.util.List;

public class GamePlayer {
    private String name;
    private Club club;

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


    //endregion
    //region Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setClub(Club club) {
        this.club = club;
    }


    //endregion
    //region ToString

    @Override
    public String toString() {
        return "GamePlayer{" +
                "name='" + name + '\'' + "\n" +
                ", club=" + club.getName() + "\n" +
                '}' + "\n";
    }

    //endregion
    //region Methods
            //Cuenta la cantidad de clubes de una lista
    public int clubCounter(List<Club> clubList) {
        int total = 0;
        for (var i : clubList) {
            total++;
        }
        return total;
    }
            //Toma una lista, cuenta la cantidad de clubes y devuelve uno random
    public Club randomClub(List<Club> clubList) {
        int total = clubCounter(clubList);
        int random = (int) (Math.random() * total);
        Club club = clubList.get(random);
        clubList.remove(club);
        return club;
    }
            //Llama al turno del jugador del equipo y "muestra resultados"
    public int play(int index) {
        return club.playerTurn(club.getTeam().get(index));
    }
            //Llama al "arrojar dados" de la clase Athlete "sin mostrar resultados"
    public int randomPlay(int index) {
        return club.getTeam().get(index).throwDices();
    }

    //endregion

}
