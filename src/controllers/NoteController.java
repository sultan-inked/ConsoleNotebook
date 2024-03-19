package controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.DataInputStream;
import java.io.FileInputStream;

import models.Note;

/**
 * A class for interacting with instances of the Note.java class.
 */
public class NoteController {
//	Getters:
	/**
	 * Get the note information as a string for the main menu, with
	 * spaces in the note title replaced by underscores.
	 * 
	 * @param note - a note to gather information
	 * @return - note information
	 */
	public String getNoteInfoAsStringReplaceSpaceOfNoteTitle(Note note) {
		return note.getFileName() + " " + note.getDate() + " " + note.getTime() +
				" " + note.getNoteTitle().replaceAll(" ", "_");
	}
	
//	Methods:
	/**
	 * To print the description of a note in the notes list in the main menu.
	 * 
	 * @param note - a note for print description
	 */
	public void showNoteDescription(Note note) {
		System.out.println(note.getDate() + " " + note.getTime() + " " + note.getNoteTitle());
	}
	
	/**
	 * Accepts the name of the note file and returns an array of integers
	 * representing the contents of the note file.
	 * 
	 * @param fileName - name of the note file
	 * @return an array of integers representing the contents
	 */
	public int[] getNoteContent(String fileName) {
		ArrayList<Integer> content = new ArrayList<>();
		try(var dataInputStream = new DataInputStream(new FileInputStream("notes/" + fileName + ".TXT"))){
			int tempInt;
			do {
				tempInt = dataInputStream.read();
				if(tempInt != -1)
					content.add(tempInt);
			}while(tempInt != -1);
		}
		catch(FileNotFoundException exc) {
			System.out.println(" - File not found. " + exc);
		}
		catch(IOException exc) {
			System.out.println(" - Problem when closing a file. " + exc);
		}
		return content.stream().mapToInt(Integer::intValue).toArray();
	}
}
