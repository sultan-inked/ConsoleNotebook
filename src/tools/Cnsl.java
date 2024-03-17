package tools;

import java.util.Scanner;


public class Cnsl {
	private static final Scanner scan = new Scanner(System.in);
	
	public static String scan() {
		String result = "";
		if(scan.hasNextLine()) {
			result = scan.nextLine().trim();
		}else {
			System.out.println(" - Failed to read from console.");
		}
		return result;
	}
	
	public static String scanWrtHere() {
		Alert.wrtHere();
		return scan();
	}
}
