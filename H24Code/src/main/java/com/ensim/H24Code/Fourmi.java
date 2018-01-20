package com.ensim.H24Code;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Fourmi {
	
	double vitesse;
	boolean veutFreiner;
	static double acceleration=1.38;
	
	Fourmi(){
		vitesse=0;
		veutFreiner=false;
	}
	
	Chemin creerTrack(Chemin c) {
		Chemin trackAllerGraine1 = new Chemin();
		Position p = new Position();
		GregorianCalendar t =  new GregorianCalendar();
		
		trackAllerGraine1.add(c.get(0));
		trackAllerGraine1.get(0).setTimestamp(t);
		
		
		for(int i=0;i<c.size()-1;i++) {
			
			t.add(Calendar.SECOND,1);
			System.out.println(t.get(Calendar.YEAR)+"-"+t.get(Calendar.MONTH)+"-"+t.get(Calendar.DAY_OF_MONTH)+"T"+t.get(Calendar.HOUR_OF_DAY)+":"+t.get(Calendar.MINUTE)+":"+t.get(Calendar.SECOND)+"."+t.get(Calendar.MILLISECOND)+"Z");
			
			if(this.vitesse<13.7 && !veutFreiner) {
				this.setVitesse(this.getVitesse()+acceleration);
			}
			
			if(i<=c.size()-10) {
				veutFreiner=true;
				this.setVitesse(this.getVitesse()-acceleration);
			}
			
			System.out.println("p"+i+" : "+c.get(i).lat+","+c.get(i).lon);
			System.out.println("p"+(i+1)+" : "+c.get(i+1).lat+","+c.get(i+1).lon);
			System.out.println("La distance entre ces deux points est de "+p.longueurEnM(c.get(i), c.get(i+1))+"m");
			if(p.longueurEnM(c.get(i), c.get(i+1))>this.vitesse) {
				p=c.get(i).prochainPointEnUneSeconde(c.get(i+1), this.vitesse);	
				p.setTimestamp(t);
				trackAllerGraine1.add(p);
				System.out.println("Le point p :"+p.lat+","+p.lon+" a ete ajoute a la liste");
				c.set(i+1, p);
			}
			else {
				p=c.get(i+1);
				p.setTimestamp(t);
				trackAllerGraine1.add(p);
				System.out.println("Le point p :"+c.get(i+1).lat+","+c.get(i+1).lon+" a ete ajoute a la liste");
			}
			
		}
		
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
		Position p1 = new Position();
		Position p3;
		
		Fourmi fourmi = new Fourmi();
		Chemin trackAllerGraine1;
		
		Chemin c = new Chemin();
		try {
			c.calculItineraire();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		trackAllerGraine1 = fourmi.creerTrack(c);

		for(int i=0;i<trackAllerGraine1.size()-1;i++) {
			System.out.println("p"+i+" : "+trackAllerGraine1.get(i).lat+","+trackAllerGraine1.get(i).lon);
			System.out.println(trackAllerGraine1.get(i).getTimestamp());
			System.out.println("La distance entre ces deux points est de "+p1.longueurEnM(trackAllerGraine1.get(i),trackAllerGraine1.get(i+1))+"m");
				
		}
		

	}
	
}
