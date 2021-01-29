package com.chien.myokhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    Button loadImg;
    ImageView imageView;
    String url = "https://miro.medium.com/max/676/1*XEgA1TTwXa5AvAdw40GFow.png";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        loadImg = findViewById(R.id.loadImg);
        final ExecutorService service = Executors.newSingleThreadExecutor();

        loadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 建立OkHttpClient物件
                final OkHttpClient client = new OkHttpClient();

                // 建立請求物件
                final Request request = new Request.Builder().url(url).build();

                // 推送請求 等待結果
                service.submit(new Runnable() {
                    @Override
                    public void run() {
                        // 處理結果
                        try {
                            //OkHttpClient物件執行指定任務
                            Response response = client.newCall(request).execute();
                            //將結果中的主體body內容轉成byte格式
                            InputStream is = response.body().byteStream();
                            //byte轉換成圖片
                            Bitmap bm = BitmapFactory.decodeStream(is);
                            //放入元件
                            imageView.setImageBitmap(bm);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}