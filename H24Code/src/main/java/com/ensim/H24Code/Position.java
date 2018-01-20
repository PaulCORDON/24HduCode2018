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
	
	double longueurEnKm(Position p1, Position p2) {
		double deltaLatKm = (p1.lat-p2.lat)*110.574;
		double deltaLonKm = (p1.lon-p2.lon)*111.320*Math.cos((p1.lat)*((Math.PI)/180));
		
		
		return Math.sqrt(Math.pow(deltaLatKm, 2)+Math.pow(deltaLonKm, 2));	
	}
	public String toString () {
		
		return "latitude = "+ lat + " longitude = "+ lon ; 
		
	}
	
	public static void main(String[] args) {
		Position p1 = new Position(47.9829081,0.2331228);
		Position p2 = new Position(47.983123,0.2335649);
		
		System.out.println(p1.longueurEnKm(p1, p2));

	}


}
