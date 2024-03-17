package controllers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import models.Note;

public class NoteListController {
//	Variables:
	private int fileNameNumber;
	private ArrayList<Note> notesList = new ArrayList<>();
	
//	Constructors:
	public NoteListController() {
		refreshNotesList();
		refreshFileNameNumber();
	}
	
//	Getters:
	public Note[] getNotesListArray() {
		return notesList.toArray(new Note[notesList.size()]);
	}
	
	public int getNumberOfNotes() {
		return notesList.size();
	}
	
	public int getFileNameNumber() {
		return fileNameNumber;
	}
	
//	Methods:
	public void fileNameIteration() {
		try(var fileWriter = new FileWriter("notes/fileCounter.TXT")){
			fileNameNumber++;
			String str = fileNameNumber + "";
			fileWriter.write(str);
		}
		catch(IOException exc) {
			System.out.println(" - I-O exception: " + exc);
		}
		refreshFileNameNumber();
	}
	
	private void refreshFileNameNumber() {
		try(var bufferedReader = new BufferedReader(new FileReader("notes/fileCounter.TXT"))){
			String str = bufferedReader.readLine();
			fileNameNumber = Integer.parseInt(str);
		}
		catch(IOException exc) {
			System.out.println(" - I-O exception: " + exc);
		}
	}
	
	public void refreshNotesList() {
		String str = "";
		notesList.clear();
		try(var bufferedReader = new BufferedReader(new FileReader("notes/ListOfNotes.TXT"))) {
			do {
				str = bufferedReader.readLine();
				if(str != null) {
					String[] strAr = str.split(" ");
					var note = new Note(strAr[0], strAr[1], strAr[2], strAr[3].replaceAll("_", " "));
					notesList.add(note);
				}
			}while(str != null);
		}
		catch(FileNotFoundException exc) {
			System.out.println(" - File not found. " + exc);
		}
		catch(IOException exc) {
			System.out.println(" - Problem when file closing. " + exc);
		}
	}
}
