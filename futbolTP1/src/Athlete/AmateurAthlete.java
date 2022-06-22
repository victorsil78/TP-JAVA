package Athlete;

import Dice.Dice;
import Game.JsonHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.List;

public final class AmateurAthlete extends Athlete implements JsonHandler {
    private final String athlete = "Amateur";


    //region Constructor

    public AmateurAthlete(String name, String lastName, int age) {
        super(name, lastName, age, 100, new Dice(0,1,0,1,0,1));

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
                "Amateur Athlete{" +
                "athlete='" + athlete + '\'' +
                '}';
    }
    //endregion

    //region Methods

    //Guarda una lista cargada a un json.
    @Override
    public void save(List<Object> amateurs, String fileName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Se crea una instancia de Gson customizada con GsonBuilder. SetPretty, mejora el formato para que sea visualmente mas agradable
        BufferedWriter writer = null;
        try{
            writer = new BufferedWriter(new FileWriter(new File(fileName))); // Se carga el buffer de escritura con un FileWriter, de nombre pasado por parametro.
            String json = gson.toJson(amateurs, amateurs.getClass()); // ToJson serializa la lista recibida y la devuelve como string.
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

    }//graba en Json

    // Se pasan datos del json a una lista vacia
    @Override
    public void jsonToList(List<Object> amateurs, String fileName){
        AthleteDeserializer deserializer = new AthleteDeserializer("athlete"); // crea instancia de deserializador
        deserializer.registerBarnType("Athlete", Athlete.class); // Se agregan al mapa las posibles clases del json
        deserializer.registerBarnType("AmateurAthlete", AmateurAthlete.class);
        Gson gson = new GsonBuilder().registerTypeAdapter(Athlete.class, deserializer).create(); // crea instancia de gson con el tipo de adapatador deserializador que creamos
        BufferedReader reader = null;
        List<AmateurAthlete> amateurList;
        try{
            reader = new BufferedReader(new FileReader(new File(fileName))); // carga buffer de lectura con archivo
            amateurList = gson.fromJson(reader, (new TypeToken<List<AmateurAthlete>>(){}.getType())); // fromJson recibe el archivo y la clase en la que deserializara, Y lo retorna a la lista.
            amateurs.addAll(amateurList); //Se agrega la lista cargada a la lista vacia recibida por parametro.
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
    }//Lee Json
    //endregion
}
