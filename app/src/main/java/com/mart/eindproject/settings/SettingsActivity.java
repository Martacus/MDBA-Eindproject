package com.mart.eindproject.settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.os.Bundle;

import com.mart.eindproject.MainActivity;
import com.mart.eindproject.R;
import com.mart.eindproject.SettingsFragment;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();

    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1){
            finish();
        }
        else {
            Intent intentSettings = new Intent(this, MainActivity.class);
            startActivity(intentSettings);
            finish();
        }
    }
}
