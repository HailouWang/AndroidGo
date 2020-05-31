package com.github.hailouwang.demosforapi.volley;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.hailouwang.volley.RequestQueue;
import com.github.hailouwang.volley.toolbox.ImageLoader;
import com.github.hailouwang.volley.toolbox.NetworkImageView;
import com.github.hailouwang.demosforapi.R;

public class NetworkImageViewTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_image_view);

        NetworkImageView networkImageView = (NetworkImageView) findViewById(R.id.network_image_view);

        //1、得到RequestQueue
        RequestQueue requestQueue = MySingleton.getInstance(this.getApplicationContext()).
                getRequestQueue();

        //2、初始化ImageLoader
        ImageLoader imageLoader = new ImageLoader(requestQueue,new ImageLoaderTest.MyImageCache());

        //3、
        networkImageView.setDefaultImageResId(R.drawable.ic_launcher_androidgo);
        networkImageView.setErrorImageResId(R.drawable.ic_sync_black_24dp);
        String url = "https://ss1.bdstatic.com/5aAHeD3nKgcUp2HgoI7O1ygwehsv/media/ch1000/png/JQRZFpc170726-bg.png";
        networkImageView.setImageUrl(url,imageLoader);
    }
}
