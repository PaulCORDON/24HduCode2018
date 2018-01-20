package com.ensim.H24Code;

import java.io.IOException;

import javax.naming.CommunicationException;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class ComunicationServeur {

	OkHttpClient client = new OkHttpClient();
	
	public String Auth(String login, String mdp){
		OkHttpClient client = new OkHttpClient();
		Response response=null;
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{\r\n    \"email\": \""+login+"\",\r\n    \"password\": \""+mdp+"\"\r\n}");
		Request request = new Request.Builder()
		  .url("https://f24h2018.herokuapp.com/auth/local")
		  .post(body)
		  .addHeader("Content-Type", "application/json")
		  .addHeader("Cache-Control", "no-cache")
		  .addHeader("Postman-Token", "a7c343d0-e873-5778-b49b-961daa54001d")
		  .build();

		try {
			response = client.newCall(request).execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response.toString();
	}
	public String describeSeed(String token) {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		  .url("https://f24h2018.herokuapp.com/api/seeds/")
		  .get()
		  .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1YTYzMmE4MWYzNmQyODcwODdhMTk4NzUiLCJyb2xlIjoiYW50IiwiaWF0IjoxNTE2NDU2NDg3LCJleHAiOjE1MTY0NzQ0ODd9.DELLy3Mm-lCiU34-ZuZE6YvpvI8eJV0_gWUUXUx2IHc")
		  .addHeader("Cache-Control", "no-cache")
		  .addHeader("Postman-Token", "75db8d96-8150-0420-8fd4-2b2aa0ddcf26")
		  .build();

		Response response = null;
		try {
			response = client.newCall(request).execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response.toString();
	}


public static void main(String [] args) {
	ComunicationServeur com=new ComunicationServeur();
	String auth1Token= com.Auth("ant1@mill.ant", "Vent");
	System.out.println(com.describeSeed(auth1Token));
	
}




}
