package noteMenuView;

import controllers.NoteController;
import models.Note;
import tools.Cnsl;

public class NoteMenuAlerts {
	
	public void showNoteContent(Note note, NoteController noteController) {
		int[] content = noteController.getNoteContent(note.getFileName());
		for(int i : content) {
			System.out.print((char)i);
		}
		System.out.println();
	}
	
	public String noteMvmnt() {
		while(true) {
			String choice = Cnsl.scanWrtHere();
			
			switch(choice) {
			case "edit":
				return choice;
			case "delete":
				return choice;
			case "back":
				return choice;
				default:
					System.out.println(" - You can write 'edit'/'delete'/'back'.");
			}
		}
	}
}
