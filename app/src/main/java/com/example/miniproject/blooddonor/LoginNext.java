package com.example.miniproject.blooddonor;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by AzMak Pirates on 11/3/2015.
 */
public class LoginNext extends Activity {
    LoginDataBaseAdapter db;
    int id;
    String blood;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginnext);

        db = new LoginDataBaseAdapter(getApplicationContext());
        db.open();
        cursor = db.similarUser();
        Intent intent = getIntent();
        String user = intent.getStringExtra("user");
        //comparing username with user and finding his ID
        cursor.moveToFirst();

        while(cursor.moveToNext()){
            if(user.equals(cursor.getString(cursor.getColumnIndex("USERNAME")))){
                id=cursor.getInt(cursor.getColumnIndex("ID"));
            }
        }
        cursor = db.db.rawQuery("select * from info where id=?", new String[]{""+id});
        cursor.moveToFirst();
        blood = cursor.getString(cursor.getColumnIndex("bloodGroup"));


        cursor = db.db.query("info",new String[]{"firstName","contactNo"},"bloodGroup=?",new String[]{""+blood},null,null,null);
        // cursor.moveToFirst();
        //  Cursor findEntry =db.db.query("sku_table", columns, "owner=?", new String[]{owner}, null, null, null);\
        //  findEntry.moveToFirst();


        ListView listView = (ListView)findViewById(R.id.listView_similar_bloodgroup);
        registerForContextMenu(listView);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);


    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
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

            convertView = getLayoutInflater().inflate(R.layout.custom, null);
            TextView phoneText = (TextView) convertView.findViewById(R.id.textView_custom_phone);
            TextView nameText = (TextView) convertView.findViewById(R.id.textView_custom_name);
            cursor.moveToPosition(position);
            phoneText.setText(cursor.getString(cursor.getColumnIndex("firstName")));
            nameText.setText(cursor.getString(cursor.getColumnIndex("contactNo")));


            return convertView;
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
    }

    int index;
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        index = info.position;
        switch(item.getItemId()){
            case R.id.update_menu:
               Intent intent = new Intent(LoginNext.this,UpdateActivity.class);
                intent.putExtra("id",index);
                startActivity(intent);
                finish();
                return true;

            case R.id.delete_account:

                AlertDialog.Builder alertDialogBuilder3 = new AlertDialog.Builder(LoginNext.this);
                alertDialogBuilder3.setTitle("Important !");
                alertDialogBuilder3
                        .setMessage("Do you want to Delete Account ?")
                        .setCancelable(false)
                        .setPositiveButton("YES",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {

                                db.delete_function(index);
                            }
                        })
                        .setNegativeButton("NO",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });
                ;

                AlertDialog alertDialog3 = alertDialogBuilder3.create();

                alertDialog3.show();
                return true;

            case R.id.locate_blood:
                Intent intent1 =new Intent(LoginNext.this,MapsActivity.class);
                startActivity(intent1);
                finish();
                return true;

            case R.id.action_settings:
                AlertDialog.Builder alertDialogBuilder4 = new AlertDialog.Builder(LoginNext.this);
                alertDialogBuilder4.setTitle("Information");
                alertDialogBuilder4
                        .setMessage("Developed by Aleem Uddin");

                AlertDialog alertDialog4 = alertDialogBuilder4.create();

                alertDialog4.show();
                return true;

        }
        return super.onContextItemSelected(item);
    }


}
