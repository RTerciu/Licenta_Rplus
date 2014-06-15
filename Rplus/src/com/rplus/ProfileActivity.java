package com.rplus;

import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);

		Intent c=getIntent();
		String nume=c.getStringExtra("nume");
		String json=c.getStringExtra("json");
		TextView tv=(TextView) findViewById(R.id.textView1);
		TextView tv1=(TextView) findViewById(R.id.textView2);
		tv.setText("Bine ai venit, "+nume);
		try {
			JSONObject js=new JSONObject(json);
			
			String url="http://rplus.co/"+js.getString("avatar");
			//tv1.append("\n" + url);
			tv.setText("Bine ai venit, "+js.getString("username"));
			new DownloadImageTask((ImageView) findViewById(R.id.imageView1)).execute(url);
			
			String output="";
			JSONArray names=js.names();
			for(int i=0;i<names.length();i++)
				output+=names.getString(i)+" : "+js.getString(names.getString(i))+ "\n";
			
			tv1.append("\n\n" + output);

		
		} catch (JSONException e) {
			tv.setText("Bine ai venit, "+nume);
			tv1.append("\n"+json);
			e.printStackTrace();
		}
		
		
            
     }
       
	
		
	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
	    ImageView bmImage;

	    public DownloadImageTask(ImageView bmImage) {
	        this.bmImage = bmImage;
	    }

	    protected Bitmap doInBackground(String... urls) {
	        String urldisplay = urls[0];
	        Bitmap mIcon11 = null;
	        try {
	            InputStream in = new java.net.URL(urldisplay).openStream();
	            mIcon11 = BitmapFactory.decodeStream(in);
	        } catch (Exception e) {
	            Log.e("Error", e.getMessage());
	            e.printStackTrace();
	        }
	        return mIcon11;
	    }

	    protected void onPostExecute(Bitmap result) {
	        bmImage.setImageBitmap(result);
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
