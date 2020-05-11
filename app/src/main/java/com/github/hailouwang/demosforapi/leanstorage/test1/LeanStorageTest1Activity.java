package com.github.hailouwang.demosforapi.leanstorage.test1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.hailouwang.demosforapi.R;

import java.util.List;

import cn.leancloud.AVObject;
import cn.leancloud.AVQuery;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LeanStorageTest1Activity extends AppCompatActivity {

    private Button leanStorageCreate;
    private Button leanStorageQuery;

    private Button leanStorageInsert;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.lean_storage_test1);

        leanStorageCreate = findViewById(R.id.lean_storage_create);
        leanStorageCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLeanStorageCreate();
            }
        });

        leanStorageQuery = findViewById(R.id.lean_storage_query);
        leanStorageQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLeanStorageQuery();
            }
        });

        leanStorageInsert = findViewById(R.id.lean_storage_batch_insert);
        leanStorageInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLeanStorageBatchInsert();
            }
        });
    }

    private void onLeanStorageQuery() {
        AVQuery<AVObject> avQuery = new AVQuery<>("Todo");
        avQuery.findInBackground().subscribe(new Observer<List<AVObject>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<AVObject> avObjects) {
                Log.d("hlwang", "size : " + avObjects.size() + ", content : " + avObjects);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void onLeanStorageQuery(AVObject avObject) {
        AVQuery<AVObject> avQuery = new AVQuery<>("Todo");
        avQuery.getInBackground(avObject.getObjectId()).subscribe(new Observer<AVObject>() {
            public void onSubscribe(Disposable disposable) {
            }

            public void onNext(AVObject o) {
                Log.d("hlwang", o.toString());
            }

            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            public void onComplete() {

            }
        });
    }

    private void onLeanStorageBatchInsert() {
        AVObject todo = new AVObject("Todo");
        for(int i=0;i<1000;i++){
            todo.put("title", "工程师"+i);
            todo.saveInBackground().subscribe(new Observer<AVObject>() {
                public void onSubscribe(Disposable disposable) {
                }

                public void onNext(AVObject avObject) {
                    Toast.makeText(LeanStorageTest1Activity.this, "succeed to save Object.", Toast.LENGTH_SHORT).show();
                    onLeanStorageQuery(avObject);
                }

                public void onError(Throwable throwable) {
                }

                public void onComplete() {
                }
            });
        }
    }

    private void onLeanStorageCreate() {
        AVObject todo = new AVObject("Todo");
        todo.put("title", "工程师3333周会");
        todo.put("content", "每周工程师会议，3333333周一下午2点");
        todo.saveInBackground().subscribe(new Observer<AVObject>() {
            public void onSubscribe(Disposable disposable) {
            }

            public void onNext(AVObject avObject) {
                Toast.makeText(LeanStorageTest1Activity.this, "succeed to save Object.", Toast.LENGTH_SHORT).show();
                onLeanStorageQuery(avObject);
            }

            public void onError(Throwable throwable) {
            }

            public void onComplete() {
            }
        });
    }
}
