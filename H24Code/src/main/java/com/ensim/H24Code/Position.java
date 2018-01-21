package com.ensim.H24Code;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Position {
	
	double lat;
	double lon;
	GregorianCalendar timestamp;
	String id ; 
	
	Position(double la, double lo){
		lat=la;
		lon=lo;
		timestamp=new GregorianCalendar();
	}
	
	Position(double la, double lo, String i){
		lat=la;
		lon=lo;
		timestamp=new GregorianCalendar();
		id =i;
	}
	
	Position(){
		timestamp=new GregorianCalendar();
	}
	
	double getLat() {
		return lat;
	}
	
	double getLon() {
		return lon;
	}
	
	void setTimestamp(GregorianCalendar t) {
		timestamp=new GregorianCalendar();
		timestamp.set(Calendar.YEAR, t.get(Calendar.YEAR));
		timestamp.set(Calendar.MONTH, t.get(Calendar.MONTH));
		timestamp.set(Calendar.DAY_OF_MONTH, t.get(Calendar.DAY_OF_MONTH));
		timestamp.set(Calendar.HOUR_OF_DAY, t.get(Calendar.HOUR_OF_DAY));
		timestamp.set(Calendar.MINUTE, t.get(Calendar.MINUTE));
		timestamp.set(Calendar.SECOND, t.get(Calendar.SECOND));
		timestamp.set(Calendar.MILLISECOND, t.get(Calendar.MILLISECOND));
	}
	
	String getTimestamp() {
		return timestamp.get(Calendar.YEAR)+"-"+timestamp.get(Calendar.MONTH)+"-"+timestamp.get(Calendar.DAY_OF_MONTH)+"T"+timestamp.get(Calendar.HOUR_OF_DAY)+":"+timestamp.get(Calendar.MINUTE)+":"+timestamp.get(Calendar.SECOND)+"."+timestamp.get(Calendar.MILLISECOND)+"Z";
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
		
		return "{\"lat\" : "+ lat + " ,\"lon\" : "+ lon + ", \"timestamp\" : \"" + this.getTimestamp() + "\"}\n"; 
		
	}
	
}
