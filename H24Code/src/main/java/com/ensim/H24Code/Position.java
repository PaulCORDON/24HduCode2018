package com.ensim.H24Code;

import java.io.IOException;
import java.time.Instant;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Position {
	
	double lat;
	double lon;
	Instant timestamp;
	String id ; 
	
	Position(double la, double lo){
		lat=la;
		lon=lo;
		timestamp= Instant.now();
	}
	Position(double la, double lo, String i){
		lat=la;
		lon=lo;
		timestamp=Instant.now();
		id =i;
	}
	
	Position(){
		timestamp=Instant.now();
	}
	
	double getLat() {
		return lat;
	}
	
	double getLon() {
		return lon;
	}
	
	void setTimestamp() {
		timestamp=Instant.now();
		System.out.println(timestamp);
//		timestamp.set(Calendar.YEAR, t.get(Calendar.YEAR));
//		timestamp.set(Calendar.MONTH, t.get(Calendar.MONTH));
//		timestamp.set(Calendar.DAY_OF_MONTH, t.get(Calendar.DAY_OF_MONTH));
//		timestamp.set(Calendar.HOUR_OF_DAY, t.get(Calendar.HOUR_OF_DAY));
//		timestamp.set(Calendar.MINUTE, t.get(Calendar.MINUTE));
//		timestamp.set(Calendar.SECOND, t.get(Calendar.SECOND));
//		timestamp.set(Calendar.MILLISECOND, t.get(Calendar.MILLISECOND));
	}
	
	Instant getTimestamp() {
		return timestamp;
	}
	
	/*Donne la longueur en m entre deux positions */
	double longueurEnM(Position p1, Position p2) {
		double deltaLatKm = (p1.lat-p2.lat)*110.574;
		double deltaLonKm = (p1.lon-p2.lon)*111.320*Math.cos((p1.lat)*((Math.PI)/180));
		
		return Math.sqrt(Math.pow(deltaLatKm, 2)+Math.pow(deltaLonKm, 2))*1000;	
	}
	
	/*Donne le point après un parcours depuis p1 vers p2 de 1s à vitesse "vitesse"*/
	Position prochainPointEnUneSeconde(Position p2, double vitesse) {
		Position p1 = new Position();
		if((p2.lat-this.lat)>0 && (this.lon-p2.lon)>0) {
			p1.lat = (vitesse*(p2.lat-this.lat)/this.longueurEnM(this, p2))+this.lat;
			p1.lon = (-vitesse*(this.lon-p2.lon)/this.longueurEnM(this, p2))+this.lon;
		}
		if((p2.lat-this.lat)<0 && (this.lon-p2.lon)>0) {
			p1.lat = (-vitesse*(this.lat-p2.lat)/this.longueurEnM(this, p2))+this.lat;
			p1.lon = (-vitesse*(this.lon-p2.lon)/this.longueurEnM(this, p2))+this.lon;
		}
		if((p2.lat-this.lat)>0 && (this.lon-p2.lon)<0) {
			p1.lat = (vitesse*(p2.lat-this.lat)/this.longueurEnM(this, p2))+this.lat;
			p1.lon = (vitesse*(p2.lon-this.lon)/this.longueurEnM(this, p2))+this.lon;
		}
		if((p2.lat-this.lat)<0 && (this.lon-p2.lon)<0) {
			p1.lat = (-vitesse*(this.lat-p2.lat)/this.longueurEnM(this, p2))+this.lat;
			p1.lon = (vitesse*(p2.lon-this.lon)/this.longueurEnM(this, p2))+this.lon;
		}
		
		return p1;
	}
	public String toString () {
		
		return "{\"lat\" : "+ lat + " ,\"lon\" : "+ lon + ", \"timestamp\" : \"" + this.timestamp + "\"}\n"; 
		
	}
	
	public static void main(String[] args) {
		/*Donnees test
		 * Position p1 = new Position(47.984482199999995,0.23617069999999998);
		 * Position p2 = new Position(47.984667800000004,0.23652710000000002);
		 */
		Position p1 = new Position();
		Position p2 = new Position();
		Position p3;
		
		Chemin cheminFourmi = new Chemin();
		
		Chemin c = new Chemin();
		try {
			c.calculItineraire(p1,p2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cheminFourmi.add(c.get(0));
		for(int i=0;i<c.size()-1;i++) {
			
			
			System.out.println("p"+i+" : "+c.get(i).lat+","+c.get(i).lon);
			System.out.println("p"+(i+1)+" : "+c.get(i+1).lat+","+c.get(i+1).lon);
			System.out.println("La distance entre ces deux points est de "+p1.longueurEnM(c.get(i), c.get(i+1))+"m");
			if(p1.longueurEnM(c.get(i), c.get(i+1))>13.88) {
				p3=c.get(i).prochainPointEnUneSeconde(c.get(i+1), 13.87);
				//System.out.println("Le prochain point atteint en 1s est le point P : "+p3.lat+","+p3.lon);
				cheminFourmi.add(p3);
				System.out.println("Le point p :"+p3.lat+","+p3.lon+" a ete ajoute a la liste");
				c.set(i+1, p3);
			}
			else {
				cheminFourmi.add(c.get(i+1));
				System.out.println("Le point p :"+c.get(i+1).lat+","+c.get(i+1).lon+" a ete ajoute a la liste");
			}
			
		}

		for(int i=0;i<cheminFourmi.size()-1;i++) {
			System.out.println("p"+i+" : "+cheminFourmi.get(i).lat+","+cheminFourmi.get(i).lon);
			System.out.println("La distance entre ces deux points est de "+p1.longueurEnM(cheminFourmi.get(i),cheminFourmi.get(i+1))+"m");
			
			
		}
	
		

	}


}
