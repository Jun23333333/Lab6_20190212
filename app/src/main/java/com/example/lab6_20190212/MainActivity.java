package com.example.lab6_20190212;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button juego = findViewById(R.id.button);
        Button juego2 = findViewById(R.id.button2);
        juego.setOnClickListener(v -> {
            Intent intent = new Intent(this,juego1.class);
            startActivity(intent);
        });
        juego2.setOnClickListener(v -> {
            Intent intent = new Intent(this,juego2.class);
            startActivity(intent);
        });
    }
}