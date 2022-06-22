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

public class Game implements JsonHandler {
    private Menu menu;

    //region Constructors
    public Game() {
        this.menu = new Menu();
    }

    //endregion
    //region Getters
    public Menu getMenu() {
        return menu;
    }
//endregion
    //regionMethods
            //Emite pausa
    public static void pause(int mellisec) {
        try {
            Thread.sleep(mellisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Guarda una lista cargada a un json.
    @Override
    public void save(List<Object> game, String fileName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Se crea una instancia de Gson customizada con GsonBuilder. SetPretty, mejora el formato para que sea visualmente mas agradable
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(new File(fileName))); // Se carga el buffer de escritura con un FileWriter, de nombre pasado por parametro.
            String json = gson.toJson(menu, menu.getClass()); // ToJson serializa la lista recibida y la devuelve como string.
            writer.write(json);//Se escribe el string json en el archivo.
        } catch (IOException e) {
            e.printStackTrace();
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
    public void jsonToList(List<Object> game, String fileName) {
        AthleteDeserializer deserializer = new AthleteDeserializer("athlete"); // crea instancia de deserializador
        deserializer.registerBarnType("AmateurAthlete", AmateurAthlete.class); // Se agregan al mapa las posibles clases del json
        deserializer.registerBarnType("Athlete", Athlete.class);
        deserializer.registerBarnType("ProAthlete", ProAthlete.class);
        deserializer.registerBarnType("StarAthlete", StarAthlete.class);
        Gson gson = new GsonBuilder().registerTypeAdapter(Athlete.class, deserializer).create(); // crea instancia de gson con el tipo de adapatador deserializador que creamos
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File(fileName))); // carga buffer de lectura con archivo
            game = gson.fromJson(reader, (new TypeToken<List<Game>>() {
            }.getType())); // fromJson recibe el archivo y la clase en la que deserializara, Y lo retorna a la lista.
        } catch (FileNotFoundException e) {
            System.out.println(Messages.FILE_NOT_FOUND.getMsg());
        } catch (IOException e) {
            e.printStackTrace();
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
            //llama a main menu
    public void GameStart() {
        menu.mainMenu();
    }

    //endregion

}