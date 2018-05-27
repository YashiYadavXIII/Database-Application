package com.example.studentdetails;


import android.Manifest;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SMSActivity extends Activity {
   // SQLiteDatabase db;
    EditText enroll,phoneno,msg;
    Button btnSend,btnChk;
    Cursor cursor=null,cursor1=null;
    String detail="yashi";
    DBHelper1 academicdb;
    DBHelper mydb;
    LinearLayout llSms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        btnSend=(Button)findViewById(R.id.send);
        enroll=(EditText)findViewById(R.id.enroll);
        phoneno=(EditText)findViewById(R.id.msghere);
        msg=(EditText)findViewById(R.id.mymsghere);
        academicdb=new DBHelper1(this);
        mydb=new DBHelper(this);
        btnChk=(Button)findViewById(R.id.checkbutton);



        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)throws SecurityException {

                sendSms();
                clear();
                Toast.makeText(SMSActivity.this,"SMS sent...",Toast.LENGTH_LONG).show();
            }
        });
        btnChk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                int roll=Integer.parseInt(enroll.getText().toString());
                cursor = mydb.getData(roll);
	            if(cursor.moveToNext())
                {
                    
                    phoneno.setText(cursor.getString(cursor.getColumnIndex("Phoneno")));

                    cursor1 = academicdb.getData1(roll);//Getting data from academicdetails.class 
                    String de="";
                    if(cursor1.moveToNext())
                    {
                        int t1marks=0,t2marks=0;
                        int total=0;
                       t1marks=Integer.parseInt(cursor1.getString(cursor1.getColumnIndex("T1Marks")));
                        t2marks=Integer.parseInt(cursor1.getString(cursor1.getColumnIndex("T2Marks")));
                        total=t1marks+t2marks;
                        
                        de=" RCOEM "+cursor.getString(cursor.getColumnIndex("Name"))+"\n Roll : "+roll+"\nSub : "+cursor1.getString(cursor1.getColumnIndex("Subject"))+"\n Marks"+total;
                         Toast.makeText(SMSActivity.this,de+"",Toast.LENGTH_LONG).show();
                         msg.setText(de);
                         
                    }
                  

                }
                else
                    Toast.makeText(SMSActivity.this,"Student Does Not Exist...",Toast.LENGTH_LONG).show();

	            academicdb.close();

            }
        });
    }

    private void clear() {
        enroll.setText("");
        phoneno.setText("");
        msg.setText("");
    }

    private void sendSms() {
        SmsManager mn=SmsManager.getDefault();
        mn.sendTextMessage(phoneno.getText().toString(),null,msg.getText().toString(),null,null);
        Log.e("sms","SMS has been sent");
    }
}
