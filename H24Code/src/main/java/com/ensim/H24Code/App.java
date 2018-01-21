package com.ensim.H24Code;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ComunicationServeur com=new ComunicationServeur();
    	
    	String seedIdDepart="5a5e71a2734d1d347185192c";
    	Position pDepart = new Position(47.9844782,0.2415538);
    	String seedId1="5a5e7207734d1d347185195c";
    	Position p1 = new Position(47.99026,0.23991);
    	String seedId2="5a5e7228734d1d3471851968";
    	Position p2 = new Position(47.9827689,0.2328369);
    	String seedId3="5a5e71e2734d1d3471851942";
    	Position p3 = new Position(47.987444,0.253475);
    	
    	Cigale cigale= new Cigale();
    	String tokenCigale=null;
    
    	
    	
    	try {
			tokenCigale=com.Auth("cicada@mill.ant", "Vent").body().string().split(":\"")[1].split("\"}")[0];
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
    	
    	Fourmi f1= new Fourmi();
    	String token1=null;
    	String idTrack1Aller=null;
    	String idTrack1Retour=null;
    	Chemin c1Aller=new Chemin();
    	Chemin c1Retour=new Chemin();
    	try {
    		c1Aller.calculItineraire(pDepart, p1);
    	} catch (IOException e1) {
    		// TODO Auto-generated catch block
    		e1.printStackTrace();
    	}
    	
		try {
			token1=com.Auth("ant1@mill.ant", "Vent").body().string().split(":\"")[1].split("\"}")[0];
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		com.createTrack("Track fourmi 1", token1, seedIdDepart, seedId1);
		try {
			idTrack1Aller=com.createTrack("Track fourmi 1", token1, seedIdDepart, seedId1).body().string().split("_id\":\"")[1].split("\",\"")[0];
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		com.CreatePositions(token1,idTrack1Aller,f1.creerTrack(c1Aller).toString());
		System.out.println("Track 1: "+f1.creerTrack(c1Aller).toString());
		com.endTrack("Track fourmi 1","vers l'infini et ...", idTrack1Aller, token1);
		System.out.println("test end track: "+com.endTrack("Track fourmi 1","vers l'infini et ...",idTrack1Aller, token1).isSuccessful());


		
		/*
		
		Fourmi f2= new Fourmi();
    	String token2=null;
		try {
			token2=com.Auth("ant2@mill.ant", "Vent").body().string().split(":\"")[1].split("\"}")[0];
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Fourmi f3= new Fourmi();
    	String token3=null;
		try {
			token3=com.Auth("an31@mill.ant", "Vent").body().string().split(":\"")[1].split("\"}")[0];
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		


        */
        
    }
}
