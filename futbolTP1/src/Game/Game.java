package Game;

import Athlete.Athlete;
import Athlete.StarAthlete;
import Athlete.AmateurAthlete;
import Athlete.ProAthlete;
import Athlete.AthleteDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.List;

public class Game implements JsonHandler{
    public static void pause (int mellisec){
        try {
            Thread.sleep(mellisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //regionMethods

    @Override
    public void save(List<Object> game, String fileName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        BufferedWriter writer = null;
        try{
            writer = new BufferedWriter(new FileWriter(new File(fileName)));
            String json = gson.toJson(game, game.getClass());
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
    public void jsonToList(List<Object> game, String fileName){
        AthleteDeserializer deserializer = new AthleteDeserializer("athlete");
        deserializer.registerBarnType("AmateurAthlete", AmateurAthlete.class);
        deserializer.registerBarnType("Athlete", Athlete.class);
        deserializer.registerBarnType("ProAthlete", ProAthlete.class);
        deserializer.registerBarnType("StarAthlete", StarAthlete.class);
        Gson gson = new GsonBuilder().registerTypeAdapter(Athlete.class, deserializer).create();
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader(new File(fileName)));
            game = gson.fromJson(reader, (new TypeToken<List<Game>>(){}.getType()));
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
