package com.mart.eindproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    }

    private void validate(String username, String password){
        if(accounts.containsKey(username)){
            if(password.contentEquals(password)){
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        }
    }

}
