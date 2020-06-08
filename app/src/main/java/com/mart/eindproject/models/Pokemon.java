package com.mart.eindproject.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

public class Pokemon {

    private String name;
    private String picture;
    private String type1;
    private String type2;
    private int id;

    private static String IMG_URL= "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/";

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

    private Bitmap getImage(String id) {
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(IMG_URL+String.valueOf(this.id)+".png").openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }
}
