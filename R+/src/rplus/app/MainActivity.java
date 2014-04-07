package rplus.app;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
       Intent cerere=getIntent();
       String actiune=cerere.getAction();
      // String tip = cerere.getType();
       
       if(actiune.equals(Intent.ACTION_SEND)){
    	    Toast.makeText(getApplicationContext(), "de la actiunea de send", Toast.LENGTH_SHORT).show();           
    	}
       else if(actiune.equals("rplus.app.action.LOGIN"))
		       {
		    	   
		    	   Toast.makeText(getApplicationContext(), "de la actiunea de RPLUS", Toast.LENGTH_SHORT).show(); 
		       }
        
       Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
              
               
              Intent i=new Intent(getApplicationContext(),Activity2.class);
              startActivity(i);
            }
        });
        
        
        button.setOnLongClickListener(new View.OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				
				
				
				
				v.setVisibility(View.GONE);
				
				Context context = getApplicationContext();
				CharSequence text = "HAHA a disparut!";
				int duration = Toast.LENGTH_SHORT;

				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
				
				return true;
			}
		});
      
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    

}
