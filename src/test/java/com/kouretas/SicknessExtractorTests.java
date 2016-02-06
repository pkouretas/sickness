package com.kouretas;


import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

public class SicknessExtractorTests {
	
	@Test
	public void compareExtracted() throws IOException {
	
		// CREATE SAMPLE OF SENTENCES
		String sentence1 = "I have a sore THROAT";
		String sentence2 = "My spondylOlisthesis hurts a lot";
		String description = "\"" + sentence1 + ". " + sentence2 + "\"";
		String[] expectedResults = new String[]{"sore throat", "spondylolisthesis"};
		
		SicknessMap map = SicknessMap.getInstance();
		map.build();
		
		List<String> results = new SicknessExtractor().extractIllnesses(description);
		
		assertEquals(expectedResults.length, results.size());
		for (int i = 0; i < expectedResults.length; i++){
			assertEquals(expectedResults[i], results.get(i));
		}
		
	}
}