package Game;

import Athlete.Athlete;
import Club.Club;
import Athlete.StarAthlete;

import java.util.*;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toMap;

public class Statistics implements Comparable<Integer> {
    private HashMap<String, Integer> positions;
    private HashMap<String, Integer> scorers;
    private HashMap<String, Integer> hattricks;

    //region Constructor
    public Statistics() {
        this.positions = new HashMap<>();
        this.scorers = new HashMap<>();
        this.hattricks = new HashMap<>();
    }

    //endregion
    //region Getters
    public HashMap<String, Integer> getPositions() {
        return positions;
    }

    public HashMap<String, Integer> getScorers() {
        return scorers;
    }

    public HashMap<String, Integer> getHattricks() {
        return hattricks;
    }

    //endregion
    //region Setters
    public void setPositions(HashMap<String, Integer> positions) {
        this.positions = positions;
    }

    public void setScorers(HashMap<String, Integer> scorers) {
        this.scorers = scorers;
    }

    public void setHattricks(HashMap<String, Integer> hattricks) {
        this.hattricks = hattricks;
    }

    //endregion
    //region toString
    @Override
    public String toString() {
        return "Statistics{" +
                "positions=" + positions +
                ", scorers=" + scorers +
                ", hattricks=" + hattricks +
                '}';
    }

    //endregion
    //region Methods
            //Agrega resultados de cada club al hash map "postions"
    public void tournamentRanking(Club club) {
        this.positions.put(club.getName(), club.getScore());
    }
            //Agrega resultados de cada atleta al hash map "scorers"
    public void scorersRanking(Athlete athlete) {
        this.scorers.put(athlete.getLastName(), athlete.getGoalCount());
    }
            //Agrega resultados de cada atleta al hash map "hattricks"
    public void hattricksRanking(Athlete athlete) {
        if (athlete instanceof StarAthlete) {
            this.hattricks.put(athlete.getLastName(), ((StarAthlete) athlete).getHattrickCount());
        }
    }
            //Compara 2 enteros y devuelve resultado
    @Override
    public int compareTo(Integer player2) {
        Integer player1 = 0;
        return player1.compareTo(player2);
    }
            //Ordena el hashmap de mayor a menor segun el resultado(Value)
    public HashMap<String, Integer> sort(HashMap<String, Integer> map) {
        HashMap<String, Integer> sortedMap = map.entrySet().stream()
                .sorted(comparingInt(e -> -1 * e.getValue()))
                .collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> {
                            throw new AssertionError();
                        },
                        LinkedHashMap::new
                ));
        return sortedMap;
    }
            //Muestra el resultado ordenado
    public void showPositions(HashMap<String, Integer> hashmap) {
        hashmap = sort(hashmap);
        hashmap.forEach(
                (k, v) -> System.out.println(k.toString() + " " + v.toString())
        );
    }
//endregion
}



