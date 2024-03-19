package editNoteMenuView;

import java.util.List;

import controllers.NoteListController;
import models.Note;
import tools.Alert;

/**
 * Provides tools for editing a note.
 */
public class EditNoteMenuView {
	
//	Variables:
	private final EditNoteMenuAlerts editNoteMenuAlerts;
	private final NoteContentMove noteContentMove;
	private List<String> noteContent;
	
//	Constructors:
	/**
	 * Edit note menu constructor
	 * 
	 * @param note - accepts a note for provide to EditNoteMenuAlerts.java
	 */
	public EditNoteMenuView(Note note) {
		editNoteMenuAlerts = new EditNoteMenuAlerts();
		noteContentMove = new NoteContentMove();
		noteContent = editNoteMenuAlerts.getNoteContentArrayList(note);
	}
	
//	Methods:
	/**
	 * Prints the content of the note, accepts command from the user
	 * and passed them to executing classes.
	 * 
	 * @param note - accepts a note for editing
	 * @param noteListController - for provide to NoteContentMove.java
	 */
	public void editNoteMenu(Note note, NoteListController noteListController) {
		// show menu -
		Alert.separator();
		System.out.println(" - Edit note menu -");
		
		// Show note content -
		editNoteMenuAlerts.showNoteContentForEdit(noteContent);
		String canWrite = "('<-line>-delete'/'<-line>-edit'/'<-line>-append'/'edit-save'/'edit-remove')";
		System.out.println(canWrite);
		
		// receive a command from a user -
		String[] choiceAr = editNoteMenuAlerts.editMvmnt(noteContent, canWrite);
		
		// executing a user command -
		switch(choiceAr[1]) {
		case "delete":
			noteContentMove.delete(choiceAr[0], noteContent);
			editNoteMenu(note, noteListController);
			return;
		case "edit":
			noteContentMove.edit(choiceAr[0], noteContent);
			editNoteMenu(note, noteListController);
			return;
		case "append":
			noteContentMove.append(choiceAr[0], noteContent);
			editNoteMenu(note, noteListController);
			return;
		case "save":
			noteContentMove.save(note, noteContent, noteListController);
			return;
		case "remove":
			return;
		}
	}
}
