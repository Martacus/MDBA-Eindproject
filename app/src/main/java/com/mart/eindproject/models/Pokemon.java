package com.mart.eindproject.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Pokemon {

    private String name;
    private String picture;

    public Pokemon(String response){
        JSONObject object;

        try {
            object  = new JSONObject(response);
            this.name = object.getString("name");
            this.picture = object.getJSONObject("sprites").getString("front_default");


        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }
    }

    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }
}
