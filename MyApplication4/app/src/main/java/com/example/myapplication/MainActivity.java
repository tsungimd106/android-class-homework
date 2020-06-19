package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button ab, gcd, range, prime,manage;
    private Button[] buttons = {range, prime};
    private EditText editText;
    Class[] content = {RangeActivity.class, PrimeActivity.class};
    int[] parms = {R.id.range, R.id.prime};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        range = findViewById(R.id.range);
        editText = findViewById(R.id.editText3);
        prime = findViewById(R.id.prime);
        ab = findViewById(R.id.ab);
        gcd = findViewById(R.id.gcd);
        manage=findViewById(R.id.button_edit);
        manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ManageActivity.class);
                startActivity(intent);
                finish();
            }
        });
        range.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RangeActivity.class);
                intent.putExtra("name", editText.getText().toString());
                startActivity(intent);
                finish();
            }
        });
        gcd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GcdActivity.class);
                intent.putExtra("name", editText.getText().toString());
                startActivity(intent);
                finish();
            }
        });
        prime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PrimeActivity.class);
                intent.putExtra("name", editText.getText().toString());
                startActivity(intent);
                finish();
            }
        });
        ab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AbActivity.class);
                intent.putExtra("name", editText.getText().toString());
                startActivity(intent);
                finish();
            }
        });
    }


}
