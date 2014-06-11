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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	TextView RaspunsJson;
	TextView conectat;
	String raspunsHttpGet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        //asculta Intent-urile de tip Rplus venite de la alte aplicatii
       // AscultaCereri();
        
        //butoanele fac operatiile de adaugare a unui cont
        
        Button button = (Button) findViewById(R.id.button1);
        Button button1= (Button) findViewById(R.id.button2);
       
      
        
        
                
        
        
        /*
         * 
         * Actiunea pentru Button1
         * Cauta daca sunt conturi Rplus pastrate pe telefon si daca nu
         * adauga unul
         * */
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
				AccountManager mAccountManager = AccountManager.get(getApplicationContext());
				
				//Account[] a=mAccountManager.getAccounts();
				Account[] a=mAccountManager.getAccountsByType("com.rplus");
				
				if(a.length>0)
	        		{
					Toast.makeText(getBaseContext(),"Ai deja un cont inregistrat!\nFoloseste Meniu->Resetare pentru a sterge contul curent!", Toast.LENGTH_LONG).show();
	        		}
				else
				{
				EditText et1= (EditText) findViewById(R.id.editText1);			
				EditText et2= (EditText) findViewById(R.id.editText2);
		
              	String myString=et2.getText().toString()+et1.getText().toString();
              	//String apiKey="$2y$10$U8WWhUfYsJbFRZcZdj.Sve.rn3MpyL4.O8CRozmehvXjcPUIanndi";

            	Date d=new Date();
            	String format = new SimpleDateFormat("yyyyMMddHH").format(d);
            	String initialToken=md5(myString);
            	String userToken=md5(initialToken+format);
            	ProgressBar progressBar=(ProgressBar)findViewById(R.id.progressBar1);
            	
            	String[] date_cont=new String[2];
            	date_cont[0]=et1.getText().toString();
            	date_cont[1]=initialToken;
            	
               new HttpAsyncTask(progressBar,"1",date_cont).execute("http://rplus.co/checkUser/"+et1.getText().toString()+"/"+userToken);
				
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
              	String apiKey="$2y$10$uzbt86lJrhS669Djzkq42uWFC4suyOvGQaL.vPx8irx7VtYo5K3nm";
              	
            	
            	
            	Date d=new Date();
            	String format = new SimpleDateFormat("yyyyMMddHH").format(d);
            	String initialToken=md5(myString);
            	String userToken=md5(initialToken+format);
            	//tv.setText(initialToken+format+" "+userToken);
            	
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
                //raspunsHttpGet="nimic";
            	ProgressBar progressBar=(ProgressBar)findViewById(R.id.progressBar1);
            	RaspunsJson.setText("");
               //new HttpAsyncTask(progressBar,"2",RaspunsJson).execute("http://rplus.co/checkApp/"+apiKey);
               // new HttpAsyncTask(progressBar,"1",RaspunsJson).execute("http://rplus.co/checkUser/"+et1.getText().toString()+"/"+userToken);
                new HttpAsyncTask(progressBar,"3",RaspunsJson).execute("http://rplus.co/getData/"+et1.getText().toString()+"/"+userToken+"/"+apiKey);

                
                
                //RaspunsJson.setText(raspunsHttpGet);
            	
            	
            	
            }
        });

    }
    
    //de verificat ca aplicatia exista
    public String verificaAplicatie()
    {
    	
    	
    return "Rplus";	
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
    	
    	private  ProgressBar pb;
    	private int codOp;
    	private Object ob;
    	
    	
    	public HttpAsyncTask( ProgressBar p , String op , Object o)
    	{	
    	this.pb=p;
    	this.codOp=Integer.parseInt(op);
    	this.ob=o;

    	}
    	
    	
    	  @Override
    	    protected void onPreExecute() {
            pb.setVisibility(View.VISIBLE); 

    	    }
    	
        @Override
        protected String doInBackground(String... urls) {
        			
        	
            return GET(urls[0]);
        
        	
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
        	pb.setVisibility(View.INVISIBLE);

        	switch(codOp)
        	{
        	case 1:  
        			if(!checkErrorJson(result))
        				{
        				AccountManager mAccountManager = AccountManager.get(getApplicationContext());
        				Account myAccount=new Account(((String[])ob)[0],"com.rplus");
        				mAccountManager.addAccountExplicitly(myAccount, ((String[])ob)[1],null);     				
        				Toast.makeText(getApplicationContext(), "Contul "+((String[])ob)[0]+" a fost verificat si adaugat cu success!",Toast.LENGTH_SHORT).show();
        				}
        			else
        				Toast.makeText(getApplicationContext(), "Credentialele introduse de dumneavoastra nu sunt valide!\n Va rugam reincercati!",Toast.LENGTH_SHORT).show();

        			break;
        	case 2:
        			((TextView) ob).append(result);
        			break;
        	case 3:	
        			//JSONArray names;
        			try {
					JSONObject js =new JSONObject(result);
					String output="";
					JSONArray names=js.names();
					for(int i=0;i<names.length();i++)
						output+=names.getString(i)+" : "+js.getString(names.getString(i))+ "\n";
					
					
					((TextView) ob).append(output);
				   } catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				   }
        			
        			break;
        	}
        	
        	
        	
       }
    }
    
    /*
     * Functia care primeste ca intrare un string in format json
     * si verifica daca acesta este un mesaj de eroare
     * */
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
           
        	
        	String url = "http://rplus.co/signup#formular_inregistrare";
        	Intent i = new Intent(Intent.ACTION_VIEW);
        	i.setData(Uri.parse(url));
        	startActivity(i);
        	
        }
        
        if(id== R.id.action_settings2)
        {
        	
        	/*
             * La apasarea butonului de Resetare din
             * meniu se realizeaza stergerea tuturor conturilor asociate cu aplicatia Rplus
             * de pe telefon la apasarea lui
             * 
             */
        	
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
        return super.onOptionsItemSelected(item);
    }



}
