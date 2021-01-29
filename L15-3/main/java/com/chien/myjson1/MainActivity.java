package com.chien.myjson1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    Context context;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        textView = findViewById(R.id.textView);
    }

    public void onclick(View view) {
        StringBuilder sb = new StringBuilder();
        String all="";

        AssetManager manager = context.getAssets();

        //manager.open("myjson.json") 這是獲得byte類型的資料  new InputStreamReader( ) 轉 char 類型
        //BufferedReader reader = new BufferedReader() 轉 String 字串
        //BufferedReader 拿到的資料都會自動變 String 字串 還可以讀取換行符號 可以一行一行的讀取
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(manager.open("test.json")));

            String line;

            while((line=reader.readLine()) != null){
                sb.append(line);
            }
            //先轉成總物件
            JSONObject jsonObject = new JSONObject(sb.toString());

            //找出friends陣列
            JSONArray jsonArray = jsonObject.getJSONArray("date");

            //再從"friends"中找出個別物件
            for(int i = 0; i<jsonArray.length(); i++){
                JSONObject array = jsonArray.getJSONObject(i);
                String name = array.getString("資源彙整機關");
                String phone = array.getString("鄉鎮市區公所名稱");
                all += name + "_" + phone + "\n";
            }
            textView.setText(all);
        }
        catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}