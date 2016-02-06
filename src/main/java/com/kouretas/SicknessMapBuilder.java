package com.kouretas;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class SicknessMapBuilder {

	public static Map<String, String> buildFrom(String relativePath) throws IOException {
		Map<String, String> sicknessMap = new HashMap<>();
		
		Stream<String> stream = Files.lines(Paths.get(relativePath));
		stream.forEach(sickness -> sicknessMap.put(sickness.toLowerCase(), sickness) );
		stream.close();
		
		return sicknessMap;
	}
	
}
