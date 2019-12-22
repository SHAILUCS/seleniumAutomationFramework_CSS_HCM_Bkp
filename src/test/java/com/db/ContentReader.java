package com.db;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.seleniumExceptionHandling.CustomExceptionHandler;

/**
 * This class has methods to read the content of any file and return string
 * object
 * 
 * @author shailendra Oct 25, 2019
 */
public class ContentReader {
	
	/**
	 * Returns the content of any file in a String
	 * 
	 * @param filePath the absolute path of the file
	 * @return File content in String  
	 * @author shailendra Oct 25, 2019
	 * */
	public static String readLineByLineJava8(String filePath) {
		StringBuilder contentBuilder = new StringBuilder();
		try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (IOException e) {
			new CustomExceptionHandler(e);
		}
		return contentBuilder.toString();
	}
}
