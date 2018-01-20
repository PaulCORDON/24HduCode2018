package com.ensim.H24Code;

import java.io.IOException;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class ComunicationServeur {

	OkHttpClient client = new OkHttpClient();
	/**
	 * connection au serveur 
	 * @param login
	 * @param mdp
	 * @return
	 */
	public Response Auth(String login, String mdp){

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
		return response;
	}
	
	
	
	public Response CreatePositions(String id){
		OkHttpClient client = new OkHttpClient();
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{\r\n    \"trackId\": \"\"5a5e71a2734d1d347185192c\",\r\n    \"positions\": [\r\n        {\r\n            \"lat\": 47.9848444,\r\n            \"lon\": 0.2373212,\r\n            \"timestamp\": \"2016-06-07T12:21:03.916Z\"\r\n        },\r\n        {\r\n            \"lat\": 47.9848336,\r\n            \"lon\": 0.2388045,\r\n            \"timestamp\": \"2016-06-07T12:21:04.916Z\"\r\n        }\r\n    ]\r\n}");
		Request request = new Request.Builder()
		  .url("https://f24h2018.herokuapp.com/api/positions/bulk")
		  .post(body)
		  .addHeader("Content-Type", "application/json")
		  .addHeader("Authorization", "Bearer "+id)
		  .addHeader("Cache-Control", "no-cache")
		  .addHeader("Postman-Token", "1b8b7eb1-5a98-47a9-c68b-9993ed9aeb9a")
		  .build();
		Response response = null;
		try {
			response = client.newCall(request).execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
		}

	
	
	public Response showPosition(String id, String idPosition) {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
		  .url("https://f24h2018.herokuapp.com/api/positions/"+idPosition)
		  .get()
		  .addHeader("Content-Type", "application/json")
		  .addHeader("Authorization", "Bearer "+id)
		  .addHeader("Cache-Control", "no-cache")
		  .addHeader("Postman-Token", "e522ac4c-4ef2-5696-7939-a5149cce37f3")
		  .build();
		Response response = null;
		try {
			response = client.newCall(request).execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
		
	}	


	
	/**
	 *
	 * @param token
	 * @return
	 */
	public Response describeSeed(String id) {


		Request request = new Request.Builder()
		  .url("https://f24h2018.herokuapp.com/api/seeds/5a632a81f36d287087a19875")
		  .get()
		  .addHeader("Authorization", "Bearer "+id)
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
		return response;
	}
	
public Response searchSeedAround(String id) {

	Request request = new Request.Builder()
	  .url("https://f24h2018.herokuapp.com/api/seeds/search")
	  .get()
	  .addHeader("Authorization", "Bearer "+id)
	  .addHeader("Cache-Control", "no-cache")
	  .addHeader("Postman-Token", "deea109b-90e2-f9bc-a754-1e243685bac1")
	  .build();

	Response response = null;
	try {
		response = client.newCall(request).execute();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	return response;
	
}

public Response ListMyTracks(String id) {
	OkHttpClient client = new OkHttpClient();

	Request request = new Request.Builder()
	  .url("https://f24h2018.herokuapp.com/api/tracks/me")
	  .get()
	  .addHeader("Authorization", "Bearer "+id)
	  .addHeader("Cache-Control", "no-cache")
	  .addHeader("Postman-Token", "646fd91b-4dd3-4f9e-09f4-dc06201e48fb")
	  .build();

	Response response = null;
	try {
		response = client.newCall(request).execute();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return response;
}



public Response ListOtherTeamsTracks(String id) {
	OkHttpClient client = new OkHttpClient();

	Request request = new Request.Builder()
	  .url("https://f24h2018.herokuapp.com/api/tracks/otherTeams")
	  .get()
	  .addHeader("Authorization","Bearer " +id)
	  .addHeader("Cache-Control", "no-cache")
	  .addHeader("Postman-Token", "884929ff-f652-bbf6-7c16-fc7faf48c420")
	  .build();

	Response response = null;
	try {
		response = client.newCall(request).execute();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return response;
}

public Response showATrack(String id, String idFourmie) {
	OkHttpClient client = new OkHttpClient();

	Request request = new Request.Builder()
	  .url("https://f24h2018.herokuapp.com/api/tracks/5a6350688fb12f001481b340")
	  .get()
	  .addHeader("Authorization","Bearer "+id)
	  .addHeader("Cache-Control", "no-cache")
	  .addHeader("Postman-Token", "9fa953d7-79ca-3ef5-85b1-8eaeefbec103")
	  .build();

	Response response = null;
	try {
		response = client.newCall(request).execute();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return response;
}


public Response GetMyUserInfo(String id) {
	OkHttpClient client = new OkHttpClient();
	Request request = new Request.Builder()
			  .url("https://f24h2018.herokuapp.com/api/users/me")
			  .get()
			  .addHeader("Content-Type", "application/json")
			  .addHeader("Authorization", "Bearer "+id)
			  .addHeader("Cache-Control", "no-cache")
			  .addHeader("Postman-Token", "08e15559-5ba3-ca09-57d9-72bbd2cf8b9c")
			  .build();
	Response response = null;
	try {
		response = client.newCall(request).execute();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return response;

}

//public Response showAllPositionsTrack(String id, )

public static void main(String [] args) {
	ComunicationServeur com=new ComunicationServeur();
	System.out.println(com.Auth("ant1@mill.ant", "Vent").isSuccessful());
	
	String token=null;
	String idTrack=null;
	
	
	try {
		token=com.Auth("ant1@mill.ant", "Vent").body().string().split(":\"")[1].split("\"}")[0];
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	try {
		idTrack=com.ListOtherTeamsTracks(token).body().string().split(":\"")[1].split(",")[0];
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

	
	System.out.println("create position: "+com.CreatePositions(token).isSuccessful());
	//System.out.println(com.showPosition(token).isSuccessful());
	System.out.println(com.describeSeed(token).isSuccessful());
	System.out.println(com.searchSeedAround(token).isSuccessful());
	System.out.println(com.ListMyTracks(token).isSuccessful());
	System.out.println(com.ListOtherTeamsTracks(token).isSuccessful());
	try {
		//System.out.println("show position:"+com.showPosition(token).body().string());
		System.out.println("describe seed:" +com.describeSeed(token).body().string());
		System.out.println("search seed around: " +com.searchSeedAround(token).body().string());
		System.out.println("liste de mes tracks:" +com.ListMyTracks(token).body().string());
		System.out.println("liste des tracks autres equipe:"+com.ListOtherTeamsTracks(token).body().string());
		System.out.println("get user info:"+com.GetMyUserInfo(token).body().string());
		System.out.println("show a track:"+com.showATrack(token, idTrack).body().string());

	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println(com.showATrack(token, idTrack).isSuccessful());
	System.out.println(com.GetMyUserInfo(token).isSuccessful());

	
	
}




}
