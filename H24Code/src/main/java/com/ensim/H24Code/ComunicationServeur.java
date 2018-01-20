package com.ensim.H24Code;

public class ComunicationServeur {

	OkHttpClient client = new OkHttpClient();

	public void Auth(String login, String mdp){
		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{\r\n    \"email\": \""+login+"\",\r\n    \"password\": \""+mdp+"\"\r\n}");
		Request request = new Request.Builder()
		  .url("https://f24h2018.herokuapp.com/auth/local")
		  .post(body)
		  .addHeader("Content-Type", "application/json")
		  .addHeader("Cache-Control", "no-cache")
		  .addHeader("Postman-Token", "a7c343d0-e873-5778-b49b-961daa54001d")
		  .build();

		Response response = client.newCall(request).execute();
	}
}


public static void main(String [] args) {
	
	Auth("ant1@mill.ant", "Vent");
}
