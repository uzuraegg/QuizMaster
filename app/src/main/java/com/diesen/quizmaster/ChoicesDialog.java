package com.diesen.quizmaster;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;

public class ChoicesDialog extends DialogFragment {
    private AlertDialog dialog ;
    private AlertDialog.Builder alert;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        alert = new AlertDialog.Builder(getActivity());

        // カスタムレイアウトの生成
        View alertView = getActivity().getLayoutInflater().inflate(R.layout.dialog_choices, null);

        // ViewをAlertDialog.Builderに追加
        alert.setView(alertView);

        // Dialogを生成
        dialog = alert.create();
        dialog.show();

        // 背景を透明にする
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //後ろのアクティビティを暗くしない
        dialog.getWindow().setFlags( 0 , WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        //領域外を押した際等のキャンセル操作無効化
        this.setCancelable(false);

        //表示位置を下方に
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.gravity = Gravity.BOTTOM;

        dialog.getWindow().setAttributes(lp);


        dialog.findViewById(R.id.bt_choice1).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //dismiss();
            }
        });
        dialog.findViewById(R.id.bt_choice2).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //dismiss();
            }
        });
        dialog.findViewById(R.id.bt_choice3).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //dismiss();
            }
        });
        dialog.findViewById(R.id.bt_choice4).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        return dialog;
    }

}