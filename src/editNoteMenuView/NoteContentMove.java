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

public class NoteContentMove {
	
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
	
	public void delete(String line, List<String> noteContent) {
		if(line.equals("t")) {
			noteContent.remove(0);
			return;
		}
		int lineInt = Integer.parseInt(line);
		noteContent.remove(lineInt);
	}
	
	public void append(String line, List<String> noteContent) {
		System.out.println(" - Write new line.");
		String newLine = Cnsl.scanWrtHere();
		
		int lineInt;
		if(line.equals("t"))
			lineInt = 0;
		else
			lineInt = Integer.parseInt(line);
		
		List<String> noteContentTemp = new ArrayList<>(noteContent.size() +1);
		
		for(int i = 0; i < noteContent.size(); i++) {
			noteContentTemp.add(noteContent.get(i));
			if(i == lineInt)
				noteContentTemp.add(newLine);
		}
		
		noteContent.clear();
		for(String content : noteContentTemp) {
			noteContent.add(content);
		}
	}
	
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
