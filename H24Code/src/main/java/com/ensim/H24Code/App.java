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
		try {
			token1=com.Auth("ant1@mill.ant", "Vent").body().string().split(":\"")[1].split("\"}")[0];
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		
		try {
			System.out.println("search seed around: " +com.searchSeedAround(token1).body().string());
			System.out.println("search seed around: " +com.searchSeedAround(token2).body().string());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


        
        
    }
}
