package com.kouretas;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class SicknessMapBuilder {
	private static Path DEFAULT_PATH_TOP_LEVEL = Paths.get("phrases");
	private static Path DEFAULT_PATH_INSIDE_FOLDER = Paths.get("assets/phrases");

	public static Map<String, String> build() throws IOException {
		Map<String, String> sicknessMap = new HashMap<>();
		
		Stream<String> stream = null;
		if (Files.exists(DEFAULT_PATH_INSIDE_FOLDER, LinkOption.NOFOLLOW_LINKS)){
			// this is where the file is located in the project structure... 
			stream = Files.lines(DEFAULT_PATH_INSIDE_FOLDER);
		} else if (Files.exists(DEFAULT_PATH_TOP_LEVEL, LinkOption.NOFOLLOW_LINKS)){
			// ...however when we create the fat-jar file, somehow the assets are moved
			// to the root of the project
			// http://stackoverflow.com/questions/28638033/class-getresource-failed-from-fat-jar-packed-by-maven-shade-plugin
			// http://stackoverflow.com/questions/30386397/cant-load-resources-when-running-the-code-from-an-executable-jar
			stream = Files.lines(DEFAULT_PATH_TOP_LEVEL);
		} else {
			throw new IOException();
		}
		
		stream.forEach(sickness -> sicknessMap.put(sickness.toLowerCase(), sickness) );
		stream.close();
		
		return sicknessMap;
	}
	
}
