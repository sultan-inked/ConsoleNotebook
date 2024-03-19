package mainMenuView;

import controllers.MainMenuConditionController;
import controllers.NoteController;
import controllers.NoteListController;
import models.Note;
import newNoteMenuView.NewNoteMenuView;
import tools.Alert;
import noteMenuView.NoteMenuView;

/**
 * The main menu of the program, which shows you
 * all the notes and will provide navigation.
 * Like next:
 * 
 * Simple console notebook
 * 1. 14.03.2024 19:35 Kitchen_list
 * 2. 13.03.2024 21:20 Movie
 * 3. 10.03.2024 11:54 Books
 * 4. 10.03.2024 09:12 To_do_list
 * - 1 of 3 - 
 * Write here: ('forward'/'backward'/'exit'/'new')
 * 
 * @author sultan
 */
public class MainMenuView {	
//	Variables:
	private final MainMenuAlerts mainMenuAlerts;
	private final NoteListController noteListController;
	private final NoteController noteController;
	private final MainMenuConditionController mainMenuConditionController;
	private final NoteMenuView noteMenuView;
	
	private int numberOfNotes, listLimit, pageNumber, pageNumberLimit;
	
//	Constructors:
	/**
	 * Constructor without parameters.
	 * Creates instances of classes required for the main menu to work.
	 */
	public MainMenuView() {
		mainMenuAlerts = new MainMenuAlerts();
		noteListController = new NoteListController();
		noteController = new NoteController();
		mainMenuConditionController = new MainMenuConditionController();
		noteMenuView = new NoteMenuView();
	}
	
//	Methods:
	/**
	 * The method shows a list of all notes.
	 * It accepts user commands via console.
	 * At each new start the notes state is updated and
	 * the data for displaying the list is obtained.
	 */
	public void mainMenu() {
		numberOfNotes = mainMenuConditionController.getNumberOfNotes();
		listLimit = mainMenuConditionController.getListLimit();
		pageNumber = mainMenuConditionController.getPageNumber();
		pageNumberLimit = mainMenuConditionController.getPageNumberLimit();
		
		noteListController.refreshNotesList();
		
		Alert.separator();
		System.out.println("Simple console notebook");
		mainMenuAlerts.showNotesList(numberOfNotes, listLimit, pageNumber, noteListController, noteController);
		mainMenuAlerts.pageStatus(pageNumber, pageNumberLimit);
		
		String choice = mainMenuAlerts.choiceNoteOrMvmnt(numberOfNotes, listLimit, pageNumber, mainMenuConditionController);
		
		if(choice.matches("[0-9]*") && !choice.equals("")) {
			Note[] notes = noteListController.getNotesListArray();
			noteMenuView.showNote(notes[Integer.parseInt(choice) -1], noteController, noteListController);
			mainMenu();
			return;
		}
		
		switch(choice) {
		case "forward":
			mainMenuConditionController.pageNumberForward();
			mainMenu();
			return;
		case "backward":
			mainMenuConditionController.pageNumberBackward();
			mainMenu();
			return;
		case "new":
			new NewNoteMenuView().newNoteMenu(noteListController);
			mainMenu();
			return;
		case "exit":
			return;
		}
	}
}
