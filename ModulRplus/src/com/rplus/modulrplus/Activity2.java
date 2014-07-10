package com.rplus.modulrplus;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

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
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Activity2 extends ActionBarActivity {

	
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity2);
	        AscultaCereri();
	        
	 }
	
	
	 
	    public void AscultaCereri()
	    {
	    	
	        Intent cerere=getIntent();
	        String actiune=cerere.getAction();
	        
	        if(actiune.equals(Intent.ACTION_SEND)){
	     	    Toast.makeText(getApplicationContext(), "de la actiunea de send", Toast.LENGTH_SHORT).show();           
	     	}
	        else if(actiune.equals("rplus.app.action.LOGIN"))
	 		       {
	     	   		String RequestAppId=cerere.getStringExtra("app_key");
	     	   		
    	   
	 		    	  AccountManager mAccountManager = AccountManager.get(getApplicationContext());
	 		    	  Account[] accounts=mAccountManager.getAccountsByType("com.rplus");
	 		    	   
		 		    	 if(accounts.length==0)
		 		    	 { 
		 		    	   
		 		    		 
			 		    	 cerere.putExtra("valid_app", true);
			 		    	 cerere.putExtra("nume", "nu exista cont");
			 		    	 cerere.putExtra("Rplus_token", "nu exista token");
			 		    	 setResult(Activity.RESULT_OK, cerere);	
			 		    	 finish();
		 		    	 }
		 		    	 else
		 		    	 {
		 		    	 String email=accounts[0].name;
		 		    	 String token=mAccountManager.getPassword(accounts[0]);
		 		    	 
		 		    	 Date d=new Date();
		 		    	 String format = new SimpleDateFormat("yyyyMMddHH").format(d);
		 		    	// String apiKey="$2y$10$uzbt86lJrhS669Djzkq42uWFC4suyOvGQaL.vPx8irx7VtYo5K3nm";
		 		    	 String userToken=md5(token+format); 
		 		    	 
 
		 	               BigInteger p=new BigInteger(128,90,new Random());
		 	               BigInteger g=new BigInteger(128,90,new Random());
		 	               BigInteger a=new BigInteger(128,new Random());
		 	               BigInteger A=g.modPow(a,p);
		
		 	               BigInteger[] bigs= new BigInteger[4];
		 	               bigs[0]=p;
		 	               bigs[1]=g;
		 	               bigs[2]=a;
		 	               
		 		    new HttpAsyncTask(cerere,bigs).execute("http://rplus.co/getData/"+email+"/"+userToken+"/"+RequestAppId+"/"+p.toString()+"/"+g.toString()+"/"+A.toString());
		 		    	 }
		 		    	 
	   		
	 		       }	
	    	
	    }
	 
	    
	    
	    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
	    	
	    	private Intent i;
	    	BigInteger a;
	    	BigInteger p;
	    	BigInteger g;
	    	
	    	
	    	public HttpAsyncTask(Intent intent, BigInteger[] bigs)
	    	{	
	    	this.i=intent;
    		this.p=bigs[0];
    		this.g=bigs[1];
    		this.a=bigs[2];
	    	}
	    	
	    	

	    	
	        @Override
	        protected String doInBackground(String... urls) {
	        			
	        	
	            return GET(urls[0]);
	        
	        	
	        }
	        // onPostExecute displays the results of the AsyncTask.
	        @Override
	        protected void onPostExecute(String result) {
	             
	        	
	        	
	        	
	        	
	        	 i.putExtra("valid_app", true);
		    	 i.putExtra("nume", "Raspuns");
		    	 try {
		    		   JSONObject raspuns=new JSONObject(result);
	        			BigInteger B=new BigInteger(raspuns.getString("B"));
	        			//Calculez cheia de sesiune
	        			BigInteger s1=B.modPow(a, p);	 
		    		 
		    		 
		    		 
					i.putExtra("Rplus_token",decripteaza(raspuns.getString("rezultat"),s1));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	 setResult(Activity.RESULT_OK, i);
		    	 finish();
		    	
	        	
	        }
	    
	    
	    }
	    
	    public static String decripteaza(String in,BigInteger s) throws Exception
	    {
	    
	    byte[] data = Base64.decode(in, Base64.DEFAULT);
	    String ivec="1234567812345678";
	    //String key=md5("1234567812345678");
	    String key=md5(s.toString());
	    SecretKeySpec cheie = new SecretKeySpec(key.getBytes(), "AES");
	    IvParameterSpec iv= new IvParameterSpec(ivec.getBytes());
	    String text="";
		//text = new String(data, "UTF-8");
		Cipher cifru= Cipher.getInstance("AES/CBC/NOPADDING");
		cifru.init(Cipher.DECRYPT_MODE,cheie,iv);
		byte[] decriptat=cifru.doFinal(data);
		return new String(decriptat);
	    
		
	    
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
	
	
}
