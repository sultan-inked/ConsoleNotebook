package tools;

import java.util.Scanner;

/**
 * This class facilitates console input and handles possible read errors.
 */
public class Cnsl {
//	Variables:
	private static final Scanner scan = new Scanner(System.in);
	
//	Methods:
	/**
	 * Performs a string read form the console using the Scanner object.
	 * Returns the entered string with the start and end spaces removed.
	 * If reading from the console fails, displays an error message.
	 * 
	 * @return - returns the entered string
	 */
	public static String scan() {
		String result = "";
		if(scan.hasNextLine()) {
			result = scan.nextLine().trim();
		}else {
			System.out.println(" - Failed to read from console.");
		}
		return result;
	}
	
	/**
	 * Prints the "Write here: " prompt, and then calls the scan() method to retrieve user input.
	 * 
	 * @return - returns the entered string
	 */
	public static String scanWrtHere() {
		Alert.wrtHere();
		return scan();
	}
}
