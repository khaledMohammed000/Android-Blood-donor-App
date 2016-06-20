package com.example.miniproject.blooddonor;

/**
 * Created by aleem on 25-Oct-15.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class LoginDataBaseAdapter
{
    static final String DATABASE_NAME = "login.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE = "create table "+"LOGIN"+
            "( " +"ID "+" integer primary key autoincrement,"+ "USERNAME text,PASSWORD text); ";
    static final String DATABASE_CREATE2 = "create table info(id integer primary key autoincrement," +
            "firstName text,lastName text,contactNo text,address text,bloodGroup text)";
    // Variable to hold the database instance
    public  SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    public DataBaseHelper dbHelper;
    public  LoginDataBaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public  LoginDataBaseAdapter open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        db.close();
    }

    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    public void insertEntry(String userName,String password)
    {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("USERNAME", userName);
        newValues.put("PASSWORD",password);

        // Insert the row into your table
        db.insert("LOGIN", null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }
    public void  insertEntry2(String firstName,String lastName,String contactNo,String address,String bloodGroup){
        ContentValues newValues = new ContentValues();
        newValues.put("firstName",firstName);
        newValues.put("lastName",lastName);
        newValues.put("contactNo",contactNo);
        newValues.put("address",address);
        newValues.put("bloodGroup",bloodGroup);
        db.insert("info", null, newValues);
    }
    public int deleteEntry(String UserName)
    {
        //String id=String.valueOf(ID);
        String where="USERNAME=?";
        int numberOFEntriesDeleted= db.delete("LOGIN", where, new String[]{UserName}) ;
        Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }
    public String getSinlgeEntry(String userName)
    {
        Cursor cursor=db.query("LOGIN", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }
    public void  updateEntry(String userName,String password)
    {
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        updatedValues.put("USERNAME", userName);
        updatedValues.put("PASSWORD", password);

        String where="USERNAME = ?";
        db.update("LOGIN", updatedValues, where, new String[]{userName});
    }

    public void updateEntry2(String firstName,String lastName,String contactNo,String address,String bloodGroup,int id){
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
/*
 "create table info(id integer primary key autoincrement," +
            "firstName text,lastName text,contactNo text,address text,bloodGroup text)";
 */
        updatedValues.put("firstName", firstName);
        updatedValues.put("lastName", lastName);
        updatedValues.put("contactNo", contactNo);
        updatedValues.put("address", address);
        updatedValues.put("bloodGroup", bloodGroup);

        String where="id = ?";
        db.update("info", updatedValues, where, new String[]{"" + id});

    }
    public int getRows(){
       /* Cursor mCount= db.rawQuery("select count(*) from info", null);
        mCount.moveToFirst();
        int count= mCount.getInt(0);
        mCount.close();
        return count;*/
        Cursor cursor = db.query("info",null,null,null,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
    public String getValue(int id){
        Cursor cursor=db.query("info", null, "Id=?", new String[]{id+""}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String firstName = cursor.getString(cursor.getColumnIndex("firstName"));
        String lastName = cursor.getString(cursor.getColumnIndex("lastName"));
        String bloodGroup = cursor.getString(cursor.getColumnIndex("bloodGroup"));
        if(bloodGroup.length()==0){
            bloodGroup = "Dont Know";
        }
        String contactNo = cursor.getString(cursor.getColumnIndex("contactNo"));
        String address = cursor.getString(cursor.getColumnIndex("address"));
//        String fi = "firstName:"+firstName+"/"+"lastName:"+lastName+"/"+"contactNo:"+contactNo+
//                    "/"+"address:"+address+"/"+"bloodGroup:"+bloodGroup;
        String fi = firstName+" "+lastName+"$Contact No : "+contactNo+"$BloodGroup : "+bloodGroup+"$Address : "+address;

        return fi;
    }

    public Cursor similarUser(){

      return db.rawQuery("select * from LOGIN",null);
    }

    public void delete_function(int id){
        String where1="ID = ?";
        String where2="id = ?";
        db.delete("LOGIN", where1, new String[]{""+id});
        db.delete("info", where2, new String[]{""+id});
        Toast.makeText(context,"Account Deleted",Toast.LENGTH_SHORT).show();
    }
List<String> list ;
    Cursor cursor;
    String blood;
    public Cursor specificBloodGroupList(int id){

        switch(id){

            case 1 :
                //<item>A+</item>
                blood = "A+";
                cursor = db.query("info",new String[]{"firstName","contactNo"},"bloodGroup=?",new String[]{""+blood},null,null,null);
                return cursor;
            case 2 :
                //<item>A-</item>
                blood = "A-";
                cursor = db.query("info",new String[]{"firstName","contactNo"},"bloodGroup=?",new String[]{""+blood},null,null,null);
                return cursor;
            case 3 :
                //<item>B+</item>
                blood = "B+";
                cursor = db.query("info",new String[]{"firstName","contactNo"},"bloodGroup=?",new String[]{""+blood},null,null,null);
                return cursor;
            case 4 :
                //<item>B-</item>
                blood = "B-";
                cursor = db.query("info",new String[]{"firstName","contactNo"},"bloodGroup=?",new String[]{""+blood},null,null,null);
                return cursor;
            case 5 :
                //<item>O+</item>
                blood = "O+";
                cursor = db.query("info",new String[]{"firstName","contactNo"},"bloodGroup=?",new String[]{""+blood},null,null,null);
                return cursor;
            case 6 :
                //<item>O-</item>
                blood = "O-";
                cursor = db.query("info",new String[]{"firstName","contactNo"},"bloodGroup=?",new String[]{""+blood},null,null,null);
                return cursor;
            case 7 :
                //<item>AB+</item>
                blood = "AB+";
                cursor = db.query("info",new String[]{"firstName","contactNo"},"bloodGroup=?",new String[]{""+blood},null,null,null);
                return cursor;
            case 8 :
                //<item>AB-</item>
                blood = "AB-";
                cursor = db.query("info",new String[]{"firstName","contactNo"},"bloodGroup=?",new String[]{""+blood},null,null,null);
                return cursor;

        }

        return null;
    }


}