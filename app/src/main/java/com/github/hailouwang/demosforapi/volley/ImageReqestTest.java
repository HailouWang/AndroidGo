package com.github.hailouwang.demosforapi.volley;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.hailouwang.volley.RequestQueue;
import com.github.hailouwang.volley.Response;
import com.github.hailouwang.volley.VolleyError;
import com.github.hailouwang.volley.toolbox.ImageRequest;
import com.github.hailouwang.demosforapi.R;

public class ImageReqestTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_reqest);

        final ImageView imageView = (ImageView) findViewById(R.id.imageView);

        //1、得到RequestQueue
        RequestQueue requestQueue = MySingleton.getInstance(this.getApplicationContext()).
                getRequestQueue();

        //2、ImageRequest
        String URL= "http://cdn2.jianshu.io/assets/web/logo-58fd04f6f0de908401aa561cda6a0688.png";
        ImageRequest imageReqest = new ImageRequest(URL, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                Log.d("hlwang","ImageReqestTest onResponse...");
                imageView.setImageBitmap(response);
            }
        }, 1080, 500, ImageView.ScaleType.CENTER,
                Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("hlwang","ImageReqestTest onErrorResponse error :"+error);
            }
        });
        //3、加入RequestQueue
        requestQueue.add(imageReqest);
    }
}
