package com.github.hailouwang.demosforapi.volley;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.hailouwang.volley.Cache;
import com.github.hailouwang.volley.Network;
import com.github.hailouwang.volley.Request;
import com.github.hailouwang.volley.RequestQueue;
import com.github.hailouwang.volley.Response;
import com.github.hailouwang.volley.VolleyError;
import com.github.hailouwang.volley.toolbox.BasicNetwork;
import com.github.hailouwang.volley.toolbox.DiskBasedCache;
import com.github.hailouwang.volley.toolbox.HurlStack;
import com.github.hailouwang.volley.toolbox.StringRequest;
import com.github.hailouwang.demosforapi.R;

public class CreateRequestQueue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView mTextView = (TextView) findViewById(R.id.text);

        RequestQueue mRequestQueue;

        // Instantiate the cache 1、初始化Cache
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        //2、建立网络连接
        Network network = new BasicNetwork(new HurlStack());

        // Instantiate the RequestQueue with the cache and network.
        //3、构建初始化RequestQueue
        mRequestQueue = new RequestQueue(cache, network);

        // Start the queue
        //4、启动RequestQueue
        mRequestQueue.start();

        String url = "http://www.example.com";

        // Formulate the request and handle the response.
        //5、初始化并构建Request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Do something with the response
                        mTextView.setText("Response is: " + response.substring(0, 500));
                        Log.d("hlwang","CreateRequestQueue onResponse response is:"+response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        Log.d("hlwang","CreateRequestQueue onErrorResponse error is:"+error);
                    }
                });

        // Add the request to the RequestQueue.
        //6、将Request加入队列
        mRequestQueue.add(stringRequest);
    }
}
