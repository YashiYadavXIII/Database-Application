package com.example.studentdetails;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
    public class DBHelper1 extends SQLiteOpenHelper {

    public static final int  DATABASE_VERSION=1;
    public static final String DATABASE_NAME = "academic.db";
    public static final String TABLE_NAME = "ACADEMICDETAILS";
    public static final String ROLLNO="Rollno";
    public static final String SUB= "Subject";
    public static final String SEM = "Sem";
    public static final String  T1MARKS= "T1Marks";
    public static final String  T2MARKS="T2Marks";
    public static final String ENTERIES ="create table "+TABLE_NAME +
            "("+ ROLLNO+","+ SUB+" text,"+SEM+" INTEGER PRIMARY KEY AUTOINCREMENT, "+T1MARKS+","+T2MARKS+")";

    public DBHelper1(Context context) {
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

    public boolean insertAcademic(int roll,String sub,int sem,String t1marks,String t2marks)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("RollNo",roll);
        contentValues.put("Subject",sub);
        contentValues.put(SEM,sem);
        contentValues.put(T1MARKS,t1marks);
        contentValues.put(T2MARKS,t2marks);
        db.insert(TABLE_NAME,null,contentValues);
        db.close(); // Closing database connection
        return true;

    }
//for display
    public Cursor getData1(int roll)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME+" where "+ROLLNO+"="+roll+";",null);
        //db.close();
        return res;
    }

   

    public ArrayList<String> getAllAcademic() {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add("Rollno:"+res.getString(+res.getColumnIndex(ROLLNO))+"\nSubject:"+res.getString(res.getColumnIndex(SUB))+"\nSemester"+res.getString(res.getColumnIndex(SEM))+"\nT1marks"+res.getString(res.getColumnIndex(T1MARKS))+"\nT2MARKS"+res.getString(res.getColumnIndex(T2MARKS))+"\n\n");

            res.moveToNext();
        }
        return array_list;
    }


    }

