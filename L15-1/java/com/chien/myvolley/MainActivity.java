package com.chien.myvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadImage(); //自訂下載圖片的方法
    }

    private void loadImage() {
        int maxWidth = 500;
        int maxHeight = 500;
        String url = "https://fc.life.com.tw/upload_file/8/content/e04b6976-5423-4a62-9be2-1b8a202db55d.jpg";

        //volley裡的類 請求下載圖片 可以下載很多來排隊
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        ImageRequest imageRequest = new ImageRequest(
                url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        ImageView imageView = findViewById(R.id.imageView);
                        imageView.setImageBitmap(response);
                    }
                },
                0,
                0,
                Bitmap.Config.RGB_565,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "FAIL", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        queue.add(imageRequest);
    }
}