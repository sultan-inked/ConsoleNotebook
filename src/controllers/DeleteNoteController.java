package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import models.Note;

/**
 * The class responsible for deleting a note.
 */
public class DeleteNoteController {
	/**
	 * Performs a complete deletion of the specified note.
	 * 
	 * @param note - note to delete
	 * @param noteController - for get note info
	 * @param noteListController - to delete a note from the notes list
	 */
	public void deleteNote(Note note, NoteController noteController, NoteListController noteListController) {
		deleteNoteInNoteList(note,noteController, noteListController);
		deleteNoteFile(note);
	}
	
	/**
	 * Perform deletion of the note file from disk.
	 * 
	 * @param note - note to delete
	 */
	private void deleteNoteFile(Note note) {
		var file = new File("notes/" + note.getFileName() + ".TXT");
		file.delete();
	}
	
	/**
	 * Perform deletion of a note from the list of notes in a file on disk.
	 * 
	 * @param note - note to delete
	 * @param noteController - for get note info
	 * @param noteListController - to delete a note from the notes list
	 */
	private void deleteNoteInNoteList(Note note, NoteController noteController,
			NoteListController noteListController) {
		
		// get note info as string
		String fileInfo = noteController.getNoteInfoAsStringReplaceSpaceOfNoteTitle(note);
		
		System.out.println(fileInfo.replaceAll("_", " ") + " - note deleted.");
		
		var fileTemp = new File("notes/temp.TXT");
		var fileListOfNotes = new File("notes/ListOfNotes.TXT");
		
		try(var fileWriter = new FileWriter(fileTemp);
			var bufferedReader = new BufferedReader(new FileReader(fileListOfNotes))){
			String str;
			do {
				str = bufferedReader.readLine();
				if(str != null && !str.equals(fileInfo)) {
					str += "\r\n";
					fileWriter.write(str);
				}
			}while(str != null);
		}
		catch(IOException exc) {
			System.out.println(" - I-O exception: " + exc);
		}
		
		fileListOfNotes.delete();
		fileTemp.renameTo(fileListOfNotes);
	}
}
