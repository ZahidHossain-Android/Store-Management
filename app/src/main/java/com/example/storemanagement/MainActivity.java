package com.example.storemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonadd,buttonview,buttonupdate,buttondelete,buttonabout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonadd=findViewById(R.id.btnAdd);
        buttonview=findViewById(R.id.btnView);
        buttonupdate=findViewById(R.id.btnUpdate);
        buttondelete=findViewById(R.id.btnDelete);
        buttonabout=findViewById(R.id.btnAbout);


        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });

        buttonview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ViewActivity.class);
                startActivity(intent);
            }
        });

        buttonupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,UpdateActivity.class);
                startActivity(intent);
            }
        });


        buttondelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,DeleteActivity.class);
                startActivity(intent);
            }
        });


        buttonabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AboutActivity.class);
                startActivity(intent);
            }
        });
    }
}
