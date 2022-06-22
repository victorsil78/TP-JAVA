package Game;


import Athlete.AmateurAthlete;
import Athlete.Athlete;
import Athlete.ProAthlete;
import Athlete.StarAthlete;
import Athlete.AthleteDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Tournament implements JsonHandler{
    List<Match> matches;
    Statistics statistics;
    List<GamePlayer> players;
    int counter;
    int start;
    int end;

    //region Constructors
    public Tournament(List<GamePlayer> players) {
        this.matches = new ArrayList<>();
        this.statistics = new Statistics();
        this.players = players;
        this.counter = 0;
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

    public int getCounter() {return counter;}

    public int getInicio() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public void setCounter(int counter) {this.counter = counter;}


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

    //Guarda una lista cargada a un json.
    @Override
    public void save(List<Object> tournament, String fileName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Se crea una instancia de Gson customizada con GsonBuilder. SetPretty, mejora el formato para que sea visualmente mas agradable
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(new File(fileName))); // Se carga el buffer de escritura con un FileWriter, de nombre pasado por parametro.
            String json = gson.toJson(tournament, tournament.getClass()); // ToJson serializa la lista recibida y la devuelve como string.
            writer.write(json);//Se escribe el string json en el archivo.
        } catch (IOException e) {
            System.out.println(e);;
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    // Se pasan datos del json a una lista vacia
    @Override
    public void jsonToList(List<Object> tournament, String fileName) {
        AthleteDeserializer deserializer = new AthleteDeserializer("athlete"); // crea instancia de deserializador
        deserializer.registerBarnType("AmateurAthlete", AmateurAthlete.class); // Se agregan al mapa las posibles clases del json
        deserializer.registerBarnType("Athlete", Athlete.class);
        deserializer.registerBarnType("ProAthlete", ProAthlete.class);
        deserializer.registerBarnType("StarAthlete", StarAthlete.class);
        Gson gson = new GsonBuilder().registerTypeAdapter(Athlete.class, deserializer).create(); // crea instancia de gson con el tipo de adapatador deserializador que creamos
        BufferedReader reader = null;
        List<Tournament> tournamentList = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(new File(fileName))); // carga buffer de lectura con archivo
            tournamentList = gson.fromJson(reader, (new TypeToken<List<Tournament>>() {}.getType())); // fromJson recibe el archivo y la clase en la que deserializara, Y lo retorna a la lista.
            tournament.addAll(tournamentList);
        } catch (FileNotFoundException e) {
            System.out.println(Messages.FILE_NOT_FOUND.getMsg());
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "matches=" + matches +
                ", statistics=" + statistics +
                ", players=" + players +
                ", counter=" + counter +
                ", start=" + start +
                ", end=" + end +
                '}';
    }

    //endregion
}
