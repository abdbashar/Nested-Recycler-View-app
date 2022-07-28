package com.noahliu.nestedrecyclerdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<MyData> data = makeData();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(data, new MyAdapter.OnItemClick() {
            @Override
            public void onItemClick(MyData.NestedData data, MyData myData) {
                Toast.makeText(MainActivity.this
                        , "chosen"+myData.getTitle()+"of "+data.getNesTitle()
                        , Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(myAdapter);

    }

    private List<MyData> makeData(){
        List<MyData> data = new ArrayList<>();
        String[] title = new String[]{"Monday","Tuesday","Wednesday","Thursday","Friday"};
        String[] nesTitle = new String[]{"Braised beef", "Tomato scrambled eggs",
                                         "Fried chicken drumsticks", "Sweet and sour fish", "Baked stewed rice",
                                         "Fried shrimps", "Braised salmon"};

        for (int i = 0; i < title.length; i++) {
            int r = (int)(Math.random()*7);
            List<MyData.NestedData> nesArray = new ArrayList<>();
            for (int j = 0; j < r+1; j++) {
                nesArray.add(new MyData.NestedData(nesTitle[(int)(Math.random()*7)]));
            }
            data.add(new MyData(title[i],nesArray));
        }
        return data;
    }
}