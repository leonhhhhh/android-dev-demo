package com.leon.dev_demo.view.custom;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.leon.dev_demo.R;

import androidx.appcompat.app.AppCompatActivity;

public class CusTextViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);

    }
    @SuppressLint("NewApi")
    public void onClick(View view) {
        char cha = '蛋';
        int intcha = cha;
        Log.e("DEBUG",cha+":"+intcha);
    }
}