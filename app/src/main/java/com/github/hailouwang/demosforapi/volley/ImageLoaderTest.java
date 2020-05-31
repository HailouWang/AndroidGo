package com.github.hailouwang.demosforapi.volley;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.LruCache;

import com.github.hailouwang.volley.RequestQueue;
import com.github.hailouwang.volley.toolbox.ImageLoader;
import com.github.hailouwang.demosforapi.R;

public class ImageLoaderTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_loader_test);

        final ImageView imageView = (ImageView) findViewById(R.id.imageView);

        //1、得到RequestQueue
        RequestQueue requestQueue = MySingleton.getInstance(this.getApplicationContext()).
                getRequestQueue();

        //2、初始化ImageLoader
        ImageLoader imageLoader = new ImageLoader(requestQueue,new MyImageCache());

        //3、初始化ImageListener,第一个参数是imageView，第二个参数是刷新图片，第三个图片是默认图片
        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(imageView,
                R.drawable.ic_sync_black_24dp,R.drawable.ic_launcher_androidgo);

        //4、发起ImageLoader请求，并配置ImageListener
        String url = "http://upload-images.jianshu.io/upload_images/3828779-6224599660db3125.png";
        imageLoader.get(url,imageListener);
    }

    public static class MyImageCache implements ImageLoader.ImageCache{
        private LruCache<String,Bitmap> mMemoryCache;

        public MyImageCache(){
            int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
            int cacheSize = maxMemory/8;
            mMemoryCache = new LruCache<String, Bitmap>(cacheSize){
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getByteCount()/1024;
                }
            };
        }

        @Override
        public Bitmap getBitmap(String url) {
            return mMemoryCache.get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            mMemoryCache.put(url, bitmap);
        }
    }
}
