package com.ensim.H24Code;

import java.util.GregorianCalendar;

public class Position {
	
	double lat;
	double lon;
	GregorianCalendar temps;
	
	Position(double la, double lo, GregorianCalendar t){
		lat=la;
		lon=lo;
		temps=t;
	}
	
	double longueurEnKm(Position p1, Position p2) {
		double deltaLatKm = (p1.lat-p2.lat)*110.574;
		double deltaLonKm = (p1.lon-p2.lon)*111.320*Math.cos(p1.lat-p2.lat);
		
		return Math.sqrt(Math.pow(deltaLatKm, 2)+Math.pow(deltaLonKm, 2));	
	}

}
