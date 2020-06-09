package com.mart.eindproject.listeners;

import android.view.View;

import com.mart.eindproject.EditNameDialog;
import com.mart.eindproject.PokemonViewFragment;
import com.mart.eindproject.models.Pokemon;
import com.mart.eindproject.util.PokemonUtil;

public class OnClickChangeNameListener  implements  View.OnClickListener {

    Pokemon pokemon;
    PokemonViewFragment fragment;

    public OnClickChangeNameListener(Pokemon pokemon, PokemonViewFragment fragment) {
        this.pokemon = pokemon;
        this.fragment = fragment;
    }

    @Override
    public void onClick(View v) {
        EditNameDialog dialog = new EditNameDialog(pokemon, fragment);
        dialog.show(PokemonUtil.getActivity(v).getSupportFragmentManager(), "Edit Pokemon");
    }
}
