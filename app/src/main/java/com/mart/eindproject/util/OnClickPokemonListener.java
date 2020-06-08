package com.mart.eindproject.util;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;

import com.mart.eindproject.PokemonListFragment;
import com.mart.eindproject.PokemonViewFragment;
import com.mart.eindproject.R;
import com.mart.eindproject.models.Pokemon;

import androidx.fragment.app.FragmentActivity;

public class OnClickPokemonListener implements  View.OnClickListener {

    private Pokemon pokemon;

    public OnClickPokemonListener(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    @Override
    public void onClick(View v) {
        FragmentActivity activity = getActivity(v);
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new PokemonViewFragment(pokemon.getId())).addToBackStack(null).commit();
    }

    private FragmentActivity getActivity(View v) {
        Context context = v.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof FragmentActivity) {
                return (FragmentActivity)context;
            }
            context = ((ContextWrapper)context).getBaseContext();
        }
        return null;
    }
}
