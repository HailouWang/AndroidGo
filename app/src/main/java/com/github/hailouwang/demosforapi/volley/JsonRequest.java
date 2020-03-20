package com.github.hailouwang.demosforapi.volley;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.hailouwang.volley.Request;
import com.github.hailouwang.volley.Response;
import com.github.hailouwang.volley.VolleyError;
import com.github.hailouwang.volley.toolbox.JsonObjectRequest;
import com.github.hailouwang.demosforapi.R;

import org.json.JSONObject;

public class JsonRequest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json_request);

        final TextView mTxtDisplay;
        ImageView mImageView;
        mTxtDisplay = (TextView) findViewById(R.id.txtDisplay);
        String url = "https://hailouwang.github.io/JsonDemo.html";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("hlwang","VolleySimpleRequest onResponse response is:"+response);
                        mTxtDisplay.setText("Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("hlwang","VolleySimpleRequest onErrorResponse error:"+error.getMessage());
                        mTxtDisplay.setText("That didn't work!");
                    }
                });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);

    }
}
