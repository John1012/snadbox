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
public class YouBikeDeserializer implements JsonDeserializer<YouBike> {

    @Override
    public YouBike deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();
        JsonElement result=jsonObject.get("result");
        int size=result.getAsJsonObject().get("count").getAsInt();

        // Delegate the deserialization to the context
        Station[] stations=context.deserialize(result.getAsJsonObject().get("results"), Station[].class);

        YouBike youbike=new YouBike();
        youbike.count=size;
        if(stations!=null) {
            for (Station station : stations)
                youbike.stations.add(station);
        }
        return youbike;
    }
}
