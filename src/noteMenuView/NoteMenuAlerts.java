package noteMenuView;

import controllers.NoteController;
import models.Note;
import tools.Cnsl;

/**
 * Helper class for NoteMenuView.java
 * Displays note content, accepts, processes, validate and returns user commands.
 */
public class NoteMenuAlerts {
	
//	Methods:
	/**
	 * Requests confirmation from the user to agree to a specific command.
	 * 
	 * @param sureWith - confirm command info for print
	 * @return - confirmation information
	 */
	public boolean areYouSure(String sureWith) {
		System.out.println(sureWith + "\n" +
							"Are you sure?\n" +
							"(yes/no)");
		while(true) {
			String answer = Cnsl.scanWrtHere();
			switch(answer) {
			case "yes":
				return true;
			case "no":
				return false;
				default:
					System.out.println(" - Write 'yes' or 'no'.");
			}
		}
	}
	
	/**
	 * Show note content.
	 * @param note - user-selected note
	 * @param noteController - for taking the note content
	 */
	public void showNoteContent(Note note, NoteController noteController) {
		int[] content = noteController.getNoteContent(note.getFileName());
		for(int i : content) {
			System.out.print((char)i);
		}
		System.out.println();
	}
	
	/**
	 * Accepts user commands, validates and returns a valid command.
	 * @return - the correct user command
	 */
	public String noteMvmnt() {
		while(true) {
			String choice = Cnsl.scanWrtHere();
			
			switch(choice) {
			case "edit":
			case "delete":
			case "back":
				return choice;
				default:
					System.out.println(" - You can write 'edit'/'delete'/'back'.");
			}
		}
	}
}
