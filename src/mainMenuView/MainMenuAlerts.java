package mainMenuView;

import controllers.MainMenuConditionController;
import controllers.NoteController;
import controllers.NoteListController;
import models.Note;
import tools.Cnsl;

/**
 * Helper class for MainMenuView.java
 * Displays a list of notes, and accepts and processes user commands.
 */
public class MainMenuAlerts {
	
	/**
	 * Displays a list of notes based on the specified number at a time
	 * and the current page of the list.
	 * 
	 * @param numberOfNotes - total numbers of notes
	 * @param listLimit - number of displayed notes per page
	 * @param pageNumber - current page of the notes list
	 * @param noteListController - this class contains a list of notes
	 * @param noteController - needed to call the 'showNoteDescription()' method
	 */
	public void showNotesList(int numberOfNotes, int listLimit, int pageNumber, 
			NoteListController noteListController, NoteController noteController) {
		
		Note[] notes = noteListController.getNotesListArray();
		for(int i = (listLimit * pageNumber) - listLimit; i < listLimit * pageNumber; i++) {
			if(i < numberOfNotes) {
				System.out.print(i+1 + ". ");
				noteController.showNoteDescription(notes[i]);
			}else break;
		}
	}
	
	/**
	 * Accepts user commands, validates and returns a valid command.
	 * If the command is not valid, it asks again until it receives a valid one.
	 * 
	 * @param numberOfNotes - total numbers of notes
	 * @param listLimit - number of displayed notes per page
	 * @param pageNumber - current page of the notes list
	 * @param mainMenuConditionController - is using to navigate through the pages of the notes list
	 * @return - the correct user command
	 */
	public String choiceNoteOrMvmnt(int numberOfNotes, int listLimit, int pageNumber,
			MainMenuConditionController mainMenuConditionController) {
		
		String choice;
		while(true){
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
		}
	}
	/**
	 * The method shows the user the allowed commands
	 * based on the number of notes.
	 * 
	 * @param numberOfChoices
	 * @param listLimit
	 * @param mainMenuConditionController
	 */
	private void printEnterNmbrOrMvmnt(int numberOfNotes, int listLimit, 
			MainMenuConditionController mainMenuConditionController) {
		String a = " - Enter note number, ",
				b = "'exit' or 'new'.";
		if(numberOfNotes > listLimit && mainMenuConditionController.pageNumberForwardValidator() &&
				mainMenuConditionController.pageNumberBackwardValidator())
			System.out.println(a + "'forward'/'backward', " + b);
		else if(numberOfNotes > listLimit && mainMenuConditionController.pageNumberForwardValidator())
			System.out.println(a + "'forward', " + b);
		else if(numberOfNotes > listLimit && mainMenuConditionController.pageNumberBackwardValidator())
			System.out.println(a + "'backward', " + b);
		else
			System.out.println(a + b);
	}
	
	/**
	 * Show page status, like: "- 1 of 3 -"
	 * 
	 * @param pageNumber - current page number
	 * @param pageNumberLimit - maximum number of pages
	 */
	public void pageStatus(int pageNumber, int pageNumberLimit) {
		System.out.println(" - " + pageNumber + " of " + pageNumberLimit + " - ");
	}
}
