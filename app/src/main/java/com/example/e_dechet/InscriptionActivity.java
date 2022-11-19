package com.example.e_dechet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InscriptionActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registre);
        Button btninscription = (Button) findViewById(R.id.btn_inscrire);
        Button btnconnectervous = (Button) findViewById(R.id.btn_connectez_vous);

        btninscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent order = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(order);
            }
        });

        btnconnectervous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent order2 = new Intent(getApplicationContext(),Login_Activity.class);
                startActivity(order2);
            }
        });
    }
}
