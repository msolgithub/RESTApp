package com.example.restapp;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.Bundle;
import android.app.ListActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        
        ArrayList<Product> Products = GetProducts();
        
        ListView lv = getListView();
               
        ArrayAdapter<Product> adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Products);
        
        lv.setAdapter(adp);
        
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
    	Product prod = (Product)getListView().getItemAtPosition(position);
    	Toast.makeText(getBaseContext(),Integer.toString(prod.Id) , Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public ArrayList<Product> GetProducts()
    {
    	ArrayList<Product> al = new ArrayList<Product>();
    	
    	HttpClient httpClient = new DefaultHttpClient();
    	HttpGet request = new HttpGet("http://webapi.msolutions.as/api/Products");
    	ResponseHandler<String> handler = new BasicResponseHandler();
   
    	String result = "";
    	JSONArray productArray;
    	try {
    		
			result = httpClient.execute(request,handler);
			
	    	productArray = new JSONArray(result);
	    	
	    	for(int i = 0; i < productArray.length();i++) {
	    		Product p = new Product();
	    		p.Id = productArray.getJSONObject(i).getInt("ID");
	    		//p.ProductCategoryID = productArray.getJSONObject(i).getInt("ProductCategoryID");
	    		p.Name = productArray.getJSONObject(i).getString("Name");
	    		//p.No =  productArray.getJSONObject(i).getString("No");
	    		//p.Price =  productArray.getJSONObject(i).getDouble("Price");
	    		al.add(p);
	    	}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	    	
    	return al;
    }


    
}
