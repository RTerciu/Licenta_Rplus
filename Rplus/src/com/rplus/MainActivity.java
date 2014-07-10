package com.rplus;


import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
		
		Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
              
           
               getLogin();
            
            }
        });	
		
		Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            
            EditText et1=(EditText) findViewById(R.id.editText1);
            EditText et2=(EditText) findViewById(R.id.editText2);

            String s=et1.getText().toString();
            String s2=et2.getText().toString();

            if((!s.isEmpty())&& (!s2.isEmpty()))
            {
            	Intent i=new Intent(getApplicationContext(),ProfileActivity.class);
            	i.putExtra("nume", s);
            	i.putExtra("json", "Logare normala!\n Fiecare aplicatie isi gestioneaza datele in functie de protocolul propriu.");
            	startActivity(i);
            }
            else Toast.makeText(getApplicationContext(), "Nu ati completat datele", Toast.LENGTH_SHORT).show();
            }
        });	
		

	}
	
	
	
    private void getLogin() {
        
        Intent intent = new Intent("rplus.app.action.LOGIN");
        intent.putExtra("app_key", "$2y$10$U8WWhUfYsJbFRZcZdj.Sve.rn3MpyL4.O8CRozmehvXjcPUIanndi");
        startActivityForResult(intent, 2506);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // If the request went well (OK) 
        if (resultCode == Activity.RESULT_OK && requestCode == 2506) {

                String nume=data.getStringExtra("nume");
                String token=data.getStringExtra("Rplus_token");
                //TextView t1=(TextView) findViewById(R.id.textView2);
                //t1.setText("S-au primit date de autentificare, redirectare la pagina de inregistrare");
                try
            	{
	                if(!token.isEmpty()&&(!token.equalsIgnoreCase("nu exista token")))	
	                {
	                Intent i=new Intent(getApplicationContext(),ProfileActivity.class);
	            	i.putExtra("nume", "Ceva");
	            	i.putExtra("json", token);
	            	startActivity(i);
	                }
	                else
						Toast.makeText(getApplicationContext(), "Eroare!Nu se poate realiza autentificarea cu Rplus la acest moment!\n Verificati Conexiunea la Internet!", Toast.LENGTH_SHORT).show();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Toast.makeText(getApplicationContext(), "Eroare!Nu se poate realiza autentificarea cu Rplus la acest moment!\n Verificati Conexiunea la Internet!", Toast.LENGTH_SHORT).show();
					e.printStackTrace();
				}
		                
                
                //else Toast.makeText(getApplicationContext(), "Nu se poate realiza autentificarea cu Rplus la acest moment!", Toast.LENGTH_SHORT).show();
                	
        }
    }

    
    public Boolean checkErrorJson(String in)
    {
		    try{
		    
		    	JSONObject js =new JSONObject(in);	
		    	
		    	if(js.has("eroare"))
		    			return true;
		    	
		    } catch (Exception e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
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
		
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}



}
