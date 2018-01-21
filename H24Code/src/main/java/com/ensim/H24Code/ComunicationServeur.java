package com.ensim.H24Code;

import java.io.IOException;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class ComunicationServeur {

	String idAnt1="5a632a81f36d287087a19875";
	OkHttpClient client = new OkHttpClient();
	/**
	 * Connection au serveur 
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
	
	
	/**
	 * Creation des positions 
	 * @param id
	 * @param idTrack
	 * @param positions tableau de toutes les positions retournées par la méthode createTrack de la classe fourmie
	 * @return
	 */
	public Response CreatePositions(String id, String idTrack,String positions){
		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{\r\n    \"trackId\": \""+idTrack+"\",\r\n    \"positions\": "+positions+"\r\n\r\n}");
		Request request = new Request.Builder()
		  .url("https://f24h2018.herokuapp.com/api/positions/bulk")
		  .post(body)
		  .addHeader("Content-Type", "application/json")
		  .addHeader("Authorization", "Bearer "+id)
		  .addHeader("Cache-Control", "no-cache")
		  .addHeader("Postman-Token", "80c0a7b4-48c3-c9a8-1ff0-95418d911853")
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
	 * donne toute les informations d'une position à partir de son ID
	 * @param id
	 * @param idPosition
	 * @return
	 */
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
	 *Méthode qui retourne la description d'une graine à partir de son id
	 * @param token
	 * @return
	 */
	public Response describeSeed(String id, String idSeed) {


		Request request = new Request.Builder()
		  .url("https://f24h2018.herokuapp.com/api/seeds/"+idSeed)
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
	
	
/**
 * Méthode qui retourne les graines au alentour	
 * @param id
 * @return
 */
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


/**
 * Méthode qui retourne la liste de toutes les tracks que nous avons créées
 * @param id
 * @return
 */
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


/**
 * Méthode qui retourne la liste de toutes les tracks qu'ont créé nos adversaire 
 * @param id
 * @return
 */
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


/**
 * Méthode qui retourne une track créé par nos adversaire à partir de son Id 
 * @param id
 * @param idTrack
 * @return
 */
public Response showATrack(String id, String idTrack) {
	OkHttpClient client = new OkHttpClient();

	Request request = new Request.Builder()
	  .url("https://f24h2018.herokuapp.com/api/tracks/"+idTrack)
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



/**
 * Méthode qui retourne nos infos utilisateur
 * @param id
 * @return
 */
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


/**
 * Méthode qui retourne toutes les position d'une track à partir de son Id
 * @param id
 * @param idTrack
 * @return
 */
public Response showAllPositionsTrack(String id, String idTrack) {
	
	OkHttpClient client = new OkHttpClient();
	Request request = new Request.Builder()
	  .url("https://f24h2018.herokuapp.com/api/tracks/"+idTrack+"/positions")
	  .get()
	  .addHeader("Authorization", "Bearer "+id)
	  .addHeader("Cache-Control", "no-cache")
	  .addHeader("Postman-Token", "3e4732cc-f298-694a-1cea-847cdc3de4cf")
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
 * Méthode qui créé une analyse de cigale sur une position d'une track
 * @param id
 * @param trackId
 * @return
 */
public Response createAnalyse(String id, String trackId,String positionId) {
	OkHttpClient client = new OkHttpClient();
	MediaType mediaType = MediaType.parse("application/json");
	RequestBody body = RequestBody.create(mediaType, "{\r\n    \"trackId\": \""+trackId+"\",\r\n    \"positionId\": \""+positionId+",\r\n    \"description\": \"dépassement de vitesse  : 75,6  > 50\"\r\n}");
	Request request = new Request.Builder()
	  .url("https://f24h2018.herokuapp.com/api/analyses")
	  .post(body)
	  .addHeader("Content-Type", "application/json")
	  .addHeader("Authorization", "Bearer "+id)
	  .addHeader("Cache-Control", "no-cache")
	  .addHeader("Postman-Token", "08e11df2-9f46-d63d-47d4-ac5101c37a7c")
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
 * Méthode qui créée une track
 * @param nomTrack
 * @param id
 * @param startSeedId
 * @param endSeedId
 * @return
 */
public Response createTrack(String nomTrack, String id, String startSeedId, String endSeedId) {
	
	OkHttpClient client = new OkHttpClient();
	MediaType mediaType = MediaType.parse("application/json");
	RequestBody body = RequestBody.create(mediaType, "{\r\n    \"name\": \""+nomTrack+"\",\r\n    \"info\": \"vers l'infini et ...\",\r\n    \"startSeedId\": \""+startSeedId+"\",\r\n    \"endSeedId\": \""+endSeedId+"\"\r\n}");
	Request request = new Request.Builder()
	  .url("https://f24h2018.herokuapp.com/api/tracks")
	  .post(body)
	  .addHeader("Content-Type", "application/json")
	  .addHeader("Authorization", "Bearer "+id)
	  .addHeader("Cache-Control", "no-cache")
	  .addHeader("Postman-Token", "5bfef78a-56f7-1c3e-db5e-80001db0570f")
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
 * Méthode qui retour nos Analyses
 * @param id
 * @return
 */
public Response getAnalyse(String id) {
	OkHttpClient client = new OkHttpClient();
	Request request = new Request.Builder()
	  .url("https://f24h2018.herokuapp.com/api/analyses/me")
	  .get()
	  .addHeader("Authorization", "Bearer "+id)
	  .addHeader("Cache-Control", "no-cache")
	  .addHeader("Postman-Token", "7bbd9cc9-3719-7590-5be4-fe15fc63785f")
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
 * Méthode qui ferme une track pour la valider et la soumettre au serveur
 * @param nomTrack
 * @param info
 * @param idTrack
 * @param id
 * @return
 */
public Response endTrack(String nomTrack, String info,String idTrack, String id) {
	OkHttpClient client = new OkHttpClient();
	MediaType mediaType = MediaType.parse("application/json");
	RequestBody body = RequestBody.create(mediaType, "{\r\n    \"name\": \""+nomTrack+"\",\r\n    \"info\": \""+info+"\"\r\n}");

	Request request = new Request.Builder()
	  .url("https://f24h2018.herokuapp.com/api/tracks/"+idTrack+"/end")
	  .put(body)
	  .addHeader("Content-Type", "application/json")
	  .addHeader("Authorization", "Bearer "+id)
	  .addHeader("Cache-Control", "no-cache")
	  .addHeader("Postman-Token", "380a399c-44cc-b78d-4ae0-00a94d42e196")
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
 * Méthode qui change la position de la fourmie une foit qu'elle a rejoind une graine
 * @param id
 * @param idFourmie
 * @param seedId
 * @return
 */
public Response changeAntPosition(String id, String idFourmie, String seedId) {
	OkHttpClient client = new OkHttpClient();

	MediaType mediaType = MediaType.parse("application/json");
	RequestBody body = RequestBody.create(mediaType, "{\r\n    \"seedId\": \""+seedId+"\"\r\n}");
	Request request = new Request.Builder()
	  .url("https://f24h2018.herokuapp.com/api/users/"+idFourmie+"/position")
	  .put(body)
	  .addHeader("Content-Type", "application/json")
	  .addHeader("Authorization", "Bearer "+id)
	  .addHeader("Cache-Control", "no-cache")
	  .addHeader("Postman-Token", "282def43-e711-2e4c-e69a-241fa1c3cb99")
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
 * Méthode qui permet à la cygale de pouvoir signaler une vitesse excessive à telle position d'une track 
 * @param token
 * @param trackid
 * @param positionid
 * @param vitesseAutorisee
 * @param vitesseRoulee
 * @return
 */
public Response AnalyseVitesseExesive(String token,String trackid,String positionid,double vitesseAutorisee,double vitesseRoulee) {
	OkHttpClient client = new OkHttpClient();

	MediaType mediaType = MediaType.parse("application/json");
	RequestBody body = RequestBody.create(mediaType, "{\r\n    \"trackId\": "+trackid+",\r\n    \"positionId\": "+positionid+",\r\n    \"description\": \"dépassement de vitesse  : "+vitesseRoulee+"  > "+vitesseAutorisee+"\"\r\n}");
	Request request = new Request.Builder()
	  .url("https://f24h2018.herokuapp.com/api/analyses")
	  .post(body)
	  .addHeader("Content-Type", "application/json")
	  .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1YTYzMmFhMmYzNmQyODcwODdhMTk4NzgiLCJyb2xlIjoiY2ljYWRhIiwiaWF0IjoxNTE2NTA2NDM2LCJleHAiOjE1MTY1MjQ0MzZ9.deFY9URgTOCKBUrhFuKS0qKu3RwzXNmJZhu-hLzS61k")
	  .addHeader("Cache-Control", "no-cache")
	  .addHeader("Postman-Token", "5abddaca-719a-bb7e-2fd3-63524f1e39e4")
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
 * Méthode qui permet à la cygale de pouvoir signaler un refut de priorité à telle position d'une track 
 * @param token
 * @param trackid
 * @param positionid
 * @return
 */
public Response AnalyseArretNonRespecter(String token,String trackid,String positionid) {
	OkHttpClient client = new OkHttpClient();

	MediaType mediaType = MediaType.parse("application/json");
	RequestBody body = RequestBody.create(mediaType, "{\r\n    \"trackId\": "+trackid+",\r\n    \"positionId\": "+positionid+",\r\n    \\\"description\\\": \\\"Vous ne vous etes pas arrete a un stop ou a un feu\\\"\\r\\n}\"");
	Request request = new Request.Builder()
	  .url("https://f24h2018.herokuapp.com/api/analyses")
	  .post(body)
	  .addHeader("Content-Type", "application/json")
	  .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1YTYzMmFhMmYzNmQyODcwODdhMTk4NzgiLCJyb2xlIjoiY2ljYWRhIiwiaWF0IjoxNTE2NTA2NDM2LCJleHAiOjE1MTY1MjQ0MzZ9.deFY9URgTOCKBUrhFuKS0qKu3RwzXNmJZhu-hLzS61k")
	  .addHeader("Cache-Control", "no-cache")
	  .addHeader("Postman-Token", "5abddaca-719a-bb7e-2fd3-63524f1e39e4")
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
 * Méthode qui permet à une formie d'aller chercher une graine 
 */
public void EnvoyerTrackFourmieComplete() {
	ComunicationServeur com=new ComunicationServeur();
	String token=null;
	String idTrack=null;
	String idPosition=null;
	/*Maison*/
	Position p1 = new Position(47.984393, 0.236012);
	/*Première graine*/
	Position p2 = new Position(47.984946, 0.238951);
	Fourmi ant1= new Fourmi();
	Chemin c = new Chemin();
	try {
		c.calculItineraire(p1, p2);
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	try {
		token=com.Auth("ant1@mill.ant", "Vent").body().string().split(":\"")[1].split("\"}")[0];

	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
	}

	com.createTrack("Track test", token, "5a5e71a2734d1d347185192c", "5a5e7207734d1d347185195c");
	try {
		idTrack=com.createTrack("Track test", token, "5a5e71a2734d1d347185192c", "5a5e7207734d1d347185195c").body().string().split("_id\":\"")[1].split("\",\"")[0];
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	com.CreatePositions(token,idTrack,ant1.creerTrack(c).toString());
	System.out.println("azertuopzertyui"+ant1.creerTrack(c).toString());
	System.out.println("test creer position: "+com.CreatePositions(token,idTrack,ant1.creerTrack(c).toString()).isSuccessful());
	com.endTrack("petite balade","vers l'infini et ...", idTrack, token);
	System.out.println("test end track: "+com.endTrack("petite balade","vers l'infini et ...",idTrack, token).isSuccessful());
	try {
		System.out.println(com.showAllPositionsTrack(token, "5a63e834df71750014294e8c").body().string());
		System.out.println(com.showAllPositionsTrack(token, "5a63e834df71750014294e8c").isSuccessful());

	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	;
	try {
		System.out.println("liste de mes tracks:" +com.ListMyTracks(token).body().string());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	com.changeAntPosition(token, idAnt1,"5a5e7207734d1d347185195c" );
	System.out.println("change ant position:"+com.changeAntPosition(token, idAnt1,"5a5e7207734d1d347185195c" ).isSuccessful());

}


/**
 * Main servant à tester la classe au fur et à mesure
 * @param args
 */

public static void main(String [] args) {	
	
	ComunicationServeur com=new ComunicationServeur();
	com.EnvoyerTrackFourmieComplete();
	/*
	String token=null;
	String idTrack=null;
	String idPosition=null;
	
	Cigale cigale=new Cigale();
	
	try {
		cigale.parsePosition();
	} catch (IOException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
	idPosition=cigale.track.get(2).id;
	
	
	try {
		token=com.Auth("cicada@mill.ant", "Vent").body().string().split(":\"")[1].split("\"}")[0];
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	try {
		idTrack=com.ListOtherTeamsTracks(token).body().string().split(":\"")[1].split("\",")[0];
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

	System.out.println("get analyse: "+com.getAnalyse(token).isSuccessful());
	System.out.println("creation d'une track: "+com.createTrack("Track test", token, "5a5e71a2734d1d347185192c", "5a5e7207734d1d347185195c").isSuccessful());
	//System.out.println("end d'une track: "+com.endTrack("Track test", token).isSuccessful());
	System.out.println("show all position d'une track: "+com.showAllPositionsTrack(token, idTrack).isSuccessful());
	//System.out.println("create position: "+com.CreatePositions(token, idTrack).isSuccessful());
	System.out.println("create analyse: "+com.createAnalyse(token, idTrack).isSuccessful());
	System.out.println("show une position:"+com.showPosition(token, idPosition).isSuccessful());
	//System.out.println(com.describeSeed(token).isSuccessful());
	System.out.println(com.searchSeedAround(token).isSuccessful());
	System.out.println(com.ListMyTracks(token).isSuccessful());
	System.out.println(com.ListOtherTeamsTracks(token).isSuccessful());
	try {
		System.out.println("get analyse: "+com.getAnalyse(token).body().string());
		System.out.println("creation d'une track: "+com.createTrack("Track test", token, "5a5e71a2734d1d347185192c", "5a5e7207734d1d347185195c").body().string());
		//System.out.println("end d'une track: "+com.endTrack("Track test", token).body().string());
		System.out.println("show position:"+com.showPosition(token, idPosition).body().string());
		//System.out.println("create position:"+com.CreatePositions(token, idTrack).body().string());
		System.out.println("create analyse: "+com.createAnalyse(token, idTrack).body().string());
		System.out.println("show all position d'une track: "+com.showAllPositionsTrack(token, idTrack).body().string());
		//System.out.println("describe seed:" +com.describeSeed(token).body().string());
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
	
	try {
		token=com.Auth("ant1@mill.ant", "Vent").body().string().split(":\"")[1].split("\"}")[0];
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	System.out.println(com.GetMyUserInfo(token).isSuccessful());
	try {
		System.out.println(com.createTrack("Track test", token, "5a5e71a2734d1d347185192c", "5a5e7207734d1d347185195c").body().string());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
	
	
}




}
