package com.ensim.H24Code;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;



public class graines {
	ArrayList<graine> LesGrainesDeLaCarte=new ArrayList<graine>();
	public graines(String token) {
		ComunicationServeur com=new ComunicationServeur();
		try {
			String s =com.searchSeedAround(token).body().string();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			
		
	}
}
