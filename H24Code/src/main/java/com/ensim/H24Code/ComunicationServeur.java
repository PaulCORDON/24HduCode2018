package com.ensim.H24Code;

public class ComunicationServeur {

	OkHttpClient client = new OkHttpClient();

	MediaType mediaType = MediaType.parse("application/json");
	RequestBody body = RequestBody.create(mediaType, "{\r\n    \"email\": \"test@example.com\",\r\n    \"password\": \"test\"\r\n}");
	Request request = new Request.Builder()
	  .url("https://f24h2018.herokuapp.com/auth/local")
	  .post(body)
	  .addHeader("Content-Type", "application/json")
	  .addHeader("Cache-Control", "no-cache")
	  .addHeader("Postman-Token", "3954a6ce-99b2-c2db-65ed-6c3b271a88e4")
	  .build();

	Response response = client.newCall(request).execute();
}
