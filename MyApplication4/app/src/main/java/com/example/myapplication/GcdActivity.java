package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GcdActivity extends AppCompatActivity {
    private TextView textViewa,textViewb,textViewResult,textView5;
    private Button button;
    private ImageView back;
    int a ,b,c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gcd);
        textViewa=findViewById(R.id.editText);
        textViewb=findViewById(R.id.editText2);
        textViewResult=findViewById(R.id.result);
        button=findViewById(R.id.button);
        back=findViewById(R.id.back);
        textView5=findViewById(R.id.textView5);
        textView5.setText(getIntent().getStringExtra("name"));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GcdActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a=Integer.parseInt(textViewa.getText().toString());
                b=Integer.parseInt(textViewb.getText().toString());
                c=a%b;
                while(c!=0){
                    a=b;b=c;c=a%b;
                }
                textViewResult.setText("GCD is "+b);
            }
        });
    }
}
