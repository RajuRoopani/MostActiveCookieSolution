package com.active.cookie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.active.cookie.service.CookieProcessorService;

/**
 * 
 * @author rroopani
 *
 */
public class ActiveCookieMain {

	/**
	 * Main method to execute this program
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 3) {
			printHelp();
			return;
		}
		String fileName = args[0];
		if( !args[1].equals("-d")) {
			printHelp();
			return;
		}
		File file = new File(fileName);
		CookieProcessorService cookieService = new CookieProcessorService();
		BufferedReader br;
		String line;
		try {
			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				if( line.startsWith("cookie,timestamp") ) {
					continue;
				}
				cookieService.processLine(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File not found:" + fileName);
			printHelp();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String localDate = args[2];
		List<String> resultList = cookieService.getTopCookies(localDate);
		for(String string: resultList) {
			System.out.println(string);
		}
		
	}

	public static void printHelp() {
		System.out.println("Invalid arguments passed. Help:\n");
		System.out.println(" arg1 = $csvFilePath, arg2= '-d' command, arg3 = timestamp in yyyy-mm-dd format");
	}
}
