package com.example.usuario.masterslavepreferences;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_settings, menu);

        //siempre que ponemos codigo delvolvemos un true
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.settings) {
            //Toast.makeText(this, "pulsamos en settings", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));

        }

        return true;
    }
}
