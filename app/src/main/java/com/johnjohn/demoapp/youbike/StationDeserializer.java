package com.johnjohn.demoapp.youbike;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by john on 2015/7/25.
 */
public class StationDeserializer implements JsonDeserializer<Station> {
    @Override
    public Station deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jObj=jsonElement.getAsJsonObject();
        Station station = new Station();
        station.name = jObj.get("sna").getAsString();
        station.totalParks = jObj.get("tot").getAsInt();
        station.availableParks = jObj.get("sbi").getAsInt();
        return station;
    }
}
