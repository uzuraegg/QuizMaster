package com.diesen.quizmaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {
    private TextView scoreText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_end);
        Bundle bundle = getIntent().getExtras();
        scoreText = (TextView) findViewById(R.id.point);
        if (bundle != null) {
            int point = bundle.getInt("point");
            scoreText.setText(String.format("%d", point));
        }
    }
}
