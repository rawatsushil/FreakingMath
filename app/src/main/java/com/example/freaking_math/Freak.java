package com.example.freaking_math;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class Freak extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freak);
        ImageButton b=(ImageButton)findViewById(R.id.button1);
        b.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i =new Intent(Freak.this,MainActivity.class);
                startActivity(i);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.freak, menu);
        return true;
    }
}
