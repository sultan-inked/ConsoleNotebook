package models;

public class Note {
//	Variables:
	private String fileName, noteTitle, data, time;
	
	
//	Constructors:
	public Note(String fileName, String noteTitle, String data, String time) {
		this.fileName = fileName;
		this.noteTitle = noteTitle;
		this.data = data;
		this.time = time;
	}

//	Getters and Setters:
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getNoteTitle() {
		return noteTitle;
	}
	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}

	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
