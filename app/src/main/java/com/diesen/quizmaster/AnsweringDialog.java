package com.diesen.quizmaster;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class AnsweringDialog extends DialogFragment {
    private AlertDialog dialog ;
    private AlertDialog.Builder alert;

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        alert = new AlertDialog.Builder(getActivity());

        // カスタムレイアウトの生成
        View alertView = getActivity().getLayoutInflater().inflate(R.layout.dialog_answering, null);

        // ViewをAlertDialog.Builderに追加
        alert.setView(alertView);

        // Dialogを生成
        dialog = alert.create();
        dialog.show();

        // 背景を透明にする
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //領域外を押した際等のキャンセル操作無効化
        this.setCancelable(false);

        return dialog;
    }
}
