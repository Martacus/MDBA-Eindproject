package com.mart.eindproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private Button Login;

    private Map<String, String> accounts = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        accounts.put("admin", "admintest");
        accounts.put("user", "user");

        Name = findViewById(R.id.username_text_field);
        Password = findViewById(R.id.password_field);
        Login = findViewById(R.id.login_button);

        Login.setOnClickListener(v -> validate(Name.getText().toString(), Password.getText().toString()));

        Context context=this.getApplicationContext();
        SharedPreferences settings=context.getSharedPreferences("PREFERENCES", 0);
        boolean isLogged = settings.getBoolean("isLogged", false);
        if(isLogged){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void validate(String username, String password){
        if(accounts.containsKey(username)){
            if(password.contentEquals(password)){
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                finish();
            }
        }
    }

}
