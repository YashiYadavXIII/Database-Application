package com.example.studentdetails;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity
        implements View.OnClickListener{


    private Button save,update,display,btn1;
    private EditText name,roll,phoneno;
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        save=(Button)findViewById(R.id.save);
        display=(Button)findViewById(R.id.display);
        update=(Button)findViewById(R.id.update);
        btn1=(Button)findViewById(R.id.btn1);
        name=(EditText)findViewById(R.id.name);
        roll=(EditText)findViewById(R.id.roll);
        phoneno=(EditText)findViewById(R.id.marks);
        mydb = new DBHelper(this);
       save.setOnClickListener(this);
        display.setOnClickListener(this);
        update.setOnClickListener(this);
        btn1.setOnClickListener(this);
       // addListenerOnButton(this);    
    
 //final Context ctx=this;
		// btn1=(Button)findViewById(R.id.btn1);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //no inspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

   
    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        ArrayList<String> arrayList=new ArrayList<String>();
        switch (v.getId())
        {

            case R.id.save:
                int t;
                t=Integer.valueOf(roll.getText().toString());
                 
                    mydb.insertStudent(name.getText().toString(),t,phoneno.getText().toString());
                    break;
            case R.id.display:
                arrayList=mydb.getAllStudent();

                    String st="";
                    for(String s:arrayList)
                    {
                        st += s;
                     if(st!="")
                        Toast.makeText(getApplicationContext(), st, Toast.LENGTH_SHORT).show();
                     else
                        Toast.makeText(getApplicationContext(), "Nothing to Display", Toast.LENGTH_SHORT).show();
                    } break;
            case R.id.update:
            mydb.updateStudent(name.getText().toString(), Integer.valueOf(roll.getText().toString()),phoneno.getText().toString());
            break;
            case R.id.btn1:
            	Intent i=new Intent(MainActivity.this,AcademicDetails.class);
            	Toast.makeText(getApplicationContext(), "Academic Details", Toast.LENGTH_SHORT).show();
            	Bundle b=new Bundle();
            	b.putString("rollno", roll.getText().toString());
            	i.putExtras(i);
            	startActivity(i);
         
            	break;
            	
            	
           }
    }
    }

