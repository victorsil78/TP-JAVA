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

    //Guarda una lista cargada a un json.
    @Override
    public void save(List<Object> proAthletes, String fileName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Se crea una instancia de Gson customizada con GsonBuilder. SetPretty, mejora el formato para que sea visualmente mas agradable
        BufferedWriter writer = null;
        try{
            writer = new BufferedWriter(new FileWriter(new File(fileName))); // Se carga el buffer de escritura con un FileWriter, de nombre pasado por parametro.
            String json = gson.toJson(proAthletes, proAthletes.getClass()); // ToJson serializa la lista recibida y la devuelve como string.
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
    public void jsonToList(List<Object> proAthletes, String fileName){
        AthleteDeserializer deserializer = new AthleteDeserializer("athlete"); // crea instancia de deserializador
        deserializer.registerBarnType("Athlete", Athlete.class); // Se agregan al mapa las posibles clases del json
        deserializer.registerBarnType("ProAthlete", ProAthlete.class);
        Gson gson = new GsonBuilder().registerTypeAdapter(Athlete.class, deserializer).create(); // crea instancia de gson con el tipo de adapatador deserializador que creamos
        BufferedReader reader = null;
        List<ProAthlete> proList;
        try{
            reader = new BufferedReader(new FileReader(new File(fileName))); // carga buffer de lectura con archivo
            proList = gson.fromJson(reader, (new TypeToken<List<ProAthlete>>(){}.getType())); // fromJson recibe el archivo y la clase en la que deserializara, Y lo retorna a la lista.
            proAthletes.addAll(proList); //Se agrega la lista cargada a la lista vacia recibida por parametro.
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
