package com.diesen.quizmaster;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import au.com.bytecode.opencsv.CSVReader;

public class QuizActivity extends AppCompatActivity {
    private CharByCharTextView  mQuestionView;
    private String questions[][] = new String[10][0];


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mQuestionView = findViewById(R.id.text_question);

        try {
            AssetManager as = getResources().getAssets();
            InputStream is = as.open("test.csv");
            CSVReader reader = new CSVReader(new InputStreamReader(is), ',');

            String[] nextText = null;
            for (int i = 0; i < 10; i++) {
                nextText = reader.readNext();
                if(nextText == null){
                    break;
                } else {
                    questions[i] = nextText;
                }
            }
        }catch (IOException e){
                e.printStackTrace();
        }

        mQuestionView.setTargetText(questions[0][0]);
        mQuestionView.startCharByCharAnim();
    }
}
