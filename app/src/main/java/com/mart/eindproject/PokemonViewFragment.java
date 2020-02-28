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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mart.eindproject.models.Pokemon;
import com.mart.eindproject.tasks.DownloadImageTask;

import org.json.JSONException;
import org.json.JSONObject;

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

    public PokemonViewFragment(){
        this(1);
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

    public void makeRequest(){
        //Get all the different components on the page
        final TextView textView = rootView.findViewById(R.id.pokemon_name);

        //Setup a request
        RequestQueue queue = Volley.newRequestQueue(rootView.getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://pokeapi.co/api/v2/pokemon/" + pokemonID,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Pokemon pokemon = new Pokemon(response);
                    textView.setText(pokemon.getName());

                    new DownloadImageTask((ImageView) rootView.findViewById(R.id.imageView)).execute(pokemon.getPicture());
                    swipeRefreshLayout.setRefreshing(false);
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    textView.setText("Something went wrong, please reload again.");
                    swipeRefreshLayout.setRefreshing(false);

                }
            }
        );
        queue.add(stringRequest);
    }
}
