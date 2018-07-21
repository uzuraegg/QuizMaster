package com.diesen.quizmaster;

import android.content.Intent;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import au.com.bytecode.opencsv.CSVReader;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {
    private CharByCharTextView  mQuestionView;
    private String questions[][] = new String[10][0];
    private SoundPool soundPool;
    private int answer_se; //回答ボタン押下効果音の識別ID
    private int count_quiz = 0;
    private int point;


    public DialogFragment dialogFragment;
    private FragmentManager flagmentManager;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        onPause();

        mQuestionView = findViewById(R.id.text_question);

        //サウンドプールの設定
        AudioAttributes attr = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(attr)
                .setMaxStreams(1)
                .build();
        answer_se = soundPool.load(this, R.raw.answer, 1);

        findViewById(R.id.imagePushButton).setOnClickListener(this);

        //csvから配列questionsに問題読み込み
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
        } catch (IOException e){
                e.printStackTrace();
        }

        mQuestionView.setTargetText(questions[count_quiz][0]);
        mQuestionView.startCharByCharAnim();
    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            switch (v.getId()) {
                case R.id.imagePushButton: //回答ボタン押した時
                    soundPool.play(answer_se,1F, 1F, 0, 0, 1F);

                    flagmentManager = getSupportFragmentManager();

                    dialogFragment = new AnsweringDialog();
                    Bundle args = new Bundle();
                    args.putString("ans",questions[count_quiz][1]); //引数
                    dialogFragment.setArguments(args);
                    dialogFragment.show(flagmentManager, "answering dialog");
                    mQuestionView.setVisibility(View.INVISIBLE);
                    break;
            }
        }

    }

    public void setNextQuestion(){
        count_quiz++;
        mQuestionView.setVisibility(View.VISIBLE);
        if(count_quiz >= 10){
            Intent intent = new Intent(getApplicationContext(), EndActivity.class);
            intent.putExtra("point", point);
            startActivity(intent);
            finish();
        }else {
            mQuestionView.setTargetText(questions[count_quiz][0]);
            mQuestionView.startCharByCharAnim();
        }
    }

    public void plusPoint(){
        point++;
    }

}
