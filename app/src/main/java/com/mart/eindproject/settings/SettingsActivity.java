package com.mart.eindproject.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mart.eindproject.R;
import com.mart.eindproject.SettingsFragment;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();

    }
}
