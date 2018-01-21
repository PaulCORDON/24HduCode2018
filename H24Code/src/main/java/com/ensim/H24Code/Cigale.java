package com.ensim.H24Code;

import java.io.IOException;
import java.util.ArrayList;


import org.json.JSONArray;
import org.json.JSONObject;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class Cigale {
	ArrayList<Position> track = new ArrayList<Position>();
	ArrayList<String> idTrack = new ArrayList<String>();
	/* FONCTION POUR DETECTER LES ERREURS DU CODE DE LA ROUTE */
	boolean estEnDehorsDeRoute() {
		return false;
	}
	
	boolean estSurRouteNonAutorise() {
		return false;
	}
	
	boolean vitesseHorsLimite() throws IOException {
		double distanceEntreDeuxPts;
		double v ; 
		String idTrack;
		String idPos;
		ComunicationServeur  serveur = new ComunicationServeur();
		for (Position p : track) {
			
			distanceEntreDeuxPts = p.longueurEnM(p, track.get(track.indexOf(p)+1));
			if (distanceEntreDeuxPts > 13.88 ) {
				System.out.println("LA FOURMI VA A PLUS DE 50KM/H !!!!!!! a la position : "+ p.getLat() + "\n" +  p.getLon() + "la vitesse est de : " + distanceEntreDeuxPts*3.6) ;
				v = distanceEntreDeuxPts*3.6;
				idTrack = track.get(0).id;
				idPos = p.id;
				boolean ok = serveur.AnalyseVitesseExesive(serveur.Auth("ant1@mill.ant", "Vent").body().string().split(":\"")[1].split("\"}")[0], "5a6350688fb12f001481b340", idPos, 50, v).isSuccessful();
				System.out.println(ok);
				return false;
			}
		}
		
		return true;
	}
	
	boolean nonRespectFeuxOuStop() {
		return false;
	}
	
	boolean refusPriorite() {
		return false;
	}
	/**
	 * 
	 *  parse les tracks des adversaires
	 * @throws IOException
	 */
	public void parseTrack () throws IOException {

		ComunicationServeur serveur = new ComunicationServeur();
		
		Response tracksAdverse = serveur.ListOtherTeamsTracks(serveur.Auth("ant1@mill.ant", "Vent").body().string().split(":\"")[1].split("\"}")[0]);
	//	System.out.println(tracksAdverse.body().string());

		String jsonDoc = tracksAdverse.body().string();
		System.out.println(jsonDoc);
	
	
		JSONArray jsonarray = new JSONArray(jsonDoc);
		
		
		for (int i = 0; i < jsonarray.length(); i++) {
		    JSONObject jsonobject = jsonarray.getJSONObject(i);
		    String id = jsonobject.getString("_id");
		   System.out.println(id);
		}
	
	
	}
	
	/**
	 * 
	 * parse les positions d'une tracks donnée 
	 * @throws IOException
	 */
	public void parsePosition () throws IOException {
		ComunicationServeur serveur = new ComunicationServeur();
		Response reponse = serveur.showAllPositionsTrack(serveur.Auth("ant1@mill.ant", "Vent").body().string().split(":\"")[1].split("\"}")[0], "5a6350688fb12f001481b340");
		//System.out.println(reponse.body().string());
		String jsonDoc = reponse.body().string();
		String id;
		double lat;
		double lon;
		String time; 
		
		JSONArray jsonarray = new JSONArray(jsonDoc);
		System.out.println(jsonDoc);
		for (int i = 0; i < jsonarray.length(); i++) {
		    JSONObject jsonobject = jsonarray.getJSONObject(i);
		     id = jsonobject.getString("_id");
		     lon = jsonobject.getDouble("lon");
		     lat = jsonobject.getDouble("lat");
		     time = jsonobject.getString("timestamp");
		   
		 /*  System.out.println(id);
		   System.out.println(lon);
		   System.out.println(lat);
		   System.out.println(time);*/
		   track.add(new Position(lat,lon,id));
		}
		
	}

	
	
	/**
	 * 
	 * ajout des contraites du code de la route au différentes position du parcours
	 * @throws IOException
	 */
	public void ajoutContraintes () throws IOException {
		
		for (Position pos : track) {
			OkHttpClient client = new OkHttpClient();

			Request request = new Request.Builder()
			  .url("http://overpass-api.de/api/interpreter?data=[out:json][timeout:25];%0A//%20gather%20results%0A%28%0A%20%20//%20query%20part%20for:%20%E2%80%9Cstop%E2%80%9D%0A%20%20node[%22highway%22=%22stop%22]%28around:1.0,"+pos.lat+"," +pos.lon+"%29;%0A%20%20node[%22highway%22=%22traffic_signals%22]%28around:500.0,47.984743,0.240155%29;%0A%29;%0A//%20print%20results%0Aout%20body;%0A%3E;%0Aout%20skel%20qt;")
			  .get()
			  .addHeader("Cache-Control", "no-cache")
			  .addHeader("Postman-Token", "9351032e-7310-1e98-01f0-73f670099a44")
			  .build();

			Response response = client.newCall(request).execute();

			System.out.println(response.body().string());
			
		}
		
		
	}
/**
 * 
 * main pour tester les fonctions
 * @param args
 * @throws IOException
 */
	public static void main(String[] args) throws IOException {
		Cigale cigale = new Cigale ();
	
		cigale.parseTrack();
		cigale.parsePosition();
	//	System.out.println(cigale.track);
		//cigale.ajoutContraintes();
		cigale.vitesseHorsLimite();
	}

}
