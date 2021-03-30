package com.leon.dev_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leon.dev_demo.view.custom.CommonViewActivity;
import com.leon.dev_demo.view.custom.CusTextViewActivity;
import com.leon.dev_demo.view.recyclerview.RecyclerViewActivity;

public class MainActivity extends AppCompatActivity {
    private LinearLayout listLLY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Android示例列表");
        listLLY = findViewById(R.id.listLLY);
        updateUI();
    }

    private void updateUI() {
        addActivity("RecyclerView", RecyclerViewActivity.class);
        addActivity("CommonView", CommonViewActivity.class);
        addActivity("DrawText", CusTextViewActivity.class);
    }

    private void addActivity(String text, final Class<?> cls) {
        TextView itemView = new TextView(this);
        listLLY.addView(itemView);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) itemView.getLayoutParams();
        layoutParams.height = 100;
        layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
        layoutParams.bottomMargin = 10;
        itemView.setText(text);
        itemView.setBackgroundColor(Color.parseColor("#d5d5d5"));
        itemView.setGravity(Gravity.CENTER);
        itemView.setClickable(true);
        itemView.setFocusable(true);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,cls);
                startActivity(intent);
            }
        });
    }

}