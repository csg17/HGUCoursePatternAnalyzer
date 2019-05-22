package edu.handong.analysis;

import edu.handong.analysis.datamodel.Course;
import edu.handong.analysis.datamodel.Student;
import java.util.*;
import java.util.ArrayList;
import java.util.Iterator;

import edu.handong.analysis.utils.NotEnoughArgumentException;
import edu.handong.analysis.utils.Utils;

public class HGUCoursePatternAnalyzer {
	
	//private int numOfStudents;
	//private int numOfCourses;
	private ArrayList<Student> students = new ArrayList<Student>(); 
	private ArrayList<Course> courses = new ArrayList<Course>();
	private HashMap<String, Student> students_hash = new HashMap<String, Student>();
	
	/**
	 * This method runs our analysis logic to get the list of student and course names from lines.
	 * @param args
	 * @throws NotEnoughArgumentException 
	 */
	public void run(String[] args) throws NotEnoughArgumentException {
		
		//numOfStudents = Integer.parseInt(args[0]);
		//numOfCourses = Integer.parseInt(args[1]);
		
		
		//students = new Student[numOfStudents];
		students_hash = loadStudentsCourseRecords(Utils.getLines(args[0], true));
		TreeMap<String, Student> treemap = new TreeMap(students_hash);
		Utils.writeAFile( countNumberOfCoursesTakenInEachSemester(students_hash), treemap, args[1] );
		/*students = initiateStudentArrayFromLines(getLines(args[0], true));
		
		System.out.println("Number of All Students: " + numOfStudents);
		for(Student student: students) {
			System.out.println(student.getName());
		}
		
		//courses = new Course[numOfCourses];
		courses = initiateCourseArrayFromLines(lines);
		System.out.println("Number of All Courses: " + numOfCourses);
		for(Course course: courses) {
			System.out.println(course.getCourseName());
		}*/
		
	}

	/**
	 * This method returns a Student array to initiate the field, students, from lines.
	 * @param lines
	 * @return
	 */
	/*private ArrayList<Student> initiateStudentArrayFromLines(ArrayList<String> lines) {		
		
		// nullptrexception error 발
		 int j = 0;
		Student tempStudent = new Student(lines[0].split(",")[1].trim() ); //nullptr 되어있었음 

		for( int i=0; i<numOfStudents; i++ ) {
			if( i>0 ){
				while(lines!=null){
					tempStudent = new Student( lines[j].split(",")[1].trim() );
					
					if( studentExist(students, tempStudent) ) j++;
					else break;
				}
			}
			students.add(i,tempStudent);
		}
		return students;
		
		int count = 0;
		for(String line:lines) {
			String studentName = line.split(",")[1].trim();
			Student newStudent = new Student(studentName);
			if(!studentExist(students,newStudent))
				students.add(count++, newStudent);
		}
		
		return students;
	}*/

	/**
	 * This method check if there is the same name of the second arugement in the array, students
	 * @param students
	 * @param student
	 * @return boolean
	 */
	private boolean studentExist(ArrayList<Student> students, Course course) {
		Student[] arrr = new Student[students.size()];
		
		for(int i=0; i<arrr.length; i++) {
			arrr[i] = students.get(i);
		}
		for(int i=0; i<arrr.length; i++) {
			// 1. students에 student들의 학번이랑 검사해서 course의 학번이랑 같은 학생이 있다면
			// 그 학생의 수강한 수강과목에 course를 추가해준다. 
			if( arrr!=null && arrr[i].getName().equals(course.getStudentId()) ) {
				arrr[i].addCourse(course);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method returns a Course array to initiate the field, courses, from lines.
	 * @param lines
	 * @return
	 */
	/*private ArrayList<Course> initiateCourseArrayFromLines(String[] lines) {
		int count = 0;
		for(String line:lines) {
			String courseName = line.split(",")[2].trim();
			Course newCourse = new Course(courseName);
			if(!courseExist(courses,newCourse)) {
				courses.add(count++, newCourse);}
		}
		
		return courses;
	}*/

	/**
	 * This method check if there is the same name of the second argument in the array, courses.
	 * @param courses
	 * @param course
	 * @return boolean
	 */
	/*private boolean courseExist(ArrayList<Course> courses, Course course) {
		/*

		while( courses!=null ) {
			if( courses.get(k).getCourseName().equals(course.getCourseName()) )
				return true;
			else {	
				k++;
				if(courses.get(k)==null) return false;
			}
		}
		
		for(Course cour : courses ) {
			if( cour!=null && cour.getCourseName().equals(course.getCourseName()) ) {
				return true;
			}
		}
		return false;
	}
	*/
	
	//hash map을 리턴한다.
	private HashMap<String, Student> loadStudentsCourseRecords(ArrayList<String> lines){
		HashMap<String, Student> students_thash = new HashMap<String, Student>();
		
		String[] arr = new String[lines.size()];
		int size = 0;
		
		for(String student: lines) {
			arr[size++] = student;
		}
		Course[] courseArr = new Course[lines.size()];

		for( int i=0; i<lines.size(); i++ ) {
			Course newCourse = new Course(arr[i]);
			
			courseArr[i]=newCourse;
			
			if( students!=null && !studentExist(students, newCourse)) {
				// 동일한 학생이 없다면 새로운 학생을 추가해준다. 
				Student newStudent = new Student(newCourse.getStudentId());
				newStudent.addCourse(newCourse);
				students.add( newStudent );
				
				students_thash.put(newCourse.getStudentId(), newStudent);
			}
			
		}
		//  1 - 학번이1인학생 
		return students_thash;
	}
	
	private ArrayList<String> countNumberOfCoursesTakenInEachSemester(Map<String, Student> sortedStudents){
		//map: 1학번-1학번 친구의 정보
		ArrayList<String> numOfCourse = new ArrayList<String>();
		int size = sortedStudents.size();
		int totalNumSemester = 0;
		Student stud = new Student();
		
		/*System.out.print(sortedStudents);
		for(int i=0; i<size; i++) {
			Student stud = sortedStudents.get(Integer.toString(i));
			//System.out.print(stud.getName());
			
			totalNumSemester = stud.getSemestersByYearAndSemester().size();
			for(int j=1; j<=totalNumSemester; j++) {
				numOfCourse.add(Integer.toString(stud.getNumCourseInNthSemester(j)));
			}
		}
		*/
		java.util.Iterator iter = sortedStudents.keySet().iterator();
		for(Map.Entry<String, Student> entry: sortedStudents.entrySet()) {
			stud = entry.getValue();
			
			HashMap<String, Integer> temp = stud.getSemestersByYearAndSemester();
			TreeMap<String, Integer> treetemp = new TreeMap(temp);
			totalNumSemester = treetemp.size();
			
			for(int j=1; j<=totalNumSemester; j++) {
				numOfCourse.add(Integer.toString(stud.getNumCourseInNthSemester(j)));
			}
		}
		return numOfCourse;
	}
}

