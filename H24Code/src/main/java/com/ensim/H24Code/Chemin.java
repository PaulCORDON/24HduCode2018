package com.ensim.H24Code;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.sun.javafx.css.CalculatedValue;

import netscape.javascript.JSObject;

public class Chemin extends ArrayList<Position> {
	
	boolean utilisable;
	int temps;
	
	void afficheTemps() {
		for(Position p : this) {
			System.out.println(p.getTimestamp());
		}
	}
	
	public void calculItineraire () throws IOException {
		
		
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
		  .url("https://roads.googleapis.com/v1/snapToRoads?path=47.984393,%200.236012|47.984946,%200.238951&interpolate=true&key=AIzaSyBdS5QGJC5iNRcKmAJxb3I7XF9TiX5Rj8Y")
		  .get()
		  .addHeader("Cache-Control", "no-cache")
		  .addHeader("Postman-Token", "6ef08fe5-1f6e-939b-37ee-c7d12a755ea6")
		  .build();
		Response response = client.newCall(request).execute();
		JSONObject jsonObj = new JSONObject(response.body().string());
		//System.out.println(jsonObj);
		JSONArray tabPosition = new JSONArray();
		tabPosition = jsonObj.getJSONArray("snappedPoints");
		//System.out.println(tabPosition);
		for (int i=0 ; i<tabPosition.length(); i++ ) {
			JSONObject point = (JSONObject) tabPosition.get(i);
			JSONObject location = (JSONObject) point.get(("location"));
			double lat = location.getDouble("latitude");
			double lon = location.getDouble("longitude");
			
			//System.out.println(lat);
		//	System.out.println(lon);
			this.add(new Position (lat,lon));
			
		}
		System.out.println(this);
	}
	
	
	public static void main (String [] args ) throws IOException {
		
		Chemin c = new Chemin ();
		c.calculItineraire();
		
		
	}
	
	
}
