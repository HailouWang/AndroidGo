package com.github.hailouwang.demosforapi.volley;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.hailouwang.volley.AuthFailureError;
import com.github.hailouwang.volley.NetworkResponse;
import com.github.hailouwang.volley.ParseError;
import com.github.hailouwang.volley.Request;
import com.github.hailouwang.volley.RequestQueue;
import com.github.hailouwang.volley.Response;
import com.github.hailouwang.volley.VolleyError;
import com.github.hailouwang.volley.toolbox.HttpHeaderParser;
import com.github.hailouwang.demosforapi.R;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class CustomVolleyRequest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView mTextView = (TextView) findViewById(R.id.text);

        //1、获得RequestQueue
        RequestQueue queue = MySingleton.getInstance(this.getApplicationContext()).
                getRequestQueue();

        //2、初始化GSON Request
        String url = "https://hailouwang.github.io/JsonDemo.html";/*http://www.google.com*/
        GsonRequest<ComplanyWebSiteInfo> gsonRequest = new GsonRequest<>(url,ComplanyWebSiteInfo.class,
            null,new Response.Listener<ComplanyWebSiteInfo>(){
                @Override
                public void onResponse(ComplanyWebSiteInfo response) {
                    Log.d("hlwang","CustomVolleyRequest onResponse ComplanyWebSiteInfo is:"+response);
                    mTextView.setText(response.toString());
                }
            },new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("hlwang","CustomVolleyRequest onErrorResponse error:"+error.getMessage());
                    mTextView.setText("That didn't work!");
                }
            });
        //3、加入RequestQueue
        queue.add(gsonRequest);
    }

    class ComplanyWebSiteInfo{
        private String name;
        private String url;
        private Address address;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        @Override
        public String toString() {
            return "ComplanyWebSiteInfo{" +
                    "name='" + name + '\'' +
                    ", url='" + url + '\'' +
                    ", address=" + address +
                    '}';
        }

        class Address{
            private String street;
            private String city;
            private String country;

            public String getStreet() {
                return street;
            }

            public void setStreet(String street) {
                this.street = street;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            @Override
            public String toString() {
                return "Address{" +
                        "street='" + street + '\'' +
                        ", city='" + city + '\'' +
                        ", country='" + country + '\'' +
                        '}';
            }
        }
    }

    class GsonRequest<T> extends Request<T> {
        private final Gson gson = new Gson();
        private final Class<T> clazz;
        private final Map<String, String> headers;
        private final Response.Listener<T> listener;

        /**
         * Make a GET request and return a parsed object from JSON.
         *
         * @param url URL of the request to make
         * @param clazz Relevant class object, for Gson's reflection
         * @param headers Map of request headers
         */
        public GsonRequest(String url, Class<T> clazz, Map<String, String> headers,
                           Response.Listener<T> listener, Response.ErrorListener errorListener) {
            super(Method.GET, url, errorListener);
            this.clazz = clazz;
            this.headers = headers;
            this.listener = listener;
        }

        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            return headers != null ? headers : super.getHeaders();
        }

        @Override
        protected void deliverResponse(T response) {
            listener.onResponse(response);
        }

        @Override
        protected Response<T> parseNetworkResponse(NetworkResponse response) {
            try {
                String json = new String(
                        response.data,
                        HttpHeaderParser.parseCharset(response.headers));
                return Response.success(
                        gson.fromJson(json, clazz),
                        HttpHeaderParser.parseCacheHeaders(response));
            } catch (UnsupportedEncodingException e) {
                return Response.error(new ParseError(e));
            } catch (JsonSyntaxException e) {
                return Response.error(new ParseError(e));
            }
        }
    }
}
