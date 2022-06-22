package Club;

import Athlete.Athlete;
import Game.JsonHandler;
import Game.Messages;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import Athlete.AthleteDeserializer;
import Athlete.AmateurAthlete;
import Athlete.ProAthlete;
import Athlete.StarAthlete;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import static Game.Game.pause;

public class Club implements JsonHandler {
    private String name;
    private int score;
    private List<Athlete> team;
    private Stadium stadium;
    private int finances;

    //region Constructors

    public Club(String name, Stadium stadium) {
        this.name = name;
        this.score = 0;
        this.team = new ArrayList<>();
        this.stadium = stadium;
        this.finances = 0;
    }

    //endregion
    //region Getters

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public List<Athlete> getTeam() {
        return team;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public int getFinances() {
        return finances;
    }

    //endregion
    //region Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setTeam(List<Athlete> team) {
        this.team = team;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }

    public void setFinances(int finances) {
        this.finances = finances;
    }

    //endregion
    //region ToString

    @Override
    public String toString() {
        return "Club{" +
                "name='" + name + '\'' + "\n" +
                ", score=" + score + "\n" +
                ", finances=" + finances + "\n" +
                '}'+ "\n" + "\n" + "\n" ;
    }

    //endregion
    //region Methods
            //Llama a la comprobacion de finanzas y si se cumplen las condiciones mejora el estadio
    public void raiseStadiumLevel() {
        int upgradeCost = stadium.getUpgradeCost();
        boolean finances = financesValidator(upgradeCost);
        if (finances) {
            boolean level = stadium.stadiumUpgrade();
            if (level) {
                setFinances(getFinances() - upgradeCost);
                System.out.println(Messages.OP_DONE.getMsg());
            } else {
                System.out.println(Messages.MAX_STADIUM_LEVEL.getMsg());
            }
        } else {
            System.out.println(Messages.INSUF_BALANCE.getMsg());
        }
    }
            //Comprueba si finanzas son mayores al dato recibido
    public boolean financesValidator(int costAmount){
        if(finances>costAmount){
            return true;
        }else{
            return false;
        }
    }
            //Agrega atleta (sin comprobaciones) para generar archivo Json inicial
    public void addAthlete(Athlete athlete){
        team.add(athlete);
    }
            //Comprueba finanzas y espacio disponible en equipo para adherir nuevo jugador
    public boolean buyAthlete(Athlete athlete){
        boolean validation = financesValidator(athlete.getMarketValue());
        boolean confirm;
        if (validation) {
            if(team.size()<3){
                setFinances(getFinances()-athlete.getMarketValue());
                team.add(athlete);
                System.out.println(Messages.OP_DONE.getMsg());
                confirm = true;
            }else{
                System.out.println(Messages.FULL_TEAM.getMsg());
                confirm = false;
            }
        }else{
            System.out.println(Messages.INSUF_BALANCE.getMsg());
            confirm = false;
        }
        return confirm;
    }
            //Permite que un jugador arroje un dado y muestra resultado
    public int playerTurn (Athlete athlete) {
        int result;
        System.out.println("\n"+Messages.TURN.getMsg() + getName().toUpperCase(Locale.ROOT));
        result = athlete.throwDices();
        System.out.println(athlete.getLastName() + Messages.SCORED_BY.getMsg() + "["+result+"]" + " Goal(s)");
        pause(3000);
        return result;
    }
            //Si el equipo no esta vacio, elimina atleta y suma a las finanzas el valor de mercado del atleta
    public void sellAthlete(Athlete athlete) {
        if (team.size() > 0) {
            setFinances(getFinances() + athlete.getMarketValue());
            getTeam().remove(athlete);
        } else {
            System.out.println("You don´t have any athlete to sell");
        }
    }
            //Muestra cada jugador del equipo con un index para poder seleccionar desde el menu
    public void checkTeam(){
        int index = 1;
        for (Athlete f: team){
            System.out.println(index + "-"+f.getName() + " " + f.getLastName());
            index++;
        }
    }
            //Suma a las finanzas el monto recaudado de tickets*capacidad del equipo
    public void homeIncomes(){
        setFinances(getFinances() + (int)getStadium().ticketsIncome());
    }

    //Guarda una lista cargada a un json.
    @Override
    public void save(List<Object> clubsToJson, String fileName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Se crea una instancia de Gson customizada con GsonBuilder. SetPretty, mejora el formato para que sea visualmente mas agradable
        BufferedWriter writer = null;
        try{
            writer = new BufferedWriter(new FileWriter(new File(fileName))); // Se carga el buffer de escritura con un FileWriter, de nombre pasado por parametro.
            String json = gson.toJson(clubsToJson, clubsToJson.getClass()); // ToJson serializa la lista recibida y la devuelve como string.
            writer.write(json);//Se escribe el string json en el archivo.
        }catch (IOException e){
            e.printStackTrace();
        } finally {
            if(writer != null){
                try{
                    writer.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

    }

    // Se pasan datos del json a una lista vacia
    @Override
    public void jsonToList(List<Object> clubsToList, String fileName){
        AthleteDeserializer deserializer = new AthleteDeserializer("athlete"); // crea instancia de deserializador
        deserializer.registerBarnType("AmateurAthlete", AmateurAthlete.class); // Se agregan al mapa las posibles clases del json
        deserializer.registerBarnType("Athlete", Athlete.class);
        deserializer.registerBarnType("ProAthlete", ProAthlete.class);
        deserializer.registerBarnType("StarAthlete", StarAthlete.class);
        Gson gson = new GsonBuilder().registerTypeAdapter(Athlete.class, deserializer).create(); // crea instancia de gson con el tipo de adapatador deserializador que creamos
        BufferedReader reader = null;
        List<Club> clubList;
        try{
            reader = new BufferedReader(new FileReader(new File(fileName))); // carga buffer de lectura con archivo
            clubList = gson.fromJson(reader, (new TypeToken<List<Club>>(){}.getType())); // fromJson recibe el archivo y la clase en la que deserializara, Y lo retorna a la lista.
            clubsToList.addAll(clubList); //Se agrega la lista cargada a la lista vacia recibida por parametro.
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(reader != null){
                    reader.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    //endregion

}
