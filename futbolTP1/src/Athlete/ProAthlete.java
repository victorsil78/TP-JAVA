package Athlete;

import Dice.Dice;
import Game.JsonHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.List;

public final class ProAthlete extends Athlete implements JsonHandler {
    private final String athlete = "Professional";


    //region Constructor

    public ProAthlete() {
    }

    public ProAthlete(String name, String lastName, int age) {
        super(name, lastName, age, 300, new Dice(0,1,2,0,1,2));

    }



    //endregion
    //region Getters

    public String getAthlete() {
        return athlete;
    }



    //endregion
    //region Setters



    //endregion
    //region ToString
    @Override
    public String toString() {
        return super.toString() +
                "Professional Athlete{" +
                "athlete='" + athlete + '\'' +

                '}';
    }
    //endregion


    //regionMethods

    @Override
    public void save(List<Object> proAthletes, String fileName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        BufferedWriter writer = null;
        try{
            writer = new BufferedWriter(new FileWriter(new File(fileName)));
            String json = gson.toJson(proAthletes, proAthletes.getClass());
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
    public void jsonToList(List<Object> proAthletes, String fileName){
        AthleteDeserializer deserializer = new AthleteDeserializer("athlete");
        deserializer.registerBarnType("Athlete", Athlete.class);
        deserializer.registerBarnType("ProAthlete", ProAthlete.class);
        Gson gson = new GsonBuilder().registerTypeAdapter(Athlete.class, deserializer).create();
        BufferedReader reader = null;
        List<ProAthlete> proList;
        try{
            reader = new BufferedReader(new FileReader(new File(fileName)));
            proList = gson.fromJson(reader, (new TypeToken<List<ProAthlete>>(){}.getType()));
            proAthletes.addAll(proList);
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
