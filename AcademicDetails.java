package com.example.studentdetails;

import java.util.ArrayList;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AcademicDetails extends Activity implements View.OnClickListener
{
	 private Button save,display,b2;
	    private EditText sem,r,t1marks,t2marks,sub;
	    DBHelper1 academicdb;

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.academic);
	        save=(Button)findViewById(R.id.save);
	        display=(Button)findViewById(R.id.display);
	       sub=(EditText)findViewById(R.id.sub);
	       r=(EditText)findViewById(R.id.roll);
	       t1marks=(EditText)findViewById(R.id.t1marks);
	       t2marks=(EditText)findViewById(R.id.t2marks);
	       sem=(EditText)findViewById(R.id.sem);
	       b2=(Button)findViewById(R.id.b2);
	       academicdb=new DBHelper1(this);
	       save.setOnClickListener(this);
	       display.setOnClickListener(this);
	       b2.setOnClickListener(this);
	       }

	  public boolean onKeyDown(int keyCode,KeyEvent event)
	    {
	    	if(keyCode == KeyEvent.KEYCODE_DPAD_CENTER);
	    	{
	    		startActivity(new Intent("com.example.studentdetails.SMSActivity"));
	    	}
			return false;
	    	
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
	    public void onClick(View v) {
	        ArrayList<String> arrayList=new ArrayList<String>();

	      switch (v.getId())
	        {

	            case R.id.save:
	                int roll,s;
                        roll=Integer.valueOf(r.getText().toString());
	                   
	                    s=Integer.valueOf(sem.getText().toString());
	                  academicdb.insertAcademic(roll,sub.getText().toString(),s,t1marks.getText().toString(),t2marks.getText().toString());

	                 break;
	            case R.id.display:
	                arrayList=academicdb.getAllAcademic();

	                    String st="";
	                    for(String sa:arrayList)
	                    {
	                       st += sa;
	                     if(st!="")
	                        Toast.makeText(getApplicationContext(), st, Toast.LENGTH_SHORT).show();
	                     else
	                        Toast.makeText(getApplicationContext(), "Nothing to Display", Toast.LENGTH_SHORT).show();
	                    }
	                    break;
	                    
	            case R.id.b2:

	    	    	Intent i=new Intent(AcademicDetails.this,SMSActivity.class);
	    	    	//Toast.makeText(getApplicationContext(), "Send SMS", Toast.LENGTH_SHORT).show();
	    	    	startActivity(i);
	    	    	/*PendingIntent pi=PendingIntent.getActivity(getApplicationContext(),0, i,0);
	    	    	Cursor res=academicdb.getData1(Integer.parseInt(r.getText().toString()));	*/    	    
	            	break;
	                    }

	    }
	}