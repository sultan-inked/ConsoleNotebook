package newNoteMenuView;

import tools.Alert;
import tools.Cnsl;

import controllers.NoteListController;
import controllers.SaveNoteController;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

/**
 * Provides an interface for creating a new note.
 */
public class NewNoteMenuView {
//	Methods:
	/**
	 * Accept an entry for a new note.
	 * @param noteListController - for saveNoteController methods
	 * @param saveNoteController - to save a new note
	 */
	public void newNoteMenu(NoteListController noteListController, SaveNoteController saveNoteController) {
		// show note form -
		Alert.separator();
		System.out.println(" - New note - ");
		System.out.println("('note-save'/'note-remove')");
		System.out.print("Title of note");
		
		// create temporary file -
		File fileTemp = new File("notes/temp.TXT");
		
		// accepts the contents of a new note line by line -
		try(var fileWriter = new FileWriter(fileTemp, true)){
			String str;
			do {
				System.out.print(": ");
				str = Cnsl.scan();
				
				if(str.equals("note-save")){
					// close stream, otherwise will error with console input -
					fileWriter.close();
					
					File file = saveNoteController.saveTemporaryNoteFile(fileTemp, noteListController);
					
					saveNoteController.addFileInfoToNoteList(file, noteListController, null);
					return;
				}
				if(str.equals("note-remove")) {
					fileTemp.delete();
					return;
				}
				
				str += "\r\n"; // add new-line character
				fileWriter.write(str);
				
			}while(!str.equals("note-save") || !str.equals("note-remove"));
		}
		catch(IOException exc) {
			System.out.println(" - I-O exception: " + exc);
		}
	}
}
