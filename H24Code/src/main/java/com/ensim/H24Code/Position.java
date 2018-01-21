package com.ensim.H24Code;

import java.time.Instant;

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
	
	void setTimestamp(Instant t) {
		timestamp=t.plusSeconds(1);
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
	
}
