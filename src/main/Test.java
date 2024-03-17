package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import models.Note;

public class Test {
	public static void main(String[] args) {
		var note = new Note("4", "New note", "2020.03.14", "12:43");
		System.out.println(note.getNoteTitle());
	}
}
