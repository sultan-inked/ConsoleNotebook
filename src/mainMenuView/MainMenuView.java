package mainMenuView;

import controllers.CheckFileController;
import controllers.MainMenuConditionController;
import controllers.NoteController;
import controllers.NoteListController;
import controllers.SaveNoteController;
import models.Note;
import newNoteMenuView.NewNoteMenuView;
import tools.Alert;
import noteMenuView.NoteMenuView;

/**
 * The main menu of the program, which shows you
 * all the notes and will provide navigation.
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
		noteListController = new NoteListController(new CheckFileController());
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
		// updating note information -
		noteListController.refreshFileNameNumber();
		noteListController.refreshNotesList();
		numberOfNotes = mainMenuConditionController.getNumberOfNotes(noteListController);
		listLimit = mainMenuConditionController.getListLimit();
		pageNumber = mainMenuConditionController.getPageNumber();
		pageNumberLimit = mainMenuConditionController.getPageNumberLimit();
		
		
		// show main menu -
		Alert.separator();
		System.out.println("Simple console notebook");
		mainMenuAlerts.showNotesList(numberOfNotes, listLimit, pageNumber, noteListController, noteController);
		mainMenuAlerts.pageStatus(pageNumber, pageNumberLimit);
		System.out.println("('forward'/'backward'/'exit'/'new')");
		
		// receiving a command from a user -
		String choice = mainMenuAlerts.choiceNoteOrMvmnt(numberOfNotes, listLimit, pageNumber,
				mainMenuConditionController, noteListController);
		
		// executing a user command -
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
			new NewNoteMenuView().newNoteMenu(noteListController, new SaveNoteController());
			mainMenu();
			return;
		case "exit":
			return;
		}
	}
}
