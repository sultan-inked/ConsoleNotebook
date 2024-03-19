package editNoteMenuView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import controllers.NoteListController;
import controllers.SaveNoteController;
import models.Note;
import tools.Cnsl;

/**
 * A class that provides methods for editing a note.
 */
public class NoteContentMove {
//	Methods:
	/**
	 * Edits the specified line in the note content as a list.
	 * 
	 * @param line - the line index to edit
	 * @param noteContent - note content to edit
	 */
	public void edit(String line, List<String> noteContent) {
		System.out.println(" - Write new line.");
		String newLine = Cnsl.scanWrtHere();
		
		if(line.equals("t")) {
			noteContent.set(0, newLine);
			return;
		}
		int lineInt = Integer.parseInt(line);
		noteContent.set(lineInt, newLine);
	}
	
	/**
	 * Adds a new line after the specified line.
	 * 
	 * @param line - line index after which to add a new line
	 * @param noteContent - note content to edit
	 */
	public void append(String line, List<String> noteContent) {
		System.out.println(" - Write new line.");
		String newLine = Cnsl.scanWrtHere();
		
		// checking for note title edits -
		int lineInt;
		if(line.equals("t"))
			lineInt = 0;
		else
			lineInt = Integer.parseInt(line);
		
		// make temporal list for new note-content -
		List<String> noteContentTemp = new ArrayList<>(noteContent.size() +1);
		
		// add new line in note-contetn -
		for(int i = 0; i < noteContent.size(); i++) {
			noteContentTemp.add(noteContent.get(i));
			if(i == lineInt)
				noteContentTemp.add(newLine);
		}
		
		// replace old content to new -
		noteContent.clear();
		for(String content : noteContentTemp) {
			noteContent.add(content);
		}
	}
	
	/**
	 * Delete the specified line.
	 * 
	 * @param line - line index for delete
	 * @param noteContent - note content to edit
	 */
	public void delete(String line, List<String> noteContent) {
		if(line.equals("t")) {
			noteContent.remove(0);
			return;
		}
		int lineInt = Integer.parseInt(line);
		noteContent.remove(lineInt);
	}
	
	/**
	 * Save the edited note.
	 * 
	 * @param note - note to retrieve a file name
	 * @param noteContent - note content to save
	 * @param noteListController - to change note info in notes list
	 */
	public void save(Note note, List<String> noteContent, NoteListController noteListController) {
		File file = changeNoteContentInFile(note, noteContent);
		new SaveNoteController().addFileInfoToNoteList(file, noteListController, note.getFileName());
	}
	private File changeNoteContentInFile(Note note, List<String> noteContent) {
		var fileTemp = new File("notes/temp.TXT");
		var file = new File("notes/" + note.getFileName() + ".TXT");
		
		try(var fileWriter = new FileWriter(fileTemp)){
			for(String content : noteContent) {
				content += "\r\n";
				fileWriter.write(content);
			}
		}
		catch(IOException exc) {
			System.out.println(" - NoteContentMove.java:70 : " + exc);
		}
		
		file.delete();
		fileTemp.renameTo(file);
		return file;
	}
}
