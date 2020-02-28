package com.mart.eindproject.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Pokemon {

    private String name;
    private String picture;
    private String type1;
    private String type2;
    private int id;

    public Pokemon(String response){
        JSONObject object;

        try {
            object  = new JSONObject(response);
            this.name = object.getString("name");
            this.picture = object.getJSONObject("sprites").getString("front_default");
            this.id = object.getInt("id");

            JSONArray pokemonTypes = object.getJSONArray("types");
            type1 = pokemonTypes.getJSONObject(0).getJSONObject("type").getString("name");
            if(pokemonTypes.length() > 1){
                type2 = pokemonTypes.getJSONObject(1).getJSONObject("type").getString("name");
            } else {
                type2 = null;
            }

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

    public int getId() {
        return id;
    }

    public String getType1() {
        return type1;
    }

    public String getType2() {
        return type2;
    }
}
