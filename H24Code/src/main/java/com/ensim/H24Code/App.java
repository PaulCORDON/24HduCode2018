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
    	/*on fait appel a un objet communicationServeur pour avoir acces aux requettes*/
    	ComunicationServeur com=new ComunicationServeur();
    	
    	/*pour la fourmilliere et les 3 graines on rentre leur id et leurs latitude/longintude */
    	String seedIdDepart="5a5e71a2734d1d347185192c";
    	Position pDepart = new Position(47.9844782,0.2415538)
    			;
    	String seedId1="5a5e7207734d1d347185195c";
    	Position p1 = new Position(47.99026,0.23991);
    	
    	String seedId2="5a5e7228734d1d3471851968";
    	Position p2 = new Position(47.9827689,0.2328369);
    	
    	String seedId3="5a5e71e2734d1d3471851942";
    	Position p3 = new Position(47.987444,0.253475);
    	
    	
    	/*on construit une cigale*/
    	Cigale cigale= new Cigale();
    	String tokenCigale=null;
    
    	
    	/*on connecte la cigale au serveur pour obtenir le token*/
    	try {
			tokenCigale=com.Auth("cicada@mill.ant", "Vent").body().string().split(":\"")[1].split("\"}")[0];
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
//    	
//    	
//    	/*on construit la fourmi1*/
//    	Fourmi f1= new Fourmi();
//    	String token1=null;
//    	String idTrack1Aller=null;
//    	String idTrack1Retour=null;
//    	Chemin c1Aller=new Chemin();
//    	Chemin c1Retour=new Chemin();
//    	
//    	/*chemin aller fourmi 1*/
//    	
//    	/*on calcul l'itineraire aller que l'on met dans un chemin*/
//    	try {
//    		c1Aller.calculItineraire(pDepart, p1);
//    	} catch (IOException e1) {
//    		// TODO Auto-generated catch block
//    		e1.printStackTrace();
//    	}
//    	
//    	/*on obtient le token du serveur*/
//		try {
//			token1=com.Auth("ant1@mill.ant", "Vent").body().string().split(":\"")[1].split("\"}")[0];
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		/*on crée notre track pour l'aller*/
//		com.createTrack("Track fourmi 1", token1, seedIdDepart, seedId1);
//		try {
//			idTrack1Aller=com.createTrack("Track fourmi 1", token1, seedIdDepart, seedId1).body().string().split("_id\":\"")[1].split("\",\"")[0];
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		/*on crée les positions qui seront dans la track*/
//		com.CreatePositions(token1,idTrack1Aller,f1.creerTrack(c1Aller).toString());
//		System.out.println("Track 1: "+f1.creerTrack(c1Aller).toString());
//		/*on termine la track*/
//		com.endTrack("Track fourmi 1","vers l'infini et ...", idTrack1Aller, token1);
//		System.out.println("test end track1 aller: "+com.endTrack("Track fourmi 1","vers l'infini et ...",idTrack1Aller, token1).isSuccessful());
//		
//		/*chemin retour fourmi 2*/
//		
//		/*on calcule l'itineraire du retour a la fourmiliere*/
//		try {
//    		c1Retour.calculItineraire(p1, pDepart);
//    	} catch (IOException e1) {
//    		// TODO Auto-generated catch block
//    		e1.printStackTrace();
//    	}
//    	
//		/*on crée la track*/
//		com.createTrack("Track fourmi 1 Retour", token1, seedId1, seedIdDepart);
//		try {
//			idTrack1Retour=com.createTrack("Track fourmi 1 Retour", token1, seedId1, seedIdDepart).body().string().split("_id\":\"")[1].split("\",\"")[0];
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		/*on crée les positions de la track*/
//		com.CreatePositions(token1,idTrack1Retour,f1.creerTrack(c1Retour).toString());
//		System.out.println("Track 1: "+f1.creerTrack(c1Retour).toString());
//		/*on finit la track*/
//		com.endTrack("Track fourmi 1 Retour","vers l'infini et ...", idTrack1Retour, token1);
//		System.out.println("test end track1 retour: "+com.endTrack("Track fourmi 1 Retour","vers l'infini et ...",idTrack1Retour, token1).isSuccessful());
//
//
//		
//		
//		
//		Fourmi f2= new Fourmi();
//    	String token2=null;
//    	String idTrack2Aller=null;
//    	String idTrack2Retour=null;
//    	Chemin c2Aller=new Chemin();
//    	Chemin c2Retour=new Chemin();
//    	
//    	/*chemin aller fourmi 2*/
//    	try {
//    		c2Aller.calculItineraire(pDepart, p2);
//    	} catch (IOException e1) {
//    		// TODO Auto-generated catch block
//    		e1.printStackTrace();
//    	}
//    	
//		try {
//			token2=com.Auth("ant2@mill.ant", "Vent").body().string().split(":\"")[1].split("\"}")[0];
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		com.createTrack("Track fourmi 2 Aller", token2, seedIdDepart, seedId2);
//		try {
//			idTrack2Aller=com.createTrack("Track fourmi 2 Aller", token2, seedIdDepart, seedId2).body().string().split("_id\":\"")[1].split("\",\"")[0];
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		com.CreatePositions(token2,idTrack2Aller,f2.creerTrack(c2Aller).toString());
//		System.out.println("Track 2 Aller: "+f2.creerTrack(c2Aller).toString());
//		com.endTrack("Track fourmi 2","Aller", idTrack2Aller, token2);
//		System.out.println("test end track2 aller: "+com.endTrack("Track fourmi 2","Aller",idTrack2Aller, token2).isSuccessful());
//		
//		/*chemin retour fourmi 2*/
//		try {
//    		c2Retour.calculItineraire(p2, pDepart);
//    	} catch (IOException e1) {
//    		// TODO Auto-generated catch block
//    		e1.printStackTrace();
//    	}
//    	
//		com.createTrack("Track fourmi 2 Retour", token2, seedId2, seedIdDepart);
//		try {
//			idTrack2Retour=com.createTrack("Track fourmi 1 Retour", token2, seedId2, seedIdDepart).body().string().split("_id\":\"")[1].split("\",\"")[0];
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		com.CreatePositions(token2,idTrack2Retour,f2.creerTrack(c2Retour).toString());
//		System.out.println("Track 2: "+f2.creerTrack(c2Retour).toString());
//		com.endTrack("Track fourmi 2 Retour","balade posey", idTrack2Retour, token2);
//		System.out.println("test end track2 retour: "+com.endTrack("Track fourmi 2 Retour","balade posey",idTrack2Retour, token2).isSuccessful());
//		
//		
//		
		
		Fourmi f3= new Fourmi();
    	String token3=null;
    	String idTrack3Aller=null;
    	String idTrack3Retour=null;
    	Chemin c3Aller=new Chemin();
    	Chemin c3Retour=new Chemin();
    	
    	/*chemin aller fourmi 3*/
    	try {
    		c3Aller.calculItineraire(pDepart, p3);
    	} catch (IOException e1) {
    		// TODO Auto-generated catch block
    		e1.printStackTrace();
    	}
    	
		try {
			token3=com.Auth("ant3@mill.ant", "Vent").body().string().split(":\"")[1].split("\"}")[0];
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		com.createTrack("Track qui est sensé marcher aller",token3, seedIdDepart, seedId3);
		try {
			idTrack3Aller=com.createTrack("Track qui est sensé marcher aller", token3, seedIdDepart, seedId3).body().string().split("_id\":\"")[1].split("\",\"")[0];
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		com.CreatePositions(token3,idTrack3Aller,f3.creerTrack(c3Aller).toString());
		System.out.println("Track 3 Aller: "+f3.creerTrack(c3Aller).toString());
		com.endTrack("Track qui est sensé marcher ","Aller", idTrack3Aller, token3);
		System.out.println("test end track3 aller: "+com.endTrack("Track qui est sensé marcher ","Aller",idTrack3Aller, token3).isSuccessful());
		
		/*chemin retour fourmi 3*/
		try {
    		c3Retour.calculItineraire(p3, pDepart);
    	} catch (IOException e1) {
    		// TODO Auto-generated catch block
    		e1.printStackTrace();
    	}
    	
		com.createTrack("Track fourmi 3 Retour les cigales vous dormez??", token3, seedId3, seedIdDepart);
		try {
			idTrack3Retour=com.createTrack("Track fourmi 3 Retour les cigales vous dormez??", token3, seedId3, seedIdDepart).body().string().split("_id\":\"")[1].split("\",\"")[0];
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		com.CreatePositions(token3,idTrack3Retour,f3.creerTrack(c3Retour).toString());
		System.out.println("Track 3: "+f3.creerTrack(c3Retour).toString());
		com.endTrack("Track fourmi 3 Retour","balade a poney", idTrack3Retour, token3);
		System.out.println("test end track3 retour: "+com.endTrack("Track fourmi 3 Retour","balade a poney",idTrack3Retour, token3).isSuccessful());
		


    }
}
