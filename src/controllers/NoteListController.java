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
		File newFile = new File("notes/" + fileNameNumber + ".TXT");
		addFileInfoToNoteList(newFile);
	}

	private void addFileInfoToNoteList(File file) {
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
		return fileNameNumber + " " + getCurrentDateTime() + " " + getTitleOfNote(file);
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
					var note = new Note(strAr[0], strAr[1], strAr[2], strAr[3]);
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
	
	/*
1 14.03.2024 19:35:34 Kitchen_list
3 13.03.2024 21:20:34 Movie
4 10.03.2024 11:54:12 Books
2 10.03.2024 09:12:54 To_do_list
5 05.03.2024 20:42:21 English
6 04.03.2024 13:34:32 Meth_lab
9 02.03.2024 14:54:15 Barry_Alen
7 27.02.2024 08:10:22 Members
8 26.12.2023 21:47:11 New_Year
	 */
}
