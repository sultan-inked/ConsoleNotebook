package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import models.Note;

public class DeleteNoteController {
	public void deleteNote(Note note, NoteController noteController, NoteListController noteListController) {
		deleteNoteInNoteList(note,noteController, noteListController);
		deleteNoteFile(note);
	}
	
	private void deleteNoteFile(Note note) {
		var file = new File("notes/" + note.getFileName() + ".TXT");
		file.delete();
	}
	
	private void deleteNoteInNoteList(Note note, NoteController noteController, NoteListController noteListController) {
		String fileInfo = noteController.getNoteInfoByStringReplaceSpaceOfNoteTitle(note);
		
		System.out.println(fileInfo);
		
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
