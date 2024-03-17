package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Test {
	public static void main(String[] args) {
		try(var bufferedReader = new BufferedReader(new FileReader("notes/14.TXT"))){
			String str = bufferedReader.readLine();
			System.out.println(str);
		}
		catch(IOException exc) {
			System.out.println("***: " + exc);
		}
	}
}
