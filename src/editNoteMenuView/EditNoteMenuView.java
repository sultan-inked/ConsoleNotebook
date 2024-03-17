package editNoteMenuView;

import java.util.List;

import controllers.NoteListController;
import controllers.SaveNoteController;
import models.Note;
import tools.Alert;

public class EditNoteMenuView {
	/*
	 *  - Edit note menu -
	 *  t| Title of note: <text>
	 *  1| <text>
	 *  2| <text>
	 *  3| <text>
	 *  4| <text
	 *  ('<string-number>-delete'/'<string-number>-edit'/'<string-number>-append'/
	 *   'edit-save'/'edit-remove')
	 *  Write here:
	 */
	
//	Variables:
	private final EditNoteMenuAlerts editNoteMenuAlerts;
	private final NoteContentMove noteContentMove;
	private List<String> noteContent;
	
//	Constructors:
	public EditNoteMenuView(Note note) {
		editNoteMenuAlerts = new EditNoteMenuAlerts();
		noteContentMove = new NoteContentMove();
		noteContent = editNoteMenuAlerts.getNoteContentArrayList(note);
	}
	
//	Methods:
	public void editNoteMenu(Note note, NoteListController noteListController) {
		Alert.separator();
		System.out.println(" - Edit note menu -");
		
		// TODO: recover next code line ↓†††
//		List<String> noteContent = editNoteMenuAlerts.getNoteContentArrayList(note);
		editNoteMenuAlerts.showNoteContentForEdit(note, noteContent);
		
		String canWrite = "('<string-number>-delete'/'<string-number>-edit'/'edit-save'/'edit-remove')";
		System.out.println(canWrite);
		
		String[] choiceAr = editNoteMenuAlerts.editMvmnt(noteContent, canWrite);
		
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
//			editNoteMenu(note, noteListController);
			return;
		case "remove":
			return;
		}
		
	}
}
