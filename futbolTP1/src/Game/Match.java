package Game;

import Club.Club;
import com.sun.source.tree.BreakTree;

import java.util.List;
import java.util.Locale;

public class Match implements Comparable<Integer> {
    private GamePlayer player1;
    private GamePlayer player2;
    private int resultPlayer1;
    private int resultPlayer2;
    private boolean play = false;

    //region Constructor
    public Match(GamePlayer player1, GamePlayer player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    //endregion
    //region Getters
    public GamePlayer getPlayer1() {
        return player1;
    }

    public GamePlayer getPlayer2() {
        return player2;
    }

    public int getResultPlayer1() {
        return resultPlayer1;
    }

    public int getResultPlayer2() {
        return resultPlayer2;
    }

    public boolean isPlay() {
        return play;
    }

    //endregion
    //region Methods
            //Se cobra la localia, y comprueba si ambos users son random, si es asi, no muestra desarrollo del partido, caso contrario si
    public void playMatch(Statistics statistics, List<Club> clubs) {
        player1.getClub().homeIncomes();
        if (this.player1.getName() == "PC" && this.player2.getName() == "PC") {
            randomPlayersMatch(statistics);
        } else {
            System.out.println(player1.getClub().getName().toUpperCase(Locale.ROOT) + " vs " + player2.getClub().getName().toUpperCase(Locale.ROOT) + "\n");
            Game.pause(2000);
            playersMatch(statistics);
        }
        this.play = true;
    }
            //llama a jugar a cada uno de los atletas de cada equipo, y setea las estadisticas comprobando tambien, quien es el ganador del partido
    public void playersMatch(Statistics statistics) {
        int p1Goals;
        int p2Goals;
        int p1TotalGoals = 0;
        int p2TotalGoals = 0;
        for (int i = 0; i < 3; i++) {
            p1Goals = player1.play(i);
            statistics.scorersRanking(player1.getClub().getTeam().get(i));
            statistics.hattricksRanking(player1.getClub().getTeam().get(i));
            p1TotalGoals += p1Goals;
            p2Goals = player2.play(i);
            statistics.scorersRanking(player2.getClub().getTeam().get(i));
            statistics.hattricksRanking(player2.getClub().getTeam().get(i));
            p2TotalGoals += p2Goals;
        }

        System.out.println("\n MATCH RESULT ");
        int total = resultComparator(p1TotalGoals, p2TotalGoals, statistics);
        if (total == 0) {
            System.out.println(Messages.TIE.getMsg() + p1TotalGoals + " - " + p2TotalGoals);
        } else if (total < 1) {
            System.out.println(player2.getClub().getName() + Messages.WON.getMsg() + " by " + p2TotalGoals + " - " + p1TotalGoals);
        } else {
            System.out.println(player1.getClub().getName() + Messages.WON.getMsg() + " by " + p1TotalGoals + " - " + p2TotalGoals);
        }
        Game.pause(2000);
    }
            //Hace tirar los dados de todos los atletas del partido, seteando estadisticas sin mostrar desarrollo
    public void randomPlayersMatch(Statistics statistics) {
        int p1Goals;
        int p2Goals;
        int p1TotalGoals = 0;
        int p2TotalGoals = 0;

        for (int i = 0; i < 3; i++) {
            p1Goals = player1.randomPlay(i);
            statistics.scorersRanking(player1.getClub().getTeam().get(i));
            statistics.hattricksRanking(player1.getClub().getTeam().get(i));
            p1TotalGoals += p1Goals;
            p2Goals = player2.randomPlay(i);
            statistics.scorersRanking(player2.getClub().getTeam().get(i));
            statistics.hattricksRanking(player2.getClub().getTeam().get(i));
            p2TotalGoals += p2Goals;
        }

        int total = resultComparator(p1TotalGoals, p2TotalGoals, statistics);
    }
            //compara 2 enteros y arroja resultado
    @Override
    public int compareTo(Integer player2) {
        Integer player1 = 0;
        return player1.compareTo(player2);
    }
            //compara 2 enteros y arroja 3 posibles resultados a las estadisticas
    public int resultComparator(Integer p1TotalGoals, Integer p2TotalGoals, Statistics statistics) {
        int total = p1TotalGoals.compareTo(p2TotalGoals);
        resultPlayer1 = p1TotalGoals;
        resultPlayer2 = p2TotalGoals;

        if (total == 0) {
            player1.getClub().setScore(player1.getClub().getScore() + 1);
            player2.getClub().setScore(player2.getClub().getScore() + 1);
        } else if (total < 1) {
            player2.getClub().setScore(player2.getClub().getScore() + 3);
            player1.getClub().setScore(player1.getClub().getScore() + 0);
        } else {
            player1.getClub().setScore(player1.getClub().getScore() + 3);
            player2.getClub().setScore(player2.getClub().getScore() + 0);

        }
        statistics.tournamentRanking(player1.getClub());
        statistics.tournamentRanking(player2.getClub());
        return total;
    }
//endregion
}
