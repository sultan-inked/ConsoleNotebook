package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class ensures that notes are saved and the note list is updated by adding information
 * about the new note to the note list file and saving the note title in the corresponding file.
 */
public class SaveNoteController {
//	Methods:
	/**
	 * Performs saving a temporary note file by renaming it.
	 * 
	 * @param noteListController - to iterate the current file name number.
	 * @param fileTemp - temporary file
	 * @return - returns the saved file with a new name
	 */
	public File saveTemporaryNoteFile(File fileTemp, NoteListController noteListController) {
		noteListController.fileNameIteration();
		File file = new File("notes/" + noteListController.getFileNameNumber() + ".TXT");
		fileTemp.renameTo(file);
		return file;
	}
	
	/**
	 * Adds information about a new note to the notes list file.
	 * If a new file is created, the filename variable should be specified as null.
	 * If the file is not new and has been edited, the current file name should be specified.
	 * 
	 * @param file - file to add
	 * @param noteListController - for get file name number value for new file created
	 * @param fileName - the current file name is specified, if the file is new and there is no name yet, it's should be specified as null
	 */
	public void addFileInfoToNoteList(File file, NoteListController noteListController, String fileName) {
		// check for fileName value and get new file info -
		String newFileInfo = fileName == null ?
				getFileInfo(file, noteListController) + "\r\n" :
					getFileInfo(file, fileName) + "\r\n";
		
		var fileTemp = new File("notes/temp.TXT");
		var fileListOfNotes = new File("notes/ListOfNotes.TXT");
		
		try(var fileWriter = new FileWriter(fileTemp, true);
			var bufferedReader = new BufferedReader(new FileReader(fileListOfNotes))){
			fileWriter.write(newFileInfo);
			String str;
			do {
				str = bufferedReader.readLine();
				
				if(str != null) {
					String[] strAr = str.split(" ");
					if(!strAr[0].equals(fileName)) {
						str += "\r\n";
						fileWriter.write(str);
					}
				}
			}while(str != null);
		}
		catch(IOException exc) {
			System.out.println(" - I-O exception: " + exc);
		}
		
		fileListOfNotes.delete();
		fileTemp.renameTo(fileListOfNotes);
	}
	/**
	 * Used to add a new note file.
	 * 
	 * @param file - file to add
	 * @param noteListController - for get file name number value for new file created
	 * @return - get file new info as string
	 */
	private String getFileInfo(File file, NoteListController noteListController) {
		return noteListController.getFileNameNumber() + " " + getCurrentDateTime() + " " + getTitleOfNote(file).replaceAll(" ", "_");
	}
	/**
	 * Used to add a current edit note file.
	 * 
	 * @param fileName - current note file name
	 * @param file - file to add
	 * @return - get file new info as string
	 */
	private String getFileInfo(File file, String fileName) {
		return fileName + " " + getCurrentDateTime() + " " + getTitleOfNote(file).replaceAll(" ", "_");
	}
	/**
	 * Get the current date and time as a string
	 * 
	 * @return date time as a string
	 */
	private String getCurrentDateTime() {
		var currentDateTime = LocalDateTime.now();
		var formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss");
		String formattedDateTime = currentDateTime.format(formatter);
		
		return formattedDateTime;
	}
	/**
	 * Get title of note from note file
	 * 
	 * @param file - file for getting title of note
	 * @return - title of note
	 */
	private String getTitleOfNote(File file) {
		String str = "";
		try(var bufferedReader = new BufferedReader(new FileReader(file))){
			str = bufferedReader.readLine();
			if(str == null || str.equals("")) str = "Untitled note";
		}
		catch(IOException exc) {
			System.out.println(" - I-O exception: " + exc);
		}
		return str;
	}
}
