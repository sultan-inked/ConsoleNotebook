package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SaveNoteController {
	public File saveTemporaryNote(NoteListController noteListController) {
		File fileTemp = new File("notes/temp.TXT");
		noteListController.fileNameIteration();
		File file = new File("notes/" + noteListController.getFileNameNumber() + ".TXT");
		fileTemp.renameTo(file);
		return file;
	}
	
	public void addFileInfoToNoteList(File file, NoteListController noteListController) {
		String newFileInfo = getFileInfo(file, noteListController) + "\r\n";
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
	private String getFileInfo(File file, NoteListController noteListController) {
		return noteListController.getFileNameNumber() + " " + getCurrentDateTime() + " " + getTitleOfNote(file).replaceAll(" ", "_");
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
}
