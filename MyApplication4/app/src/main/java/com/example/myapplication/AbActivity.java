package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AbActivity extends AppCompatActivity {

    Button button;
    ImageView back;
    TextView editText,textView4;
    private LinearLayout.LayoutParams params;
    RecyclerView recyclerView;
    Toast toast;
    private Long startTime, endTime=0L;
    List<String> resultSet = new ArrayList<>();
    int a, b, guess,count;// 宣告要使用的變數,a->儲存判斷位置相同的數字是否相同,b->儲存相同的數字是否存在但位置不同,
    // guess ->儲存玩家輸入的值
    boolean checkRepeating;// 宣告布林值幫助判斷數值是否重複
    List<Integer> pswArray = new ArrayList<>();//宣告pswArray清單儲存電腦產生的亂數
    int[] guessArray = new int[4];// 宣告要將玩家輸入的四位數字轉成陣列來判斷a,b
    Random random = new Random();// 宣告Random物件來讓來使用next...一系列的方法產生亂數
String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ab);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AbActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                final String dbName="test",tName="ab";
                SQLiteDatabase sqLiteDatabase;
                sqLiteDatabase=openOrCreateDatabase(dbName, Context.MODE_PRIVATE,null);
                String creatTable="CREATE TABLE IF NOT EXISTS "+tName+
                        "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        "name VARCHAR(32), count VARCHAR(2) ,time varchar(5))";
                sqLiteDatabase.execSQL(creatTable);
                ContentValues cv=new ContentValues(3);
                cv.put("name",name);
                cv.put("count",Integer.toString(count));
                cv.put("time",getTime());
                sqLiteDatabase.insert(tName,null,cv);
                sqLiteDatabase.close();
            }
        });
        pswArray.add(random.nextInt(9) + 1);//在電腦產生的亂數中，增加第一位數字，範圍在1~9之間
        for (int i = 1; i < 4; i++) {//重複三次,分別增加電腦產生亂數的第二,三,四位數字
            int temp = random.nextInt(10);//取範圍在0~9之間的一個亂數
            checkRepeating = false;//預設這個數字沒有重複
            for (int j = 0; j < pswArray.size(); j++) {//重複次數為已放入pswArray中數字的數量
                if (temp == pswArray.get(j)) {//分別取出已放入pswArray中的數字，和之前取的亂數比較是否有相同
                    i--;//如果有相同,i減1,讓外層迴圈執行的這次重來
                    checkRepeating = true;//更改變數為true
                    break;//跳出內層迴圈
                }
            }
            if (!checkRepeating) {//如果沒有重複
                pswArray.add(temp);//將先前取的亂數放入pswArray清單中

            }
        }
        for (int ii : pswArray) {
            System.out.println(ii);
        }
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1);
        params.setMargins(0, 1, 0, 1);
        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);
        textView4=findViewById(R.id.textView4);
        toast = new Toast(AbActivity.this);
        recyclerView = findViewById(R.id.recyclerVIew);
        System.out.println(recyclerView.getContext());
        recyclerView.addItemDecoration(new DividerItemDecoration(AbActivity.this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(AbActivity.this));
        recyclerView.setAdapter(new RecyclerHelper(resultSet));
        toastHelper("please enter fore no repeating number");
        name=getIntent().getStringExtra("name");
        textView4.setText(name);
        count=0;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    guess = Integer.parseInt(editText.getText().toString());// 取得使用者輸入的值(先假設使用者知道且願意輸入正確數值)
                    guessArray[0] = guess % 10;// 透過運算取得guess的個位數字,放入guessArray陣列裡
                    guessArray[1] = (guess % 100) / 10;// 透過運算取得guess的十位數字,放入guessArray陣列裡
                    guessArray[2] = (guess % 1000) / 100;// 透過運算取得guess的百位數字,放入guessArray陣列裡
                    guessArray[3] = guess / 1000;// 透過運算取得guess的千位數字,放入guessArray陣列裡
                    checkRepeating = false;// 預設數值沒有重複
                    for (int i = 0; i < 3; i++) {
                        for (int j = i + 1; j < 4; j++) {
                            if (guessArray[i] == guessArray[j] && i != j) {
                                /*
                                 * 分別取出電腦產生亂數的個,十,百位數字,來判斷在不同位置中，是否有重複的數字 且因為在個位數不必和自己比較，直接從十位數字開始比對
                                 * 十位數字因在之前已被個位數字比對過，直接從百位數字開始比對 百位數字因在之前已被十位與個位數字比對過,直接從千位數字開始比對
                                 */
                                checkRepeating = true;// 當數值重複時，更改變數為true
                                break;// 跳出迴圈
                            }
                        }
                        if (checkRepeating) {// 判斷是否有重複
                            break;// 有重複時，跳出判斷的迴圈
                        }
                    }
                    if (!checkRepeating) {
                        a = 0;
                        b = 0;// 讓a,b為0
                        for (int i = 0; i < 4; i++) {
                            for (int j = 0; j < 4; j++) {// 分別先從儲存玩家輸入數值的炙烈取出個,十,百,千,的數字，與從儲存電腦產生亂數的陣列取出個,十,百,千的數字比對
                                if (pswArray.get(i) == guessArray[j]) {// 如果有數字相同的話
                                    if (j == i) {// 如果位置相同的話
                                        a++;// a累加1
                                    } else {
                                        b++;// b累加1
                                    }
                                }
                            }
                        }
                        if (a != 4) {
                            addTextView(String.format("%04d ==> %dA %dB", guess, a, b));
                        } else {
                            addTextView("You win");
                            endTime = System.currentTimeMillis();
                            addTextView(getTime());
                        }
                        count++;
                    } else {
                        toastHelper("please enter fore no repeating number");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (e instanceof NumberFormatException) {
                        toastHelper("please enter fore number");
                    }
                }
                editText.setText("");
            }
        });
        startTime = System.currentTimeMillis();
    }

    public void addTextView(String result) {
        Collections.reverse(resultSet);
        resultSet.add(result);
        Collections.reverse(resultSet);
        recyclerView.setAdapter(new RecyclerHelper(resultSet));
    }

    public void toastHelper(String info) {
        toast = Toast.makeText(editText.getContext(), info, Toast.LENGTH_LONG);
        toast.show();
    }
    public String getTime(){
        endTime=(endTime!=0L?endTime:System.currentTimeMillis());
        return String.valueOf((endTime - startTime)/1000);
    }
}
