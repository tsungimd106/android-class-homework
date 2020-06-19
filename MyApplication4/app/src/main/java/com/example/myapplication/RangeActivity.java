package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class RangeActivity extends AppCompatActivity {
private TextView textView,textViewResult,txtName;
int l,r,password;
private Button button ;
private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_range);
        textView=findViewById(R.id.editText);
        txtName=findViewById(R.id.txtName);
        txtName.setText(getIntent().getStringExtra("name"));
        textViewResult=findViewById(R.id.textView);
        button=findViewById(R.id.button);
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RangeActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Random random=new Random();
        l=1;
        r=99;
        password=random.nextInt(97);
        password+=2;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            int   a=Integer.parseInt(textView.getText().toString());
                if(a<password)
                    l=a;
                else r=a;

                String result=("please enter in range of "+l+" and "+r);
                if(a==password){
                    result="You win";
                }
                textViewResult.setText(result);

            }
        });
    }

}
