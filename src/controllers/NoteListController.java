package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
	
//	Methods:
	public void saveTemporaryNote() {
		File fileTemp = new File("notes/temp.TXT");
		fileNameIteration();
		File file = new File("notes/" + fileNameNumber + ".TXT");
		fileTemp.renameTo(file);
	}
	private void fileNameIteration() {
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

	public void addFileInfoToNoteList() {
		var file = new File("notes/" + fileNameNumber + ".TXT");
		String newFileInfo = getFileInfo(file) + "\r\n";
		var fileTemp = new File("notes/temp.TXT");
		var fileListOfNotes = new File("notes/ListOfNotes.TXT");
		
		try(var fileWriter = new FileWriter(fileTemp, true);
			var bufferedReader = new BufferedReader(new FileReader(fileListOfNotes))){
			fileWriter.write(newFileInfo);
			String str;
			do {
				str = bufferedReader.readLine();
				if(str != null) {
					str += "\r\n";
					fileWriter.write(str);
				}
			}while(str != null);
		}
		catch(IOException exc) {
			System.out.println(" - I-O exception: " + exc);
		}
		
		fileListOfNotes.delete();
		fileTemp.renameTo(fileListOfNotes);
	}
	private String getFileInfo(File file) {
		return fileNameNumber + " " + getCurrentDateTime() + " " + getTitleOfNote(file).replaceAll(" ", "_");
	}
	private String getCurrentDateTime() {
		var currentDateTime = LocalDateTime.now();
		var formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss");
		String formattedDateTime = currentDateTime.format(formatter);
		
		return formattedDateTime;
	}
	private String getTitleOfNote(File file) {
		String str = "";
		try(var bufferedReader = new BufferedReader(new FileReader(file))){
			str = bufferedReader.readLine();
			System.out.println(" *** " + file);
			System.out.println(str);
			
		}
		catch(IOException exc) {
			System.out.println(" - I-O exception: " + exc);
		}
		System.out.println(str);
		return str;
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
