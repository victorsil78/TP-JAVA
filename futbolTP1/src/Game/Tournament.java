package Game;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Tournament {
    List<Match> matches;
    Statistics statistics;
    List<GamePlayer> players;
    int start;
    int end;

    //region Constructors
    public Tournament(List<GamePlayer> players) {
        this.matches = new ArrayList<>();
        this.statistics = new Statistics();
        this.players = players;
        this.start = LocalDate.now().getYear();
        this.end = LocalDate.now().plusYears(1).getYear();
    }

    //endregion
    //region Getters
    public List<Match> getMatches() {
        return matches;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public List<GamePlayer> getPlayers() {
        return players;
    }

    public int getInicio() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    //endregion
    //region Methods
            //Recibe lista de gameplayers (reales y bots) y genera fixture ida y vuelta de sus clubes
    public void fixtureGenerate(List<GamePlayer> players) {

        System.out.println("\nSEASON " + start + "-" + end);
        start++;
        end++;
        int rounds = players.size() - 1;
        int matchesPerRound = players.size() / 2;

        for (int round = 0; round < rounds; round++) {
            for (int match = 0; match < matchesPerRound; match++) {
                int home = (round + match) % (players.size() - 1);
                int away = (players.size() - 1 - match + round) % (players.size() - 1);
                if (match == 0) {
                    away = players.size() - 1;
                }
                matches.add(new Match(players.get(home), players.get(away)));
            }
        }
        for (int round = 0; round < rounds; round++) {
            for (int match = 0; match < matchesPerRound; match++) {
                int home = (round + match) % (players.size() - 1);
                int away = (players.size() - 1 - match + round) % (players.size() - 1);
                if (match == 0) {
                    away = players.size() - 1;
                }
                matches.add(new Match(players.get(away), players.get(home)));
            }
        }
        System.out.println("\nTournament Teams\n");
        for (var i : getPlayers()) {
            System.out.println(i.getClub().getName());
        }
    }
            //Muestra todos los partidos "ya jugados" con sus resultados
    public void matchesResults() {
        int index = 1;
        for (var i : getMatches()) {
            if (i.isPlay()) {
                System.out.println("----------------------------------------------------------");
                System.out.println("Fecha-" + index + "- " + i.getPlayer1().getClub().getName() + " Vs " + i.getPlayer2().getClub().getName());
                System.out.println(i.getResultPlayer1() + " - " + i.getResultPlayer2());

                index++;
            }
        }
    }
            ////Muestra todos los partidos "por jugar"
    public void nextMatches() {

        for (var i : getMatches()) {
            if (!i.isPlay()) {
                System.out.println(i.getPlayer1().getClub().getName() + " Vs " + i.getPlayer2().getClub().getName());

            }
        }
    }

    //endregion
}
