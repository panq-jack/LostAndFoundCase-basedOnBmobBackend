package com.example.panqian.myapplication.views;


import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.panqian.myapplication.R;
import com.example.panqian.myapplication.model.Found;
import com.example.panqian.myapplication.model.Lost;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by panqian on 2016/6/8.
 */
public class MyDialogFactory {
    private static MyDialogFactory instance;

    public static synchronized MyDialogFactory getInstance() {
        if (instance == null) {
            instance = new MyDialogFactory();
        }
        return instance;
    }

    public AlertDialog createDialog(final Context context, final int tag, View.OnClickListener onClickListener) throws IllegalArgumentException {
        if (context == null) {
            throw new IllegalArgumentException("the param context can not be null in this dialog style");
        }
        if (tag < 0) {
            throw new IllegalArgumentException("the param message can not be empty in this dialog style");
        }
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_plus, null);
        TextView dialog_theme = (TextView) dialogView.findViewById(R.id.dialog_theme);
        final EditText dialog_title = (EditText) dialogView.findViewById(R.id.dialog_title);
        final EditText dialog_desc = (EditText) dialogView.findViewById(R.id.dialog_desc);
        final EditText dialog_phone = (EditText) dialogView.findViewById(R.id.dialog_phone);
        ImageView dialog_ok = (ImageView) dialogView.findViewById(R.id.dialog_ok);
        String theme = "";
        if (0 == tag) {
            theme = "失物";
        } else if (1 == tag) {
            theme = "招领";
        }
        dialog_theme.setText("添加 " + theme + " 信息");
        final AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setView(dialogView)
                .setCancelable(false)
                .create();
        alertDialog.setCanceledOnTouchOutside(true);
        dialog_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (0 == tag) {
                    Lost lost = new Lost();
                    lost.setTitle(dialog_title.getText().toString());
                    lost.setDesc(dialog_desc.getText().toString());
                    lost.setPhone(dialog_phone.getText().toString());
                    lost.save(context, new SaveListener() {
                        @Override
                        public void onSuccess() {
                            Toast.makeText(context, "添加lost数据成功", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            Toast.makeText(context, "添加lost数据失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else if (1 == tag) {
                    Found found = new Found();
                    found.setTitle(dialog_title.getText().toString());
                    found.setDesc(dialog_desc.getText().toString());
                    found.setPhone(dialog_phone.getText().toString());
                    found.save(context, new SaveListener() {
                        @Override
                        public void onSuccess() {
                            Toast.makeText(context, "添加found数据成功", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            Toast.makeText(context, "添加found数据失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                alertDialog.dismiss();
            }
        });
        return alertDialog;
    }
}
