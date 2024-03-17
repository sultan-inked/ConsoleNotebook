package noteMenuView;

import controllers.NoteController;
import models.Note;
import tools.Cnsl;

public class NoteMenuAlerts {
	
	public boolean areYouSure(String printStr) {
		System.out.println(printStr + "\n" +
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
