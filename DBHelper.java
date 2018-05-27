package com.example.studentdetails;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;

import java.util.ArrayList;
    public class DBHelper extends SQLiteOpenHelper {

    public static final int  DATABASE_VERSION=1;
    public static final String DATABASE_NAME = "MYDB.db";
    public static final String TABLE_NAME = "STUDENTDETAILS";
    public static final String NAME = "Name";
    public static final String ROLLNO = "Rollno";
    public static final String PHONENO = "Phoneno";
    public static final String ENTERIES ="create table "+TABLE_NAME +
            "("+ NAME+" text,"+ROLLNO+" INTEGER PRIMARY KEY AUTOINCREMENT, "+PHONENO+" text)";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(ENTERIES);
   }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS"+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertStudent(String Name,int roll,String phoneno)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("Name",Name);
        contentValues.put("Rollno",roll);
        contentValues.put("Phoneno",phoneno);
        db.insert("STUDENTDETAILS",null,contentValues);
        db.close(); // Closing database connection
        return true;

    }
//for display
    public Cursor getData(int roll)
    {
        SQLiteDatabase db=this.getReadableDatabase();
                Cursor res=db.rawQuery("select * from "+TABLE_NAME+" where Rollno="+roll+";",null);
      //  db.close();
                return res;

    }

    public boolean updateStudent(String Name,int roll,String phoneno)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("Name",Name);
        contentValues.put("Rollno",roll);
        contentValues.put("PHONENO",phoneno);
        db.update("STUDENTDETAILS", contentValues, "rollno = ? ", new String[] { Integer.toString(roll) } );
        db.close();
        return true;
    }

   

    public ArrayList<String> getAllStudent() {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add("Name:"+res.getString(+res.getColumnIndex(NAME))+"\nRollno:"+res.getString(res.getColumnIndex(ROLLNO))+"\nPhoneno"+res.getString(res.getColumnIndex(PHONENO))+"\n\n");

            res.moveToNext();
        }
        return array_list;
    }

    }

