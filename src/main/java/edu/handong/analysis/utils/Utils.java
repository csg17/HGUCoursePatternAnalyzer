package edu.handong.analysis.utils;

import java.util.Scanner;
import java.util.TreeMap;

import edu.handong.analysis.*;
import edu.handong.analysis.datamodel.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Utils {

	public static ArrayList<String> getLines(String file, boolean removeHeader){
		String filename = file;
		Scanner inputstream = null;
		ArrayList<String> fileLines = new ArrayList<String>();
		
		try {
			inputstream = new Scanner(new File(filename));
		} catch ( FileNotFoundException e ) {
			System.out.println("Error opening the file " + filename );
			System.exit (0);
		}
		
		if( removeHeader == true ) {
			String line = inputstream.nextLine();
		}
		
		while( inputstream.hasNextLine() ) {
			fileLines.add(inputstream.nextLine());
		}
		inputstream.close();
		
		return fileLines;
	}
	
	public static void writeAFile(ArrayList<String> lines, String targetFileName) {
		//트리맵에는 
		//TreeMap<String, Student> studentTree = new TreeMap<>();
		PrintWriter outputStream = null;
		
		try {
			outputStream = new PrintWriter(targetFileName);
		}
		catch( FileNotFoundException e ) {
			System.out.print("Error opening the file " + targetFileName );
			System.exit(0);
		}
		
		for(String tempLine : lines) {
			outputStream.println("," + tempLine);
		}
	}
}
