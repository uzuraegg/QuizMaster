package com.diesen.quizmaster;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class AnsweringDialog extends DialogFragment implements View.OnClickListener{
    private AlertDialog dialog ;
    private AlertDialog.Builder alert;
    private String text = "";
    private TextView tv;
    private String answer;

    private String[] choices = new String[4];
    private Button[] btns = new Button[4];

    private SoundPool soundPool;
    private int good_se; // 正解の効果音の識別ID
    private int bad_se; // 不正解の効果音の識別ID

    private QuizActivity parentActivity;

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //サウンドプールの設定
        AudioAttributes attr = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(attr)
                .setMaxStreams(1)
                .build();
        bad_se = soundPool.load(getContext(), R.raw.wrong, 1);
        good_se = soundPool.load(getContext(), R.raw.correct, 1);

        // MainActivityのインスタンスを取得
        parentActivity = (QuizActivity) getActivity();

        alert = new AlertDialog.Builder(getActivity());

        // カスタムレイアウトの生成
        View alertView = getActivity().getLayoutInflater().inflate(R.layout.dialog_answering, null);

        tv = alertView.findViewById(R.id.txt_ans);

        // ViewをAlertDialog.Builderに追加
        alert.setView(alertView);

        // Dialogを生成
        dialog = alert.create();

        dialog.show();

        // 背景を透明にする
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //領域外を押した際等のキャンセル操作無効化
        this.setCancelable(false);

        btns[0] = alertView.findViewById(R.id.bt_choice1);
        btns[1] = alertView.findViewById(R.id.bt_choice2);
        btns[2] = alertView.findViewById(R.id.bt_choice3);
        btns[3] = alertView.findViewById(R.id.bt_choice4);

        for(int i=0; i < 4; i++){
            btns[i].setOnClickListener(this);
        }

        answer = getArguments().getString("ans");

        setRndChars();

        return dialog;
    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            switch (v.getId()) {
                case R.id.bt_choice1:
                    addChar(btns[0].getText().toString());
                    judge();
                    break;
                case R.id.bt_choice2:
                    addChar(btns[1].getText().toString());
                    judge();
                    break;
                case R.id.bt_choice3:
                    addChar(btns[2].getText().toString());
                    judge();
                    break;
                case R.id.bt_choice4:
                    addChar(btns[3].getText().toString());
                    judge();
                    break;
                default:
                    break;
            }
        }
    }

    public void setRndChars(){
        Random rnd = new Random();
        for(int i=0; i < 4; i++) {
            choices[i] = String.valueOf((char)('ア'+rnd.nextInt('ワ'-'ア')));
            btns[i].setText(choices[i]);
        }
        btns[rnd.nextInt(4)].setText(String.valueOf(answer.charAt(text.length())));
    }

    public void addChar(String txt){
        text += txt;
        tv.setText(text);
    }

    //正誤判定
    public void judge(){
        if(!answer.startsWith(text)) {
            Log.d("ans", answer);
            Log.d("text", text);
            soundPool.play(bad_se, 1F, 1F, 0, 0, 1F);
            parentActivity.setNextQuestion();
            dismiss();
        } else if(answer.equals(text)){
            soundPool.play(good_se, 1F, 1F, 0, 0, 1F);
            parentActivity.plusPoint();
            parentActivity.setNextQuestion();
            dismiss();
        }else {
            setRndChars();
        }
    }
}
