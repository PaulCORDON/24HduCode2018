package com.ensim.H24Code;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Class servant à parser le resultat de la methode searchSeedAround de la class CommunicationServeur pour remplir son ArrayList de graine
 * @author paul cordon
 *
 */
public class graines {
	ArrayList<graine> LesGrainesDeLaCarte=new ArrayList<graine>();
	public graines(String token) {
		ComunicationServeur com=new ComunicationServeur();
			String jsonDoc = null;
			try {
				jsonDoc = com.searchSeedAround(token).body().string();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			System.out.println(jsonDoc);		
		
			JSONArray jsonarray = new JSONArray(jsonDoc);
			for (int i = 0; i < jsonarray.length(); i++) {
			    JSONObject jsonobject = jsonarray.getJSONObject(i);
			    
			    String id=null;
			    if((id=jsonobject.getString("_id"))!= null) {
			    	this.LesGrainesDeLaCarte.add(new graine());
					LesGrainesDeLaCarte.get(LesGrainesDeLaCarte.size()-1).id=id;
			    }
				

			    
			   System.out.println(id);
			}
		
	}
}
