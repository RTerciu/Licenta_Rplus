package com.rplus.modulrplus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.support.v7.app.ActionBarActivity;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	TextView RaspunsJson;
	TextView conectat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        //asculta Intent-urile de tip Rplus venite de la alte aplicatii
        AscultaCereri();
        
        //butoanele fac operatiile de adaugare a unui cont
        
        Button button = (Button) findViewById(R.id.button1);
        Button button1= (Button) findViewById(R.id.button2);
        Button button2= (Button) findViewById(R.id.button3);
      
        /*
         * Button2 realizeaza stergerea tuturor conturilor asociate cu aplicatia Rplus
         * de pe telefon la apasarea lui
         * 
         */
        
        button2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AccountManager mAccountManager = AccountManager.get(getApplicationContext());
				Account[] accounts=mAccountManager.getAccountsByType("com.rplus");
				
				
				   final Handler handler = new Handler (); 

				    AccountManagerCallback<Boolean> callback = new AccountManagerCallback<Boolean>()
				    {
				        @Override
				        public void run(AccountManagerFuture<Boolean> arg0)
				        {
				           // nada
				        }
				    };
				
				
			    for (int index = 0; index < accounts.length; index++) {
			    		String x=accounts[index].name;
			        	mAccountManager.removeAccount(accounts[index], callback, handler);
						Toast.makeText(getBaseContext(), "Cont "+x+" sters", Toast.LENGTH_SHORT).show();

			        }
				
				
			}
		});
        
        
        
        /*
         * 
         * Actiunea pentru Button1
         * Cauta daca sunt conturi Rplus pastrate pe telefon si daca nu
         * adauga unul
         * */
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
				AccountManager mAccountManager = AccountManager.get(getApplicationContext());
				Account[] a=mAccountManager.getAccounts();
				
				EditText et1= (EditText) findViewById(R.id.editText1);
				EditText et2= (EditText) findViewById(R.id.editText2);
				
				String names="Nume conturi: ";
				int i; 
				for (i = 0; i < a.length; i++) {
				        if(a[i].type.equalsIgnoreCase("com.rplus")&&a[i].name.equals(et1.getText().toString()))
				        	{Toast.makeText(getBaseContext(), "Esti deja autentificat!", Toast.LENGTH_LONG).show();
				        	break;				        	
				        	}
				    }
				if(i==a.length)
					{
					Account myAccount=new Account(et1.getText().toString(),"com.rplus");
					mAccountManager.addAccountExplicitly(myAccount, et2.getText().toString(),null);
					Toast.makeText(getBaseContext(), "Cont adaugat!", Toast.LENGTH_LONG).show();
					}
				
				
			}
		});
        
        
       /*
        * Buttonul1 realizeaza trimiterea datelor din formularul de contact catre 
        *  Rplus_Server si primeste un JSON cu datele de autentificare
        *  
        *  
        */
        
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            	TextView tv = (TextView) findViewById(R.id.textView1);
            	
            	EditText et1= (EditText) findViewById(R.id.editText1);
            	EditText et2= (EditText) findViewById(R.id.editText2);

              	String myString=et2.getText().toString()+et1.getText().toString();
              	String apiKey="$2y$10$U8WWhUfYsJbFRZcZdj.Sve.rn3MpyL4.O8CRozmehvXjcPUIanndi";
              	
            	
            	
            	Date d=new Date();
            	String format = new SimpleDateFormat("yyyyMMddHH").format(d);
            	String initialToken=md5(myString);
            	String userToken=md5(initialToken+format);
            	tv.setText(initialToken+format+" "+userToken);
            	
            	RaspunsJson = (TextView) findViewById(R.id.textView2);
            	conectat=(TextView)findViewById(R.id.textView3);
                //tvIsConnected = (TextView) findViewById(R.id.tvIsConnected);
         
                // check if you are connected or not
                if(isConnected()){
                	conectat.setBackgroundColor(0xFF00CC00);
                    conectat.setText("Esti Conectat la Internet!");
                }
                else{
                   Toast.makeText(getApplicationContext(), "Nu esti conectat la Internet",Toast.LENGTH_SHORT).show();
                   conectat.setBackgroundColor(0xFFFF0000);
                   conectat.setText("Nu esti conectat la Internet!");
                }
         
                // call AsynTask to perform network operation on separate thread
                new HttpAsyncTask().execute("http://192.168.1.10/Rplus_Server/public/getData/"+et1.getText().toString()+"/"+userToken+"/"+apiKey);
                
            	
            	
            	
            	
            }
        });

    }

    
    public void AscultaCereri()
    {
    	
    	
        Intent cerere=getIntent();
        String actiune=cerere.getAction();
       // String tip = cerere.getType();
        
        if(actiune.equals(Intent.ACTION_SEND)){
     	    Toast.makeText(getApplicationContext(), "de la actiunea de send", Toast.LENGTH_SHORT).show();           
     	}
        else if(actiune.equals("rplus.app.action.LOGIN"))
 		       {
     	   		String RequestAppId=cerere.getStringExtra("app_key");
     	   		
     	   		//verific la server daca aplicatia este una inregistrata
     	   		//aici hardcodat
     	   		if(RequestAppId.equals("1234"))
     	   		{ Random r=new Random();
     	   		   int numar1=r.nextInt(10);
     	   		   int numar2=r.nextInt(20)+10;
 		    	   //Toast.makeText(getApplicationContext(), "de la actiunea de RPLUS", Toast.LENGTH_SHORT).show();
     	   		   cerere.putExtra("valid_app", true);
 		    	   cerere.putExtra("nume", "Radu"+numar1);
 		    	   cerere.putExtra("Rplus_token", "token"+numar2);
 		    	   setResult(Activity.RESULT_OK, cerere);
 		    	   finish();
     	   		
     	   		} 
     	   		else 
     	   		{
     	   			//Toast.makeText(getApplicationContext(), RequestAppId, Toast.LENGTH_SHORT).show();
     	   		
     	   		cerere.putExtra("valid_app",false);
     	   		cerere.putExtra("nume", "NeAutorizat");
 		    	cerere.putExtra("Rplus_token", "NeAutorizat");
 		    	
 		    	setResult(Activity.RESULT_OK, cerere);
 		    	finish();	
 		    	
     	   		}
     	   		
 		       }	
    	
    }
    
    
    public static String GET(String url){
        InputStream inputStream = null;
        String result = "";
        try {
 
            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();
 
            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
 
            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();
 
            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";
 
        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
 
        return result;
    }
 
    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;
 
        inputStream.close();
        return result;
 
    }
 
    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) 
                return true;
            else
                return false;   
    }
    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
 
            return GET(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
            RaspunsJson.setText(result);
       }
    }

    public static final String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
