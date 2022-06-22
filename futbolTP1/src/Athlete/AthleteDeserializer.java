package Athlete;

import Club.Stadium;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

//Clase deserializadora que implementa interfaz de la libreria Gson

public class AthleteDeserializer implements JsonDeserializer {
    private String athleteTypeElementName;
    private Gson gson;
    private Map<String, Class<? extends Athlete>> athleteTypeRegistry;
    // el signo ? significa que es una clase no especificada, que extiende de Athlete.

    public AthleteDeserializer(String athleteTypeElementName) {
        this.athleteTypeElementName = athleteTypeElementName;
        this.gson = new Gson();
        this.athleteTypeRegistry = new HashMap<>();
    }

    //Este metodo se usa para cargar el mapa de las clases que extienden de la abrstracta Athleyte
    public void registerBarnType(String athleteTypeName, Class<? extends Athlete> athleteType) {
        athleteTypeRegistry.put(athleteTypeName, athleteType);
    }


    // Se sobreescribe el unico metodo de la interfaz JsonDeserializer
    // En nuestro caso solo utilizamos el primer parametro que se le pasa. Los otros dos no los necesitamos.
    // Tira una excepcion de Error de sintaxis en caso de que este mal el json.
    @Override
    public Athlete deserialize(JsonElement json, Type typeOft, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject athleteObject = json.getAsJsonObject(); // se toma el parametro json como Objeto Json
        JsonElement athleteTypeElement = athleteObject.get(athleteTypeElementName); // del objeto se toma el atributo Athlete
        String athleteType = String.valueOf(athleteTypeElement); // Se transforma el elemento a un string.
        if(athleteType == "Amateur"){
            return gson.fromJson(athleteObject, AmateurAthlete.class); // se retorna el json deserializado en la clase hija correspondiente.
        }else if(athleteType == "Professional"){
            return gson.fromJson(athleteObject, ProAthlete.class);
        }else{
            return gson.fromJson(athleteObject, StarAthlete.class);
        }

    }
}
