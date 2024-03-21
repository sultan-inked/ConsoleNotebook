package controllers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import models.Note;

/**
 * A class to manage a list of notes and files to store the list of notes.
 */
public class NoteListController {
//	Variables:
	private int fileNameNumber;
	private ArrayList<Note> notesList = new ArrayList<>();
	
	private String fileCounterStr = "notes/fileCounter.TXT";
	private String ListOfNotesStr = "notes/ListOfNotes.TXT";
	
//	Constructors:
	/**
	 * Constructor of NoteListController.java without parameters.
	 * When an instance of the class is created, the list with notes
	 * and the variable with the file name counter are updated.
	 */
	public NoteListController(CheckFileController checkFileController) {
		checkFilesAndCreatIfExcept(checkFileController);
		refreshNotesList();
		refreshFileNameNumber();
	}
	
//	Getters:
	/**
	 * Get current list of notes as array of notes.
	 * 
	 * @return - array of notes
	 */
	public Note[] getNotesListArray() {
		return notesList.toArray(new Note[notesList.size()]);
	}
	
	/**
	 * Get number of notes.
	 * 
	 * @return - number of notes as int variable
	 */
	public int getNumberOfNotes() {
		return notesList.size();
	}
	
	/**
	 * Get current value of the counter of the number of file names.
	 * 
	 * @return - number of file names
	 */
	public int getFileNameNumber() {
		return fileNameNumber;
	}
	
//	Methods:
	/**
	 * Check if there are files to store a list and iterates the name value of new files.
	 * If not, they are created with default values.
	 * 
	 * @param checkFileController to check and create file
	 */
	public void checkFilesAndCreatIfExcept(CheckFileController checkFileController) {
		
		if(!checkFileController.checkFileToExists(ListOfNotesStr)) {
			checkFileController.creatNewFile(ListOfNotesStr, null);
		}
		
		if(!checkFileController.checkFileToExists(fileCounterStr)) {
			checkFileController.creatNewFile(fileCounterStr, "0");
		}
	}
	
	/**
	 * Iterate the number of file names and write the value to fileCounter.TXT file.
	 * Updates the fileNameNumber value at the end of the method.
	 */
	public void fileNameIteration() {
		try(var fileWriter = new FileWriter(fileCounterStr)){
			fileNameNumber++;
			String str = fileNameNumber + "";
			fileWriter.write(str);
		}
		catch(FileNotFoundException exc) {
			System.out.println(" - File not found: " + exc);
		}
		catch(IOException exc) {
			System.out.println(" - I-O exception: " + exc);
		}
		refreshFileNameNumber();
	}
	
	/**
	 * Updates the value of the fileNameNumber variable with information
	 * form the fileCounter.TXT file.
	 */
	public void refreshFileNameNumber() {
		try(var bufferedReader = new BufferedReader(new FileReader(fileCounterStr))){
			String str = bufferedReader.readLine();
			fileNameNumber = Integer.parseInt(str);
		}
		catch(FileNotFoundException exc) {
			System.out.println(" - File not found: " + exc);
		}
		catch(IOException exc) {
			System.out.println(" - I-O exception: " + exc);
		}
	}
	
	/**
	 * Update the contents of the notes list from file ListOfNotes.TXTss
	 */
	public void refreshNotesList() {
		String str = "";
		notesList.clear();
		try(var bufferedReader = new BufferedReader(new FileReader(ListOfNotesStr))) {
			do {
				str = bufferedReader.readLine();
				if(str != null) {
					String[] strAr = str.split(" ");
					// create new instance of Note -
					var note = new Note(strAr[0], strAr[1], strAr[2], strAr[3].replaceAll("_", " "));
					notesList.add(note);
				}
			}while(str != null);
		}
		catch(FileNotFoundException exc) {
			System.out.println(" - File not found: " + exc);
		}
		catch(IOException exc) {
			System.out.println(" - Problem when file closing. " + exc);
		}
	}
}
