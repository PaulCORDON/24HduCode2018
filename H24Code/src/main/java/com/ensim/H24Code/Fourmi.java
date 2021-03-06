package com.ensim.H24Code;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.Temporal;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Fourmi {
	Instant t =  Instant.now();
	double vitesse;
	boolean veutFreiner;
	static double acceleration=1.38;
	
	Fourmi(){
		vitesse=0;
		veutFreiner=false;
	}
	
	/*Creer une liste de Position (Chemin) décrivant l'itinéraire de la fourmi à partir de l'itinéraire récuperer sur googlemap*/
	Chemin creerTrack(Chemin c) {
	    Chemin track = new Chemin();
	    Position p = new Position();
	    
	    /*On ajoute le premier point dans notre liste et on set le timestamp au temps actuel*/
	    track.add(c.get(0));
	    track.get(0).setTimestamp(t);

	    /*On parcours l'itinéraire googlemap*/
	    for(int i=0;i<c.size()-1;i++) {
	      
	    	/*On incrémente le timestamp de 1 seconde*/	
	    	t=t.plusMillis(1000);
		    
		    /*Si la vitesse est inférieure à la vitesse max et qu'on ne veut pas freiner, on accélère*/
		    if(this.vitesse<13.7 && !veutFreiner) {
		    	this.setVitesse(this.getVitesse()+acceleration);
		      }
		    
		    /*Freinage en anticipant l'arrêt*/
			if(i>=c.size()-(this.getVitesse()/acceleration)) {
			    veutFreiner=true;
			    this.setVitesse(this.getVitesse()-acceleration);
			  }
			
			/*si la longueur entre les points de googlemap est plus grande que la distance que l'on
			 * peut parcourir à notre vitesse, alors on recalcule un nouveau point plus proche.
			 */
			if(p.longueurEnM(c.get(i), c.get(i+1))>this.vitesse*1) {
				p=c.get(i).prochainPointEnUneSeconde(c.get(i+1), this.vitesse);  
				p.setTimestamp(t);
				track.add(p);
				/*On ajoute ce nouveau point à la liste google map pour garder une cohérence dans
				 * le parcours.
				 */
				c.add(i+1, p);
		      
			}
			else {
				/*Si on dépasse, alors on prend le point google map que l'on vient de dépasser
				 * pour l'ajouter dans notre liste
				 */
				p=c.get(i+1);
				p.setTimestamp(t);
				track.add(p);
			}
			    
		}
	    /*à la fin, on ajoute le dernier point google map
	     * on réinitalise la vitesse et le booléen
	     */
	    p=c.get(c.size()-1);
		p.setTimestamp(t);
		track.add(p);
		veutFreiner=false;
		this.setVitesse(0);
		return c;
		  
		}
	
	void setVitesse(double v) {
		vitesse=v;
	}
	
	double getVitesse() {
		return vitesse;
	}
	
	public static void main(String[] args) {
		/*Donnees test
		 * Position p1 = new Position(47.984482199999995,0.23617069999999998);
		 * Position p2 = new Position(47.984667800000004,0.23652710000000002);
		 */
		
		/*Maison*/
		Position p2 = new Position(47.984393, 0.236012);
		/*graine*/
		Position p1 = new Position(47.987444,0.253475);
		
		Fourmi fourmi = new Fourmi();
		Chemin trackAllerGraine1;
		Chemin trackRetourGraine1;
		
		Chemin c = new Chemin();
		Chemin c1 = new Chemin();
		try {
			c.calculItineraire(p2,p1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.add(p1);
		trackAllerGraine1 = fourmi.creerTrack(c);
	
		

		for(int i=0;i<trackAllerGraine1.size()-1;i++) {
			System.out.println("\np"+i+" : "+trackAllerGraine1.get(i).lat+","+trackAllerGraine1.get(i).lon);
			System.out.println(trackAllerGraine1.get(i).getTimestamp());
			System.out.println("La distance entre ces deux points est de "+p1.longueurEnM(trackAllerGraine1.get(i),trackAllerGraine1.get(i+1))+"m");
				
		}
		
		System.out.println("p"+(trackAllerGraine1.size()-1)+" : "+trackAllerGraine1.get(trackAllerGraine1.size()-1).lat+","+trackAllerGraine1.get(trackAllerGraine1.size()-1).lon);
		System.out.println(trackAllerGraine1.get(trackAllerGraine1.size()-1).getTimestamp());
		
		try {
			c1.calculItineraire(p1, p2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		c1.add(p2);
		trackRetourGraine1=fourmi.creerTrack(c1);
		
		for(int i=0;i<trackRetourGraine1.size()-1;i++) {
			System.out.println("\np"+i+" : "+trackRetourGraine1.get(i).lat+","+trackRetourGraine1.get(i).lon);
			System.out.println(trackRetourGraine1.get(i).getTimestamp());
			System.out.println("La distance entre ces deux points est de "+p1.longueurEnM(trackRetourGraine1.get(i),trackRetourGraine1.get(i+1))+"m");
				
		}
		
		System.out.println("p"+(trackRetourGraine1.size()-1)+" : "+trackRetourGraine1.get(trackRetourGraine1.size()-1).lat+","+trackRetourGraine1.get(trackRetourGraine1.size()-1).lon);
		System.out.println(trackRetourGraine1.get(trackRetourGraine1.size()-1).getTimestamp());
	}

	
}
