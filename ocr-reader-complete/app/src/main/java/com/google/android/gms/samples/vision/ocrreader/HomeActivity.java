package com.google.android.gms.samples.vision.ocrreader;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


public class HomeActivity extends AppCompatActivity {
    private Button add, unadd, times, into;
    private Boolean difficult = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        add = findViewById(R.id.addButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("enter this");
                chanegePage("+");
            }
        });
        unadd = findViewById(R.id.button2);
        unadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("enter this");
                chanegePage("-");
            }
        });
        times = findViewById(R.id.button3);
        times.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("enter this");
                chanegePage("×");
            }
        });
        into = findViewById(R.id.button4);
        into.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("enter this");
                chanegePage("÷");
            }
        });
    }

    private void chanegePage(final String mode) {
        RadioGroup level = new RadioGroup(HomeActivity.this);

        final AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this).setTitle("請選擇挑戰難度");
        LinearLayout linearLayout = new LinearLayout(HomeActivity.this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(100, 0, 0, 0);
        TextView textViewLable = new TextView(HomeActivity.this);
        textViewLable.setText("");
        linearLayout.addView(textViewLable);
        final RadioButton high = new RadioButton(HomeActivity.this);
        final RadioButton low = new RadioButton(HomeActivity.this);
//        low.setSelected(false);
        high.setText("十位數");
        low.setText("個位數");
        level.addView(high);
        level.addView(low);
        level.check(low.getId());
        linearLayout.addView(level);
        builder.setView(linearLayout);
        builder.setNegativeButton("關閉", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setPositiveButton("確認", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent gonext = new Intent(HomeActivity.this, OcrCaptureActivity.class);
                gonext.putExtra("mode", mode);
                gonext.putExtra("difficult", difficult);
                startActivity(gonext);
            }
        });
        level.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                difficult = (checkedId == high.getId() ? true : false);

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
