package com.mart.eindproject.util;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mart.eindproject.PokemonListFragment;
import com.mart.eindproject.R;
import com.mart.eindproject.models.Pokemon;
import com.mart.eindproject.tasks.DownloadImageTask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {
    private ArrayList<Pokemon> pokemons;

    public PokemonAdapter(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;

        Log.e("test", "yeeet");
    }

    @NonNull
    @Override
    public PokemonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view =  layoutInflater.inflate(R.layout.pokemon_card, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.id.setText(String.valueOf(pokemons.get(position).getId()));
        holder.name.setText(pokemons.get(position).getName());
        holder.itemView.setOnClickListener(new OnClickPokemonListener(pokemons.get(position)));
        new DownloadImageTask((ImageView) holder.img).execute(pokemons.get(position).getPicture());
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, id;
        private ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.pokemon_id);
            name = (TextView) itemView.findViewById(R.id.pokemon_name);
            img = (ImageView) itemView.findViewById(R.id.pokemon_image);
        }
    }

    public void addPokemon(Pokemon pokemon){
        this.pokemons.add(pokemon);
        notifyDataSetChanged();
    }

}
