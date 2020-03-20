package com.github.hailouwang.demosforapi.widget;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;

import com.github.hailouwang.demosforapi.R;

public class ViewVibration extends ListActivity {
    public static final String[] TITLES = 
    {
            "Henry IV (1)",   
            "Henry V",
            "Henry VIII",       
            "Richard II",
            "Richard III",
            "Merchant of Venice",  
            "Othello",
            "King Lear"
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, TITLES));
        getListView().setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view,
                    int position, long id) {
                if(position%2==0){
                    return true;
                }
                return false;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}