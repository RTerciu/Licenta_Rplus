package com.rplus;

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
              
               //Toast.makeText(getApplicationContext(), "button apasat",Toast.LENGTH_SHORT).show();
               /*Intent intent=new Intent();
               intent.setAction("rplus.app.action.LOGIN");
               startActivity(intent);*/
               getLogin();
            
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
                TextView t1=(TextView) findViewById(R.id.textView2);
                t1.setText("Nume: "+nume+" cu tokenul "+token);

        }
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
