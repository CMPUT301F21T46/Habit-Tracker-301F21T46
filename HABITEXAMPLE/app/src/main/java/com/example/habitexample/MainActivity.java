package com.example.habitexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    boolean isClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listView);

        //Expose MedData as a singleton and set the its HabitlistAdapter
        ListAdapter adapter = HabitData.getInstance(this,R.layout.activity_main).getHabitListAdapter();
        listView.setAdapter(adapter);

        Button comment = findViewById(R.id.commentOnHabitButton);
        Button photo = findViewById(R.id.addPhotoHabitButton);
        Button location = findViewById(R.id.addLocationHabitButton);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                isClicked = true;
            }
        });
    }
}