package com.github.hailouwang.demosforapi.widget.listview;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class ListViewTest extends ListActivity{
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
        }
}
