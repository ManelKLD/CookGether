package com.example.cookgether;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  {

    private Button btnCon;
    private Button btnIns;
    private Intent conIntent;
    private Intent insIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCon = (Button) findViewById(R.id.btnCon);
        btnIns = (Button) findViewById(R.id.btnIns);
    }


    public void identification(View view){
        switch (view.getId()) {
            case R.id.btnCon: {
                conIntent = new Intent(MainActivity.this,connexionActivity.class);
                startActivity(conIntent);
                break;
            }
            case R.id.btnIns: {
                insIntent = new Intent(MainActivity.this,InscriptionActivity.class);
                startActivity(insIntent);
                break;
            }
        }
    }

}
