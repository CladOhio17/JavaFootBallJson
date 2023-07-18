package com.footballJson.footballJson;



import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


/**
 * simple unit test for FootballJson.
 */
public class FootballJsonTest{ 
	
	@Test
	void footballTest() throws InterruptedException {
		assertEquals(62,FootballJson.getScore("manutd", FootballJson.getJsonObjet()));
		
		return;
	}
}
