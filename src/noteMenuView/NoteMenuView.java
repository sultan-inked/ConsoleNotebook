package noteMenuView;

import controllers.DeleteNoteController;
import controllers.NoteController;
import controllers.NoteListController;
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
	public void showNote(Note note, NoteController noteController, NoteListController noteListController) {
		Alert.separator();
		System.out.println(" - Note menu - ");
		noteMenuAlerts.showNoteContent(note, noteController);
		
		System.out.println("('edit'/'delete'/'back')");
		String choice = noteMenuAlerts.noteMvmnt();
		
		switch(choice) {
		case "edit":
			Alert.ftrNotYet();
			// TODO: make file-edit feature
			showNote(note, noteController, noteListController);
			return;
		case "delete":
			if(noteMenuAlerts.areYouSure("Delete: " + note.getNoteTitle())) {
				new DeleteNoteController().deleteNote(note, noteController, noteListController);
				return;
			}else {
				showNote(note, noteController, noteListController);
				return;
			}
		case "back":
			return;
		}
	}
}
