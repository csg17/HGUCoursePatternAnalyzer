package edu.handong.analysis;

import edu.handong.analysis.datamodel.Course;
import edu.handong.analysis.datamodel.Student;

public class HGUCoursePatternAnalyzer {
	
	String[] lines = {	"1999-1, JC Nam, Java Programming",
						"1999-2, JC Nam, Programming Language Theory",
						"1999-1, JC Nam, Data Structures",
						"2001-1, JC Nam, Database Systems",
						"2018-1, SB Lim, Java Programming",
						"2018-2, SB Lim, Programming Language Theory",
						"2019-1, SB Lim, Data Structures",
						"2019-1, SB Lim, Algorithm Analysis",
						"2018-1, SJ Kim, Java Programming",
						"2018-2, SJ Kim, Programming Language Theory",
						"2019-1, SJ Kim, Logic Design",
						"2019-1, SJ Kim, Algorithm Analysis",
						};

	int numOfStudents;
	int numOfCourses;
	Student[] students;
	Course[] courses;
	
	/**
	 * This method runs our analysis logic to get the list of student and course names from lines.
	 * @param args
	 */
	public void run(String[] args) {
		
		numOfStudents = Integer.parseInt(args[0]);
		numOfCourses = Integer.parseInt(args[1]);

		students = new Student[numOfStudents];
		students = initiateStudentArrayFromLines(lines);
		
		System.out.println("Number of All Students: " + numOfStudents);
		for(Student student: students) {
			System.out.println(student.getName());
		}
		
		courses = new Course[numOfCourses];
		courses = initiateCourseArrayFromLines(lines);
		System.out.println("Number of All Courses: " + numOfCourses);
		for(Course course: courses) {
			System.out.println(course.getCourseName());
		}
		
	}

	/**
	 * This method returns a Student array to initiate the field, students, from lines.
	 * @param lines
	 * @return
	 */
	private Student[] initiateStudentArrayFromLines(String[] lines) {
		/*int j = 0;

		for( int i=0; i<numOfStudents; i++ ) {
			if( i>0 ){
				while(lines!=null){
					if( lines[j].split(",")[1].trim().equals(students[i-1].getName()) ){
						j++;
					}
					else break;
				}
			}
			students[i] = new Student(lines[j].split(",")[1].trim() );
		}
		return students;*/

		int j = 0;
		int check = 0;

		for( int i=0; i<numOfStudents; i++ ) {
			if( i>0 ){
				check=0;

				while(lines!=null){
					check=0;
					for( int k=0; k<i ; k++ ){
						if( lines[j].split(",")[1].trim().equals(students[k].getName()) ) {
							check=1;
							break;
						}
					}
					if( check == 1 ){
						j++;
					}
					else break;
				}

			}
			students[i] = new Student(lines[j].split(",")[1].trim() );
		}
		return students;
	}

	/**
	 * This method check if there is the same name of the second arugement in the array, students
	 * @param students
	 * @param student
	 * @return boolean
	 */
	private boolean studentExist(Student[] students, Student student) {
		int i=0;
		
		while( students!=null ) {
			if( students[i].getName().equals(student) )
				return true;
			i++;
		}

		return false;
	}
	
	/**
	 * This method returns a Course array to initiate the field, courses, from lines.
	 * @param lines
	 * @return
	 */
	private Course[] initiateCourseArrayFromLines(String[] lines) {
		/*
		for( int i=0; i<numOfCourses ; i++ ) {
			courses[i] = new Course( lines[i].split(",")[2].trim() );
		}
		return courses;
		*/
		int j = 0;
		int check = 0;

		for( int i=0; i<numOfCourses; i++ ) {
			if( i>0 ){
				check=0;

				while(lines!=null){
					check=0;
					// 놓쳣던 부분: 중복검사, check의 초기화, 객체들의 배열의 생성문제
					// 이미 저장된 과목들과의 중복을 검사한다.
					// 중복이 된다면 check를 1로 두고, lines에서 다음줄을 읽기 위해 j를 증가시킨다. 그 후에 다음줄과 이미 저장된
					// 과목들배열을 처음부터 다시 검사한다.
					// 중복이 되는 것이 없다면, break로 반복문을 나와서 courses에 저장해준다!
					for( int k=0; k<i ; k++ ){
						if( lines[j].split(",")[2].trim().equals(courses[k].getCourseName()) ) {
							check=1;
							break;
						}
					}
					if( check == 1 ){
						j++;
					}
					else break;
				}

			}
			courses[i] = new Course(lines[j].split(",")[2].trim() );
		}
		return courses;
	}

	/**
	 * This method check if there is the same name of the second argument in the array, courses.
	 * @param courses
	 * @param course
	 * @return boolean
	 */
	private boolean courseExist(Course[] courses, Course course) {
		int i = 0;
		
		while( courses!=null ) {
			if( courses[i].getCourseName().equals(course) )
				return true;
			i++;
		}
		return false;
	}

}

