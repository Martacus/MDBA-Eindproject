package com.mart.eindproject;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mart.eindproject.models.Pokemon;
import com.mart.eindproject.util.PokemonAdapter;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PokemonListFragment extends Fragment {

    private ArrayList<Pokemon> pokemons;
    private RecyclerView recyclerView;
    private View rootView = null;
    private PokemonAdapter adapter = null;
    private RequestQueue queue;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_pokemon_list, container, false);
        this.rootView = rootView;
        this.pokemons = new ArrayList<>();

        recyclerView = rootView.findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new PokemonAdapter(this.pokemons);
        recyclerView.setAdapter(adapter);

        if (pokemons.size() == 0) {
            getPokemons(rootView);
        }

        return rootView;
    }

    public void getPokemons(View rootView) {
        for(int i = 1; i < 152; i++){
            StringRequest req = new StringRequest(Request.Method.GET, "https://pokeapi.co/api/v2/pokemon/" + i,
                    response -> adapter.addPokemon(new Pokemon(response)),
                    Throwable::printStackTrace);
            req.setShouldCache(false);
            getRequestQueue(rootView.getContext()).add(req);
        }
    }

    public RequestQueue getRequestQueue(Context context) {
        if (queue == null) {
            queue = Volley.newRequestQueue(context);
        }

        return queue;
    }


}

