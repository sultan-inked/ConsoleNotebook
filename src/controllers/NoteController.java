package controllers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.DataInputStream;
import java.io.FileInputStream;

import models.Note;

public class NoteController {
//	Variables:
	
//	Constructors:
	
//	Methods:
	public void showNoteDescription(Note note) {
		System.out.println(note.getNoteTitle() + " " + note.getData() + " " + note.getTime());
	}
	
	public int[] getNoteContent(String fileName) {
//		String content = "";
//		try(var bufferedReader = new BufferedReader(new FileReader("notes/" + fileName + ".TXT"))){
//			String tempStr;
//			do {
//				tempStr = bufferedReader.readLine();
//				if(tempStr != null)
//					content += tempStr;
//			}while(tempStr != null);
//		}
//		catch(FileNotFoundException exc) {
//			System.out.println(" - File not found. " + exc);
//		}
//		catch(IOException exc) {
//			System.out.println(" - Problem when closing a file. " + exc);
//		}
		
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
