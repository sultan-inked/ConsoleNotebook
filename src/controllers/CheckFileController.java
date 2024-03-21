package controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CheckFileController {
//	Methods:
	/**
	 * Check if the file with the name specified in the parameters exists.
	 * 
	 * @param fileName file name to check
	 * @return true or false
	 */
	public boolean checkFileToExists(String fileName) {
		File file = new File(fileName);
		
		return file.exists();
	}
	
	/**
	 * Creating a new file and writing a line, if specified.
	 * If only an empty file is to be created, write null in the stringToWriteInNewFile parameter.
	 * 
	 * @param newFileName new file name
	 * @param stringToWriteInNewFile String or null
	 */
	public void creatNewFile(String newFileName, String stringToWriteInNewFile) {
		File newFile = new File(newFileName);
		
		try {
			if(newFile.createNewFile()) {
				System.out.println("File created: " + newFile.getAbsolutePath());
			}else {
				System.out.println(" - The file already exists.");
			}
		}
		catch(IOException exc) {
			System.out.println(" - Error when creating a file.");
		}
		
		if(stringToWriteInNewFile != null) {
			try(var fileWriter = new FileWriter(newFile)){
				fileWriter.write(stringToWriteInNewFile);
			}
			catch(IOException exc) {
				System.out.println(" - Trouble with file writer: " + exc);
			}
		}
	}
}
