package com.diesen.quizmaster;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class TitleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        findViewById(R.id.online_button).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View w){
                Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }
}
