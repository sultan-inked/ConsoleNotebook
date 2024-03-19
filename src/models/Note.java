package models;

/**
 * The class representing a note contains information about the note file,
 * but don't contain information about the file itself.
 */
public class Note {
//	Variables:
	private String fileName, noteTitle, data, time;
	
	
//	Constructors:
	/**
	 * Constructor of Note.java.
	 * 
	 * @param fileName - name of note file
	 * @param data - the date the note was created or edited
	 * @param time - the time the note was created or edited
	 * @param noteTitle - title of note
	 */
	public Note(String fileName, String data, String time, String noteTitle) {
		this.fileName = fileName;
		this.noteTitle = noteTitle;
		this.data = data;
		this.time = time;
	}

//	Getters and Setters:
	/**
	 * Get file name.
	 * 
	 * @return - file name
	 */
	public String getFileName() {
		return fileName;
	}
	
	/**
	 * Get title of note.
	 * 
	 * @return - title of note
	 */
	public String getNoteTitle() {
		return noteTitle;
	}
	
	/**
	 * Get create or edit date of note.
	 * 
	 * @return - the date the note was created or edited
	 */
	public String getDate() {
		return data;
	}
	
	/**
	 * Get create or edit time of note.
	 * 
	 * @return - the time the note was created or edited
	 */
	public String getTime() {
		return time;
	}
}
