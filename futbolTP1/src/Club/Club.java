package Club;

import Athlete.Athlete;
import Game.JsonHandler;
import Game.Messages;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
                ", team=" + team + "\n" +
                ", stadium=" + stadium + "\n" +
                ", finances=" + finances + "\n" +
                '}'+ "\n" + "\n" + "\n" ;
    }

    //endregion
    //region Methods

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
            System.out.println(Messages.INSUF_BALANCE.getMsg() + " Or ");
        }
    }

    public boolean financesValidator(int costAmount){// TERNARIO
        if(finances>costAmount){
            return true;
        }else{
            return false;
        }
    }

    public void addAthlete(Athlete athlete){
        team.add(athlete);
    }

    public void buyAthlete(Athlete athlete){
        boolean validation = financesValidator(athlete.getMarketValue());
        if (validation) {
            if(team.size()<3){
            team.add(athlete);
                System.out.println(Messages.OP_DONE.getMsg());
            }else{
                System.out.println(Messages.FULL_TEAM.getMsg());
            }
        }else{
            System.out.println(Messages.INSUF_BALANCE.getMsg());
        }
    }

    public int playerTurn (Athlete athlete) {
        int result;
        //System.out.println("\nTurno de " + getClub() + "\n");
        System.out.println(Messages.TURN.getMsg() + getName() + "\n");
        pause(2000);
        result = athlete.throwDices();
        System.out.println("El futbolista: " + athlete.getLastName() + " metio " + result + " goles\n");
        pause(2000);
        return result;
    }

    public void sellAthlete(Athlete athlete){
        setFinances(getFinances()+ athlete.getMarketValue());
        getTeam().remove(athlete);
    }

    public void checkTeam(){
        int index = 1;
        System.out.println(this.getTeam());
        for (Athlete f: team){
            System.out.println(index + "-"+f.getName() + " " + f.getLastName());
            index++;
        }
    }


    @Override
    public void save(List<Object> clubsToJson, String fileName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        BufferedWriter writer = null;
        try{
            writer = new BufferedWriter(new FileWriter(new File(fileName)));
            String json = gson.toJson(clubsToJson, clubsToJson.getClass());
            writer.write(json);
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

    @Override
    public void jsonToList(List<Object> clubsToList, String fileName){
        AthleteDeserializer deserializer = new AthleteDeserializer("athlete");
        deserializer.registerBarnType("AmateurAthlete", AmateurAthlete.class);
        deserializer.registerBarnType("Athlete", Athlete.class);
        deserializer.registerBarnType("ProAthlete", ProAthlete.class);
        deserializer.registerBarnType("StarAthlete", StarAthlete.class);
        Gson gson = new GsonBuilder().registerTypeAdapter(Athlete.class, deserializer).create();
        BufferedReader reader = null;
        List<Club> clubList;
        try{
            reader = new BufferedReader(new FileReader(new File("ClubsOriginal.json")));
            clubList = gson.fromJson(reader, (new TypeToken<List<Club>>(){}.getType()));
            clubsToList.addAll(clubList);
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
