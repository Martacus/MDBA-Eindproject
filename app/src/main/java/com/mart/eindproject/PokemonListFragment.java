package com.mart.eindproject;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mart.eindproject.models.Pokemon;
import com.mart.eindproject.util.PokemonAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PokemonListFragment extends Fragment {

    private ArrayList<Pokemon> pokemons;
    private RecyclerView recyclerView;
    private View rootView = null;
    private PokemonAdapter adapter = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_pokemon_list, container, false);
        this.rootView = rootView;
        this.pokemons = new ArrayList<Pokemon>();

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        getPokemons();

        adapter = new PokemonAdapter(this.pokemons);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    public void getPokemons() {
        RequestQueue queue = Volley.newRequestQueue(rootView.getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://pokeapi.co/api/v2/pokemon",
                response -> {
                    try {
                        JSONObject obj = new JSONObject(response);
                        JSONArray array = obj.getJSONArray("results");
                        for(int i=0; i<array.length(); i++) {
                            JSONObject x = array.getJSONObject(i);
                            String url = x.getString("url");
                            addPokemon(url);
                            Log.d("tag", response);
                        }
                    } catch(JSONException err) {

                    }
                },
                error -> DoSomething()
        );
        queue.add(stringRequest);
    }

    private void addPokemon(String url) {
        RequestQueue queue = Volley.newRequestQueue(rootView.getContext());
        StringRequest req = new StringRequest(Request.Method.GET, url,
                response -> {
                    adapter.addPokemon(new Pokemon(response));
                    Log.d("tag", "yeeete");
                },
                error -> {
                    Log.d("taag", String.valueOf(error));
                });
        queue.add(req);
    }


    public void DoSomething() {

    }
}
