package com.son.temp3.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.son.temp3.databinding.ActivitySecondBinding;


public class SecondActivity extends AppCompatActivity {

    ActivitySecondBinding binding;
    String zalo = "";
    String tele = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        zalo = intent.getStringExtra("zalo");
        tele = intent.getStringExtra("tele");

        binding.btnZalo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(zalo));
                startActivity(intent);
            }
        });

        binding.btnTele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(tele));
                startActivity(intent);
            }
        });
    }
}