package com.mart.eindproject.listeners;

import android.view.View;

import com.mart.eindproject.models.Pokemon;
import com.mart.eindproject.tasks.SendImageTask;

public class OnClickSendImageListener implements View.OnClickListener {

    private Pokemon pokemon;


    public OnClickSendImageListener(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    @Override
    public void onClick(View v) {
        new SendImageTask(v).execute(pokemon.getPicture());
    }

}
