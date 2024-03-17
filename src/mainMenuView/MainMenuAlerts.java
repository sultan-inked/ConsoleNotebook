package mainMenuView;

import java.util.Scanner;

import controllers.MainMenuConditionController;
import controllers.NoteController;
import controllers.NoteListController;
import models.Note;
import tools.Alert;
import tools.Cnsl;

public class MainMenuAlerts {
	
	public Note[] showNotesList(int numberOfNotes, int listLimit, int pageNumber, 
			NoteListController noteListController, NoteController noteController) {
		Note[] notes = noteListController.getNotesListArray();
		for(int i = (listLimit * pageNumber) - listLimit; i < listLimit * pageNumber; i++) {
			if(i < numberOfNotes) {
				System.out.print(i+1 + ". ");
				noteController.showNoteDescription(notes[i]);
			}else break;
		}
		return notes;
	}
	
	public String choiceNoteOrMvmnt(int numberOfNotes, int listLimit, int pageNumber,
			MainMenuConditionController mainMenuConditionController) {
		
		int counter = 0;
		
		String choice = "";
		do{
			choice = Cnsl.scanWrtHere();
			
			switch(choice) {
			case "exit":
				return choice;
			case "new":
				return choice;
			case "forward":
				if(numberOfNotes > listLimit && 
						mainMenuConditionController.pageNumberForwardValidator())
					return choice;
				else break;
			case "backward":
				if(numberOfNotes > listLimit &&
						mainMenuConditionController.pageNumberBackwardValidator())
					return choice;
				else break;
			}
			
			try {
				int choiceInt = Integer.parseInt(choice);
				if(choiceInt > (pageNumber * listLimit) - listLimit && choiceInt <= listLimit * pageNumber &&
						choiceInt <= numberOfNotes)
					return choice;
				else
					printEnterNmbrOrMvmnt(numberOfNotes, listLimit, mainMenuConditionController);
			}
			catch(NumberFormatException exc) {
				printEnterNmbrOrMvmnt(numberOfNotes, listLimit, mainMenuConditionController);
			}
			counter++;
			if(counter > 10) {
				System.out.println(" - Exceeding the number of attempts.");
				return choice;
			}
		}while(!choice.equals(""));
		return choice;
	}
	private void printEnterNmbrOrMvmnt(int numberOfChoices, int listLimit, 
			MainMenuConditionController mainMenuConditionController) {
		String a = " - Enter note number, ",
				b = "'exit' or 'new'.";
		if(numberOfChoices > listLimit && mainMenuConditionController.pageNumberForwardValidator() &&
				mainMenuConditionController.pageNumberBackwardValidator())
			System.out.println(a + "'forward'/'backward', " + b);
		else if(numberOfChoices > listLimit && mainMenuConditionController.pageNumberForwardValidator())
			System.out.println(a + "'forward', " + b);
		else if(numberOfChoices > listLimit && mainMenuConditionController.pageNumberBackwardValidator())
			System.out.println(a + "'backward', " + b);
		else
			System.out.println(a + b);
	}
	
	public void pageStatus(int pageNumber, int pageNumberLimit) {
		System.out.println(" - " + pageNumber + " of " + pageNumberLimit + " - ");
	}
}
