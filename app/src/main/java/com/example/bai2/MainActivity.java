package com.example.bai2;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayAdapter adapter;
    ArrayList<String> list;
    EditText editText;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.editTextText);
        list = new ArrayList<>();
        lv = findViewById(R.id.lv);
        context = this;
        getData();
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    editText.setText("");  // Xóa văn bản khi nhấp vào
                }
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 0) {
                    ArrayList<String> filteredList = new ArrayList<>();
                    for (String item : list) {
                        if (item.toLowerCase().contains(s.toString().toLowerCase())) {
                            filteredList.add(item);
                        }
                    }
                    adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, filteredList);
                    lv.setAdapter(adapter);
                }else{
                    adapter = new ArrayAdapter(context,android.R.layout.simple_expandable_list_item_1, list);
                    lv.setAdapter(adapter);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void getData() {
       String[] a = getResources().getStringArray(R.array.lists_name);
       String[] b = getResources().getStringArray(R.array.lists_mssv);
       for(int i = 0; i< a.length;i++){
           String x = a[i] + "\n" + b[i];
           list.add(x);
       }
       adapter = new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1, list);
       lv.setAdapter(adapter);
    }
}