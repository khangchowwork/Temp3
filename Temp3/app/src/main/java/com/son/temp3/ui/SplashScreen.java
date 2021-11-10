package com.son.temp3.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.son.temp3.R;
import com.son.temp3.api.API;
import com.son.temp3.model.Ip;
import com.son.temp3.model.Links;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SplashScreen extends AppCompatActivity {


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("temp3");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

         new StartCheckingInternet().execute();
    }

    private boolean isInternetWorking() {
        boolean internet = false;
        try {
            URL url = new URL("https://google.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(10000);
            connection.connect();
            internet = connection.getResponseCode() == 200;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return internet;
    }

    private class StartCheckingInternet extends AsyncTask<Void, Boolean, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {

            while (true)   {
                if(isInternetWorking()) {
                    return true;
                }else   {
                    publishProgress(false);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        @Override
        protected void onProgressUpdate(Boolean... values) {
            super.onProgressUpdate(values);
            if (!values[0]) {
                Toast.makeText(SplashScreen.this, "Vui Lòng Kiểm Tra Lại Internet", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean)   {
                API.apiService.getIpAdress().enqueue(new Callback<Ip>() {
                    @Override
                    public void onResponse(Call<Ip> call, Response<Ip> response) {

                        Ip ip = response.body();
                        if (ip.countryCode.equals("VN"))    {
                            getLinks();
                        }else   {
                            System.exit(0);
                        }
                    }

                    @Override
                    public void onFailure(Call<Ip> call, Throwable t) {
                    }
                });
            }
        }
    }

    private void getLinks() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                intent.putExtra("links", new Gson().toJson(snapshot.getValue(Links.class)));
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}