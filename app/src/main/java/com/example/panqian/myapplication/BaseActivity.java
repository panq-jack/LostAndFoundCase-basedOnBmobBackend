package com.example.panqian.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import cn.bmob.v3.Bmob;

/**
 * Created by panqian on 2016/6/8.
 */
public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this,"");
    }
    protected void toast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
    protected void log(String msg){
        Log.d("pan",msg);
    }
}
