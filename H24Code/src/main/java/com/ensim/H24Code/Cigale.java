package com.ensim.H24Code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.naming.CommunicationException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.squareup.okhttp.Response;

public class Cigale {
	ArrayList<Position> track = new ArrayList<Position>();
	ArrayList<String> idTrack = new ArrayList<String>();
	
	boolean estEnDehorsDeRoute() {
		return false;
	}
	
	boolean estSurRouteNonAutorise() {
		return false;
	}
	
	boolean vitesseHorsLimite() {
		return false;
	}
	
	boolean nonRespectFeuxOuStop() {
		return false;
	}
	
	boolean refusPriorite() {
		return false;
	}
	
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

	public static void main(String[] args) throws IOException {
		Cigale cigale = new Cigale ();
	
		cigale.parseTrack();
		cigale.parsePosition();
		System.out.println(cigale.track);
		
	}

}
