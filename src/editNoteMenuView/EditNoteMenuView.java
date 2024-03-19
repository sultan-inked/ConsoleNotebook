package editNoteMenuView;

import java.util.List;

import controllers.NoteListController;
import models.Note;
import tools.Alert;

public class EditNoteMenuView {
	
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
			return;
		case "remove":
			return;
		}
		
	}
}
