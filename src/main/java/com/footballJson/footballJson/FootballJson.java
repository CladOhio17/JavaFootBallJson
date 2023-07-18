package com.footballJson.footballJson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 
 * @author Nate Yach
 * @since July 18, 2023
 */
public class FootballJson 
{
    public static void main( String[] args )
    {
    	List<String> keyList = Arrays.asList("manutd", "crystalpalace", "arsenal", "liverpool");
    	JsonObject jo = getJsonObjet();
    	
    	for(int i = 0; i < keyList.size();i++) {
    		
    		System.out.println("The 2014/15 Season Score for "+keyList.get(i)+" is: "+getScore(keyList.get(i), jo));
    	}
        
    }
    /**
     * Gets the score of the given team key
     * @param teamKey the key used to identify a team
     * @param jo the json object which the data is retrieved from
     * @return the score of the given team key
     */
    public static int getScore(String teamKey, JsonObject jo) {
		
		int score = 0;
		
			try{

				JsonArray rounds = (JsonArray) jo.get("rounds");
				for(int i = 0; i<rounds.size();i++) {
					JsonObject round =  (JsonObject) rounds.get(i);
					JsonArray matches = (JsonArray) round.get("matches");

					for(int j = 0; j< matches.size();j++) {
						
						JsonObject match =  (JsonObject) matches.get(j);
						JsonObject team1 = (JsonObject) match.get("team1");
						JsonObject team2 = (JsonObject) match.get("team2");

						if(team1.get("key").toString().replaceAll("^\"|\"$","").equals(teamKey)) {
							score += Integer.parseInt(match.get("score1").toString());
							
						}else if(team2.get("key").toString().replaceAll("^\"|\"$","").equals(teamKey)) {
							score += Integer.parseInt(match.get("score2").toString());
						}
					}
				}
				
			}catch(Exception e) {
				System.out.print(e);
			}
			

			return score;
		
	}
    /**
     * Gets a json object from a static url
     * @return json object from static url
     */
    public static JsonObject getJsonObjet() {
    	
    	String URL = "https://s3.eu-west-1.amazonaws.com/hackajob-assets1.p.hackajob/challenges/football_session/football.json";
    	
    	try{
    		
			InputStream input = new URL(URL).openStream();
			InputStreamReader stream = new InputStreamReader(input);
        	BufferedReader reader = new BufferedReader(stream);
        	StringBuilder jsonText = new StringBuilder();
        	
        	int c;
			
			while((c = reader.read()) != -1){
				jsonText.append((char) c);
			}
			return JsonParser.parseString(jsonText.toString()).getAsJsonObject();
			
    	}catch(Exception e) {
			System.out.print(e);
		}
			
		return null;
    	
    }
}
