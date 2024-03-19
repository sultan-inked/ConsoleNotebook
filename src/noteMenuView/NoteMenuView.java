package noteMenuView;

import controllers.DeleteNoteController;
import controllers.NoteController;
import controllers.NoteListController;
import editNoteMenuView.EditNoteMenuView;
import models.Note;
import tools.Alert;

/**
 * A user interface for viewing and interacting with the selected note.
 */
public class NoteMenuView {
//	Variables:
	private static NoteMenuAlerts noteMenuAlerts;
	
//	Constructors:
	/**
	 * Constructor without parameters.
	 * Creates an instance of the 'NoteMenuAlerts.java' helper class.
	 */
	public NoteMenuView() {
		noteMenuAlerts = new NoteMenuAlerts();
	}
	
//	Methods:
	/**
	 * Prints the content of the note, accepts command from the user
	 * and passed them to executing classes.
	 * 
	 * @param note - user-selected note
	 * @param noteController - to retrieve information about the note
	 * @param noteListController - to change the note information in the list
	 */
	public void showNote(Note note, NoteController noteController, NoteListController noteListController) {
		// show menu -
		Alert.separator();
		System.out.println(" - Note menu - ");
		
		// show note content -
		noteMenuAlerts.showNoteContent(note, noteController);
		System.out.println("('edit'/'delete'/'back')");
		
		// receive a command from a user -
		String choice = noteMenuAlerts.noteMvmnt();
		
		// executing a user command -
		switch(choice) {
		case "edit":
			new EditNoteMenuView(note).editNoteMenu(note, noteListController);
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
