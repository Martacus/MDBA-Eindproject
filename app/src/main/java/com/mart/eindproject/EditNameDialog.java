package com.mart.eindproject;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.mart.eindproject.models.Pokemon;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

public class EditNameDialog extends AppCompatDialogFragment {
    private Pokemon pokemon;
    private EditText name;
    private PokemonViewFragment fragment;

    public EditNameDialog(Pokemon pokemon, PokemonViewFragment fragment) {
        this.pokemon = pokemon;
        this.fragment = fragment;
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_edit_name, null));
        builder.setPositiveButton("Save", (dialog, id) -> {
            this.pokemon.setName(name.getText().toString());
            fragment.populateData(pokemon);
        });
        builder.setNegativeButton("Cancel", (dialog, id) -> Objects.requireNonNull(EditNameDialog.this.getDialog()).cancel());

        View view = inflater.inflate(R.layout.dialog_edit_name, null);
        builder.setView(view);
        name = view.findViewById(R.id.pokemon_name);

        name.setText(pokemon.getName());

        return builder.create();
    }
}
