package edu.handong.analysis.utils;

import java.util.Scanner;
import java.util.TreeMap;

import edu.handong.analysis.utils.Utils.*;
import edu.handong.analysis.*;
import edu.handong.analysis.datamodel.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;

public class Utils {

	public static ArrayList<String> getLines(String file, boolean removeHeader){
		String filename = file;
		Scanner inputstream = null;
		ArrayList<String> fileLines = new ArrayList<String>();
		
		try {
			inputstream = new Scanner(new File(filename));
		} catch ( FileNotFoundException e ) {
			System.out.println("The file path does not exist. Please check your CLI argument!");
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
	
	public static void writeAFile(ArrayList<String> lines, TreeMap<String, Student> tree, String targetFileName) throws NotEnoughArgumentException{
		PrintWriter outputStream = null;
		ArrayList<String> total = new ArrayList<String>();
		
		try {
			//입력값이 주소인경우랑 파일이름인 경우를 나눠야 하는
			File targetFile = new File(targetFileName);
			if( targetFileName == null ) {
				throw new NotEnoughArgumentException("No CLI argument Exception! Please put a file path.");
			}
			else if(!targetFile.exists()) {
				targetFile.mkdirs();
				outputStream = new PrintWriter( targetFile );
			
			}
			java.util.Iterator iter = tree.keySet().iterator();
			
			outputStream = new PrintWriter( targetFile );

			for( Map.Entry<String, Student> entry: tree.entrySet() ) {
				// 학번이 1인 학생 
				Student tempStud = entry.getValue();
				int totalNumberOfSemester = tempStud.getSemestersByYearAndSemester().size();
				
				for(int i=1; i<=totalNumberOfSemester; i++) {
					outputStream.println( totalNumberOfSemester + "," + tempStud.getNumCourseInNthSemester(i) +  "," + lines + "\n" );
				}
			}

		}
		catch( NotEnoughArgumentException e ) {
			System.out.println(e.getMessage());
			System.exit(0);
		} catch (FileNotFoundException e) {
			e.getMessage();
		}
		
	}
}
