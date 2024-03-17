package noteMenuView;

import controllers.NoteController;
import models.Note;
import tools.Alert;

public class NoteMenuView {
	/*
	 * Kitchen list
	 * 
	 * Bread
	 * Apple
	 * Banana
	 * Potato
	 * Oil
	 * Water
	 * Milk
	 * Pickles
	 * 
	 * Write here: ('edit'/'delete'/'back')
	 */
	
//	Variables:
	private static NoteMenuAlerts noteMenuAlerts;
	
//	Constructors:
	public NoteMenuView() {
		noteMenuAlerts = new NoteMenuAlerts();
	}
	
//	Methods:
	public void showNote(Note note, NoteController noteController) {
		Alert.separator();
		System.out.println(" - Note menu - ");
		noteMenuAlerts.showNoteContent(note, noteController);
		
		String choice = noteMenuAlerts.noteMvmnt();
		
		switch(choice) {
		case "edit":
			Alert.ftrNotYet();
			// TODO: make file-edit feature
			showNote(note, noteController);
			return;
		case "delete":
			Alert.ftrNotYet();
			// TODO: make file-delete feature
			showNote(note, noteController);
			return;
		case "back":
			return;
		}
	}
}
