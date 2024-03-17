package controllers;

public class MainMenuConditionController {
//	Variables:
	private static int pageNumber = 1, pageNumberLimit, listLimit = 5, numberOfNotes;
	
//	Constructors:
	public MainMenuConditionController() {
		
	}
	
//	Getters:
	public int getPageNumber() {
		return pageNumber;
	}
	
	public int getPageNumberLimit() {
		return pageNumberLimit;
	}
	
	public int getListLimit() {
		return listLimit;
	}
	
	public int getNumberOfNotes() {
		numberOfNotesRefresh();
		return numberOfNotes;
	}
	
//	Methods:
	private void numberOfNotesRefresh() {
		numberOfNotes = new NoteListController().getNumberOfNotes();
		pageNumberLimitRefresh();
	}
	private void pageNumberLimitRefresh() {
		pageNumberLimit = (int)Math.ceil((double)numberOfNotes / (double)listLimit);
	}
	
	public void pageNumberReset() {
		pageNumber = 1;
	}
	
	public void pageNumberForward() {
		pageNumber++;
	}
	public boolean pageNumberForwardValidator() {
		numberOfNotesRefresh();
		return pageNumber < pageNumberLimit ? true : false;
	}
	
	public void pageNumberBackward() {
		if(pageNumber > 1) pageNumber--;
	}
	public boolean pageNumberBackwardValidator() {
		return pageNumber > 1 ? true : false;
	}
	
}
