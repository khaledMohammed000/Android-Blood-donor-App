package com.example.miniproject.blooddonor;
/**
 * Created by AzMak Pirates on 11/3/2015.
 */
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class spinner extends AppCompatActivity {
Spinner spinner;
    ListView listView;
    LoginDataBaseAdapter db;
    Cursor cursor;
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Click for Launching Map", Snackbar.LENGTH_LONG)
                        .setAction("Launch", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(spinner.this,MapsActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }).show();
            }
        });

        spinner =(Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> arrayAdapter= ArrayAdapter.createFromResource(this,R.array.blood_group,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        listView = (ListView)findViewById(R.id.listView_007);
       // ArrayAdapter<String> adapter  = new ArrayAdapter<String>(this,R.layout.custom2,)

        db =  new LoginDataBaseAdapter(this);
        db.open();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                switch (position + 1) {
                    case 1:
                        //<item>A+</item>
                        cursor = db.specificBloodGroupList(1);
                    customAdapter.notifyDataSetChanged();
                        break;
                    case 2:
                        //<item>A-</item>
                        cursor = db.specificBloodGroupList(2);
                        customAdapter.notifyDataSetChanged();
                        break;
                    case 3:
                        //<item>B+</item>
                        cursor = db.specificBloodGroupList(3);
                        customAdapter.notifyDataSetChanged();
                        break;
                    case 4:
                        //<item>B-</item>
                        cursor = db.specificBloodGroupList(4);
                        customAdapter.notifyDataSetChanged();
                        break;
                    case 5:
                        //<item>O+</item>
                        cursor = db.specificBloodGroupList(5);
                        customAdapter.notifyDataSetChanged();
                        break;
                    case 6:
                        //<item>O-</item>
                        cursor = db.specificBloodGroupList(6);
                        customAdapter.notifyDataSetChanged();
                        break;
                    case 7:
                        //<item>AB+</item>
                        cursor = db.specificBloodGroupList(7);
                        customAdapter.notifyDataSetChanged();
                        break;
                    case 8:
                        //<item>AB-</item>
                        cursor = db.specificBloodGroupList(8);
                        customAdapter.notifyDataSetChanged();
                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

    }
    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if(cursor==null)
                return 0 ;

            return cursor.getCount();

        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(cursor!=null) {
                convertView = getLayoutInflater().inflate(R.layout.custom, null);
                TextView phoneText = (TextView) convertView.findViewById(R.id.textView_custom_phone);
                TextView nameText = (TextView) convertView.findViewById(R.id.textView_custom_name);
                cursor.moveToPosition(position);
                phoneText.setText(cursor.getString(cursor.getColumnIndex("firstName")));
                nameText.setText(cursor.getString(cursor.getColumnIndex("contactNo")));
            }

            return convertView;
        }
    }

}
