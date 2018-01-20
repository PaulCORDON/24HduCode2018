package com.ensim.H24Code;

import java.util.GregorianCalendar;

public class Position {
	
	double lat;
	double lon;
	GregorianCalendar temps;
	
	Position(double la, double lo){
		lat=la;
		lon=lo;
		//temps=t;
	}
	
	Position(){
		
	}
	
	/*Donne la longueur en m entre deux positions */
	double longueurEnM(Position p1, Position p2) {
		double deltaLatKm = (p1.lat-p2.lat)*110.574;
		double deltaLonKm = (p1.lon-p2.lon)*111.320*Math.cos((p1.lat)*((Math.PI)/180));
		
		return Math.sqrt(Math.pow(deltaLatKm, 2)+Math.pow(deltaLonKm, 2))*1000;	
	}
	
	Position prochainPointEnUneSeconde(Position p2, double vitesse) {
		Position p1 = new Position();
		p1.lat = (vitesse*Math.abs(p2.lat-this.lat)/this.longueurEnM(this, p2))+this.lat;
		p1.lon = (vitesse*Math.abs(this.lon-p2.lon)/this.longueurEnM(this, p2))+this.lon;
		
		return p1;
	}
	
	public static void main(String[] args) {
		/*Donnees test
		 * p1 : 47.984482199999995,0.23617069999999998
		 * p2 : 47.984667800000004,0.23652710000000002
		 */
		Position p1 = new Position(47.984482199999995,0.23617069999999998);
		Position p2 = new Position(47.984667800000004,0.23652710000000002);
		Position p3;
		
		
		System.out.println("p1 : 47.984482199999995,0.23617069999999998");
		System.out.println("p2 : 47.984667800000004,0.23652710000000002");
		System.out.println("La distance entre ces deux points est de "+p1.longueurEnM(p1, p2)+"m");
		
		System.out.println("En 1 seconde, on arrive au point :");
		p3=p1.prochainPointEnUneSeconde(p2, 13.79);
		
		System.out.println("p3 : "+p3.lat+","+p3.lon);
	
		System.out.println("p2 : 47.984667800000004,0.23652710000000002");
		System.out.println("La distance entre ces deux points est de "+p3.longueurEnM(p3, p2)+"m");
		
		System.out.println("En 1 seconde, on arrive au point :");
		p1=p3.prochainPointEnUneSeconde(p2, 13.79);
		
		System.out.println("p1 : "+p1.lat+","+p1.lon);
		System.out.println("p2 : 47.984667800000004,0.23652710000000002");
		System.out.println("La distance entre ces deux points est de "+p1.longueurEnM(p1, p2)+"m");
		

	}


}
