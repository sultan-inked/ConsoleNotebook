package controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.DataInputStream;
import java.io.FileInputStream;

import models.Note;

public class NoteController {
//	Variables:
	
//	Constructors:
	
//	Getters:
	public String getNoteInfoByStringReplaceSpaceOfNoteTitle(Note note) {
		return note.getFileName() + " " + note.getData() + " " + note.getTime() + " " + note.getNoteTitle().replaceAll(" ", "_");
	}
	
//	Methods:
	public void showNoteDescription(Note note) {
		System.out.println(note.getData() + " " + note.getTime() + " " + note.getNoteTitle());
	}
	
	public int[] getNoteContent(String fileName) {
		ArrayList<Integer> content = new ArrayList<>();
		try(var dataInputStream = new DataInputStream(new FileInputStream("notes/" + fileName + ".TXT"))){
			int tempInt;
			do {
				tempInt = dataInputStream.read();
				if(tempInt != -1)
					content.add(tempInt);
			}while(tempInt != -1);
		}
		catch(FileNotFoundException exc) {
			System.out.println(" - File not found. " + exc);
		}
		catch(IOException exc) {
			System.out.println(" - Problem when closing a file. " + exc);
		}
		return content.stream().mapToInt(Integer::intValue).toArray();
	}
}
