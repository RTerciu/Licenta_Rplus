package com.rplus.modulcomunicatie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
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
        
        
        Button button = (Button) findViewById(R.id.button1);
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
                new HttpAsyncTask().execute("http://192.168.1.132/Rplus_Server/public/getData/"+et1.getText().toString()+"/"+userToken+"/"+apiKey);
                
            	
            	
            	
            	
            }
        });

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
