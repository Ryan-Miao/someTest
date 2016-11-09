package com.test.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import java.util.Date;

/**
 * Created by rmiao on 11/8/2016.
 */
public class GsonUtils {

    public static Gson getDeserializerDateGson() {
        // Creates the json object which will manage the information received
        GsonBuilder builder = new GsonBuilder();

        // Register an adapter to manage the date types as long values
        builder.registerTypeAdapter(Date.class,
                (JsonDeserializer<Date>) (json, typeOfT, context) -> new Date(json.getAsJsonPrimitive().getAsLong())
        );

        Gson gson = builder.create();
        return gson;
    }
}
