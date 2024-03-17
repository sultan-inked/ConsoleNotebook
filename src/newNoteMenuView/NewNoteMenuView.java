package newNoteMenuView;

import tools.Alert;
import tools.Cnsl;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import controllers.NoteListController;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

public class NewNoteMenuView {
	/*
	 *  - New note -
	 *  ('note-save'/'note-remove')
	 *  Title of note: <text>
	 *  <text>
	 *  <text>
	 *  ('note-save'/'note-remove')
	 */
	
//	Methods:
	public void newNoteMenu(NoteListController noteListController) {
		Alert.separator();
		System.out.println(" - New note - ");
		System.out.println("('note-save'/'note-remove')");
		System.out.print("Title of note");
		
		File file = new File("notes/temp.TXT");
		String str;
		
		try(var fileWriter = new FileWriter(file, true)){
			do {
				System.out.print(": ");
				str = Cnsl.scan();
				
				if(str.equals("note-save")){
					noteListController.saveTemporaryNote();
					fileWriter.close();
					noteListController.addFileInfoToNoteList();
					return;
				}
				if(str.equals("note-remove")) {
					file.delete();
					return;
				}
				
				str += "\r\n"; // add new-line character
				fileWriter.write(str);
				
			}while(!str.equals("note-save") | !str.equals("note-remove"));
			
		}
		catch(IOException exc) {
			System.out.println(" - I-O exception: " + exc);
		}
	}
}
