package editNoteMenuView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.Note;
import tools.Cnsl;

/**
 * Helper class for EditNoteMenuView.java
 * Printing note content, and accepts, check and return user commands.
 */
public class EditNoteMenuAlerts {
//	Methods:
	/**
	 * Receives, validates and returns a user command.
	 * 
	 * @param noteContent - note content by List for print
	 * @param canWrite - list of available commands
	 * @return returns a two-entry user command
	 */
	public String[] editMvmnt(List<String> noteContent, String canWrite) {
		while(true) {
			String choice = Cnsl.scanWrtHere();
			String[] choiceAr = choice.split("-");
			
			switch(choice) {
			case "edit-save":
			case "edit-remove":
			case "t-delete":
			case "t-edit":
			case "t-append":
				return choiceAr;
			}
			
			if(choiceAr.length == 2) {
				if(choiceAr[0].matches("[0-9]*") &&
						(choiceAr[1].equals("delete") | choiceAr[1].equals("edit") | choiceAr[1].equals("append"))) {
					int choiceInt = Integer.parseInt(choiceAr[0]);
					if(choiceInt > 0 && choiceInt < noteContent.size()) {
						return choiceAr;
					}
				}
			}
			System.out.println(" - You can write:\n" + canWrite);
		}
	}
	
	/**
	 * Formats the content of the note with each line labeled and print.
	 * 
	 * @param noteContent - note content for edit
	 */
	public void showNoteContentForEdit(List<String> noteContent) {
		for(int i = 0; i < noteContent.size(); i++) {
			if(i == 0)
				System.out.println("t| " + noteContent.get(i));
			else
				System.out.println(i + "| " + noteContent.get(i));
		}
	}
	
	/**
	 * Take note and return the contents of a note as a list.
	 * 
	 * @param note - note to retrieve the file name
	 * @return - contents as a list
	 */
	public List<String> getNoteContentArrayList(Note note){
		List<String> noteContent = new ArrayList<>();
		try(var bufferedReader = new BufferedReader(new FileReader("notes/" + note.getFileName() + ".TXT"))){
			String str;
			do {
				str = bufferedReader.readLine();
				if(str != null) noteContent.add(str);
			}while(str != null);
		}
		catch(IOException exc) {
			System.out.println(" - EditNoteMenuAlerts.java :22 :" + exc);
		}
		return noteContent;
	}
}
