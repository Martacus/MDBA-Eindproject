package com.mart.eindproject.util;

import com.mart.eindproject.R;

import java.util.HashMap;
import java.util.Map;

public class PokemonUtil {

    public static Map<String, Integer> typeColor;

    public static Integer getTypeColor(String type){
        if(typeColor == null){
            typeColor = new HashMap<>();
            typeColor.put("water", R.color.waterType);
            typeColor.put("steel", R.color.steelType);
            typeColor.put("rock", R.color.rockType);
            typeColor.put("psychic", R.color.psychicType);
            typeColor.put("poison", R.color.poisonType);
            typeColor.put("normal", R.color.normalType);
            typeColor.put("ice", R.color.iceType);
            typeColor.put("ground", R.color.groundType);
            typeColor.put("grass", R.color.grassType);
            typeColor.put("ghost", R.color.ghostType);
            typeColor.put("flying", R.color.flyingType);
            typeColor.put("fire", R.color.fireType);
            typeColor.put("fighting", R.color.fightingType);
            typeColor.put("fairy", R.color.fairyType);
            typeColor.put("electric", R.color.electricType);
            typeColor.put("dragon", R.color.dragonType);
            typeColor.put("dark", R.color.darkType);
            typeColor.put("bug", R.color.bugType);
        }

        if(!typeColor.containsKey(type)){
            return typeColor.get("normal");
        }
        else{
            return typeColor.get(type);
        }
    }

}
