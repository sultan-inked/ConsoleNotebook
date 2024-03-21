package controllers;

/**
 * This class is responsible for managing pagination and the state of the
 * application's main menu, ensuring that values are updated and checking
 * if you can navigate through the pages.
 */
public class MainMenuConditionController {
//	Variables:
	private static int pageNumber = 1, pageNumberLimit, listLimit = 5, numberOfNotes;
	
//	Getters:
	/**
	 * Get the current page number.
	 * 
	 * @return - current page number.
	 */
	public int getPageNumber() {
		return pageNumber;
	}
	
	/**
	 * Get the value of the maximum numbers of pages.
	 * 
	 * @return - get a page limit value
	 */
	public int getPageNumberLimit() {
		return pageNumberLimit;
	}
	
	/**
	 * Get the value of the maximum numbers of notes per page.
	 * 
	 * @return - get list limit value
	 */
	public int getListLimit() {
		return listLimit;
	}
	
	/**
	 * Get the value of the number of all notes.
	 * 
	 * @return - number of notes
	 */
	public int getNumberOfNotes(NoteListController noteListController) {
		numberOfNotesRefresh(noteListController);
		pageNumberLimitRefresh();
		return numberOfNotes;
	}
	
//	Methods:
	private void numberOfNotesRefresh(NoteListController noteListController) {
		numberOfNotes = noteListController.getNumberOfNotes();
	}
	private void pageNumberLimitRefresh() {
		pageNumberLimit = (int)Math.ceil((double)numberOfNotes / (double)listLimit);
	}
	
	/**
	 * Moves to the next page of the list.
	 * And increases the pageNumber value by 1.
	 */
	public void pageNumberForward() {
		pageNumber++;
	}
	
	/**
	 * Moves to the previous page of the list.
	 * And decreases the pageNumber value by 1.
	 */
	public void pageNumberBackward() {
		if(pageNumber > 1) pageNumber--;
	}
	
	/**
	 * Checks if it's possible to go to the next page,
	 * returning true if it's possible.
	 * 
	 * @return - boolean value
	 */
	public boolean pageNumberForwardValidator(NoteListController noteListController) {
		numberOfNotesRefresh(noteListController);
		return pageNumber < pageNumberLimit ? true : false;
	}
	
	/**
	 * Checks if it's possible to go to the previous page,
	 * returning true if it's possible.
	 * 
	 * @return - boolean value
	 */
	public boolean pageNumberBackwardValidator() {
		return pageNumber > 1 ? true : false;
	}
}
