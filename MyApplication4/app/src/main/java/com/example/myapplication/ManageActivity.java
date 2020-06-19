package com.example.myapplication;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.RecyclerHelperManage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ManageActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayout.LayoutParams params;
    private Button button;
    private ImageView back;
    private List<DataBean> dataBeanList = new ArrayList<>();
    final String dbName = "test", tName = "ab";
    SQLiteDatabase sqLiteDatabase;
    String creatTable = "CREATE TABLE IF NOT EXISTS " + tName +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name VARCHAR(32), count VARCHAR(2) ,time varchar(5))";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
        sqLiteDatabase = openOrCreateDatabase(dbName, Context.MODE_PRIVATE, null);
        sqLiteDatabase.execSQL(creatTable);
        button = findViewById(R.id.reset);
        back = findViewById(R.id.back);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(ManageActivity.this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(ManageActivity.this));
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1);
        params.setMargins(0, 5, 0, 0);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManageActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetdata();
            }
        });
        resetdata();

    }

    private void reload(final List<DataBean> reloadList) {
        RecyclerHelperManage recyclerHelperManage = new RecyclerHelperManage(reloadList);
        recyclerView.setAdapter(recyclerHelperManage);
        recyclerHelperManage.setListener(new RecyclerHelperManage.OnItemClickListener() {

            @Override
            public void onItemClick(final int position) {
                System.out.println("enterToDialog");
                final AlertDialog.Builder builder = new AlertDialog.Builder(ManageActivity.this).setTitle("詳細資料");
                LinearLayout linearLayout = new LinearLayout(ManageActivity.this);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.setPadding(100, 0, 0, 0);
                System.out.println(position);
                String[] dialogLabel = new String[]{"玩家", "費時", "次數"};
                String[] dialogValue = new String[]{reloadList.get(position).getName(), reloadList.get(position).getTime(), reloadList.get(position).getCount()};
                System.out.println(dialogValue);
                final EditText[] editTexts = {new EditText(ManageActivity.this), new EditText(ManageActivity.this), new EditText(ManageActivity.this)};
                for (int i = 0; i < dialogValue.length; i++) {
                    LinearLayout linearLayoutText = new LinearLayout(ManageActivity.this);
                    linearLayoutText.setOrientation(LinearLayout.HORIZONTAL);
                    TextView textView = new TextView(ManageActivity.this);
                    textView.setLayoutParams(params);
                    textView.setText(dialogLabel[i] + " : ");
                    textView.setTextSize(15);
                    textView.setGravity(Gravity.LEFT);
                    linearLayoutText.addView(textView);
                    editTexts[i].setText(dialogValue[i]);
                    textView.setTextSize(15);
                    linearLayoutText.addView(editTexts[i]);
                    linearLayout.addView(linearLayoutText);
                }
                String[] buttonText = {"編輯", "刪除"};
                View.OnClickListener[] buttonEvent = {
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                edit(position, editTexts[0].getText().toString(), editTexts[1].getText().toString(), editTexts[2].getText().toString());
                            }
                        }
                        , new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        del(position);
                    }
                }};
                LinearLayout linearLayoutbutton = new LinearLayout(ManageActivity.this);
                linearLayoutbutton.setOrientation(LinearLayout.HORIZONTAL);

                for (int i = 0; i < buttonText.length; i++) {
                    Button dialogButton = new Button(ManageActivity.this);
                    dialogButton.setLayoutParams(params);
                    dialogButton.setText(buttonText[i]);
                    dialogButton.setOnClickListener(buttonEvent[i]);
//                    dialogButton.setGravity(Gravity.CENTER);
                    linearLayoutbutton.addView(dialogButton);
                }
                linearLayout.addView(linearLayoutbutton);

                builder.setView(linearLayout);
                builder.setNegativeButton("關閉", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


    }

    private void resetdata() {

        Cursor c = sqLiteDatabase.rawQuery("select * from " + tName, null);
        List<DataBean> dataBeansreset = new ArrayList<>();
        if (c.getCount() == 0) {

        } else {

            if (c.moveToFirst()) {
                do {
                    dataBeansreset.add(new DataBean(c.getInt(0), c.getString(1), c.getString(3), c.getString(2)));
                } while (c.moveToNext());
            }

            if (!dataBeansreset.isEmpty()) {
                reload(dataBeansreset);
            }
        }

    }

    private void del(int position) {
        sqLiteDatabase.delete(tName, "_id=" + position, null);
        resetdata();

    }

    private void edit(int position, String name, String time, String count) {
        ContentValues cv = new ContentValues(3);
        cv.put("name", name);
        cv.put("time", time);
        cv.put("count", count);
        sqLiteDatabase.update(tName, cv, "_id=" + position, null);
        resetdata();

    }

}
