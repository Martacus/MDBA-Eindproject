package com.mart.eindproject;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mart.eindproject.models.Pokemon;
import com.mart.eindproject.tasks.DownloadImageTask;
import com.mart.eindproject.util.PokemonUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class PokemonViewFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private final int pokemonID;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View rootView = null;

    public PokemonViewFragment(int pokemonID){
        this.pokemonID = pokemonID;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Initiate root view
        final View rootView = inflater.inflate(R.layout.pokemon_view, container, false);
        this.rootView = rootView;

        //Set the swipe layout listener
        this.swipeRefreshLayout = rootView.findViewById(R.id.swipe_layout_pokemon_view);
        swipeRefreshLayout.setOnRefreshListener(this);

        //Requesting data
        makeRequest();



        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onRefresh() {
        makeRequest();
    }

    public void populateData(Pokemon pokemon){
        //Gather the data objects
        final TextView textName = rootView.findViewById(R.id.pokemon_name);
        final TextView textType1 = rootView.findViewById(R.id.textType1);
        final TextView textType2 = rootView.findViewById(R.id.textType2);

        //Data
        int id =  pokemon.getId();
        String pokemonName = getCapitalWorld(pokemon.getName()) + " | #" + id;

        //Set Data
        textName.setText(pokemonName);

        textType1.setText(getCapitalWorld(pokemon.getType1()));
        textType1.setBackgroundColor(PokemonUtil.getTypeColor(pokemon.getType1()));

        if(pokemon.getType2() != null){
            textType2.setText(getCapitalWorld(pokemon.getType2()));
            textType2.setBackgroundColor(PokemonUtil.getTypeColor(pokemon.getType2()));
        }

        new DownloadImageTask((ImageView) rootView.findViewById(R.id.imageView)).execute(pokemon.getPicture());
        swipeRefreshLayout.setRefreshing(false);
    }

    public void makeRequest(){
        RequestQueue queue = Volley.newRequestQueue(rootView.getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://pokeapi.co/api/v2/pokemon/" + pokemonID,
                response -> {
                    Pokemon pokemon = new Pokemon(response);
                    populateData(pokemon);
                },
                error -> swipeRefreshLayout.setRefreshing(false)
        );
        queue.add(stringRequest);
    }

    public String getCapitalWorld(String s){
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
