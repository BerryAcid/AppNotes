package com.BerryAcid.notas.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.BerryAcid.notas.R;

public class MainActivity extends AppCompatActivity {

    Button btnLoggin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLoggin = findViewById(R.id.loggin);

        //Evento Click sobre botón de Loggin

        btnLoggin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (MainActivity.this, DashboardActivity.class);
                startActivity(i);
            }
        });
    }
}