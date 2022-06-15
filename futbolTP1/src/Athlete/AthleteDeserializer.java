package Athlete;

import Club.Stadium;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class AthleteDeserializer implements JsonDeserializer {
    private String athleteTypeElementName;
    private Gson gson;
    private Map<String, Class<? extends Athlete>> athleteTypeRegistry;

    public AthleteDeserializer(String athleteTypeElementName) {
        this.athleteTypeElementName = athleteTypeElementName;
        this.gson = new Gson();
        this.athleteTypeRegistry = new HashMap<>();
    }

    public void registerBarnType(String athleteTypeName, Class<? extends Athlete> athleteType) {
        athleteTypeRegistry.put(athleteTypeName, athleteType);
    }


    @Override
    public Athlete deserialize(JsonElement json, Type typeOft, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject athleteObject = json.getAsJsonObject();
        JsonElement athleteTypeElement = athleteObject.get(athleteTypeElementName);
        String athleteType = String.valueOf(athleteTypeElement);
        if(athleteType == "Amateur"){
            return gson.fromJson(athleteObject, AmateurAthlete.class);
        }else if(athleteType == "Professional"){
            return gson.fromJson(athleteObject, ProAthlete.class);
        }else{
            return gson.fromJson(athleteObject, StarAthlete.class);
        }

    }
}
