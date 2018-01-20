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
	
	
	
	public Response CreatePositions(String trackId){
		OkHttpClient client = new OkHttpClient();
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{\r\n    \"trackId\": \""+trackId+",\r\n    \"positions\": [\r\n        {\r\n            \"lat\": 47.9848444,\r\n            \"lon\": 0.2373212,\r\n            \"timestamp\": \"2016-06-07T12:21:03.916Z\"\r\n        },\r\n        {\r\n            \"lat\": 47.9848336,\r\n            \"lon\": 0.2388045,\r\n            \"timestamp\": \"2016-06-07T12:21:04.916Z\"\r\n        }\r\n    ]\r\n}");
		Request request = new Request.Builder()
		  .url("https://f24h2018.herokuapp.com/api/positions/bulk")
		  .post(body)
		  .addHeader("Content-Type", "application/json")
		  .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1YTYzMmE4MWYzNmQyODcwODdhMTk4NzUiLCJyb2xlIjoiYW50IiwiaWF0IjoxNTE2NDU3ODAyLCJleHAiOjE1MTY0NzU4MDJ9.dYuP25Jd8tHmlJnKIOgtKrbqhfHoRo2EiX_ok_7Vlsk")
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

	
	
	public Response showPosition(String id) {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
		  .url("https://f24h2018.herokuapp.com/api/positions/ChIJZ4ayW7yO4kcR3Jr7Wy_82Tk")
		  .get()
		  .addHeader("Content-Type", "application/json")
		  .addHeader("Authorization", id)
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
		  .addHeader("Authorization", "Bearer " +id)
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
	
public Response searchSeedAround() {

	Request request = new Request.Builder()
	  .url("https://f24h2018.herokuapp.com/api/seeds/search")
	  .get()
	  .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1YTYzMmE4MWYzNmQyODcwODdhMTk4NzUiLCJyb2xlIjoiYW50IiwiaWF0IjoxNTE2NDU3NzAyLCJleHAiOjE1MTY0NzU3MDJ9.LZ7l-88HzLot4gFyTpPVyo86mir6jjBQoAhp1rb5_vs")
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

public Response ListMyTracks() {
	OkHttpClient client = new OkHttpClient();

	Request request = new Request.Builder()
	  .url("https://f24h2018.herokuapp.com/api/tracks/me")
	  .get()
	  .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1YTYzMmE4MWYzNmQyODcwODdhMTk4NzUiLCJyb2xlIjoiYW50IiwiaWF0IjoxNTE2NDU2NDg3LCJleHAiOjE1MTY0NzQ0ODd9.DELLy3Mm-lCiU34-ZuZE6YvpvI8eJV0_gWUUXUx2IHc")
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

public Response showATrack(String id) {
	OkHttpClient client = new OkHttpClient();

	Request request = new Request.Builder()
	  .url("https://f24h2018.herokuapp.com/api/tracks/5a632a81f36d287087a19875")
	  .get()
	  .addHeader("Authorization", id)
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

public static void main(String [] args) {
	ComunicationServeur com=new ComunicationServeur();
	System.out.println(com.Auth("ant1@mill.ant", "Vent").isSuccessful());
	
	String token=null;
	
	try {
		System.out.println(com.Auth("ant1@mill.ant", "Vent").body().string().split(":\"")[1].split("\"}")[0]);
		token=com.Auth("ant1@mill.ant", "Vent").body().string().split(":\"")[1].split("\"}")[0];
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//System.out.println(com.CreatePositions("").isSuccessful());
	System.out.println(com.showPosition(token).isSuccessful());
	System.out.println(com.describeSeed(token).isSuccessful());
	System.out.println(com.searchSeedAround().isSuccessful());
	System.out.println(com.ListMyTracks().isSuccessful());
	try {
		System.out.println(com.ListOtherTeamsTracks(token).body().string());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println(com.showATrack(token).isSuccessful());
	System.out.println(com.GetMyUserInfo(token).isSuccessful());

	/*System.out.println(com.searchSeedAround().toString().split("url=")[1].split("}")[0]);
	try {
		System.out.println(com.searchSeedAround().body().string());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
	
	
}




}
