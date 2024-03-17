package controllers;

public class MainMenuConditionController {
//	Variables:
	private static int pageNumber = 1, pageNumberLimit, listLimit = 15, numberOfNotes;
	
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
	
//	Setters:
	public void setNumberOfNotes(int numberOfNotes) {
		if(numberOfNotes >= 0)
			this.numberOfNotes = numberOfNotes;
	}
	
	public void setListLimit(int listLimit) {
		if(listLimit >= 0)
			this.listLimit = listLimit;
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
