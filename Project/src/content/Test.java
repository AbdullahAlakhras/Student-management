package content;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int choice = 0; 
		boolean stop = false;
		
		//Load object
		
		while(!stop) {
			System.out.println("[1] Add a new course.\n" +
							   "[2] Display students in a course.\n" +
							   "[3] Add a student to a course.\n" +
							   "[4] Drop a student from a course.\n" +
							   "[5] Raise a course's capacity.\n" +
							   "[6] Display a student's status.\n" +
							   "[7] Exit");
			System.out.print("Input: ");
			choice = input.nextInt();
			int crn; int id;
			switch(choice) {
			case 1:
				System.out.print("/nCRN: ");
				crn = input.nextInt();
				System.out.print("/nCourse name: ");
				String courseName = input.nextLine();
				System.out.print("/nCapacity: ");
				int cap = input.nextInt();
				hashTable.addCourse(new Course(crn, courseName, cap)); //addCourse not implemented yet
				break;
			case 2:
				System.out.print("/nCRN: ");
				crn = input.nextInt();
				hashTable.search(crn).displayStudents(); //Search not implemented yet
				break;
			case 3:
				System.out.print("/nCRN: ");
				crn = input.nextInt();
				System.out.print("/nStudent ID: ");
				id = input.nextInt();
				System.out.print("/nStudent name: ");
				String name = input.nextLine();
				hashTable.addStudent(crn, new Student(id, name)); //addStudent not implemented yet
				break;
			case 4:
				System.out.print("/nCRN: ");
				crn = input.nextInt();
				System.out.print("/nStudent ID: ");
				id = input.nextInt();
				hashTable.dropStudent(id, crn); //dropStudent not implemented yet
				break;
			case 5:
				System.out.print("/nCRN: ");
				crn = input.nextInt();
				System.out.print("/nCapacity increase: ");
				int capIncrease = input.nextInt();
				hashTable.raiseCapacity(crn, capIncrease); //raiseCapacity not implemented yet
				break;
			case 6:
				System.out.print("/nStudent ID: ");
				id = input.nextInt();
				Course[] enrolled = hashTable.studentEnrolled(id); //studentEnrolled not implemented yet
				Course[] waiting = hashTable.studentWaiting(id); //studentWaiting not implemented yet
				System.out.println("/nEnrolled Courses: ");
				for(int i = 0; i < enrolled.length ; i++) 
					enrolled[i].displayCourse();
				System.out.println("/nWaiting Courses: ");
				for(int j = 0; j < waiting.length ; j++)
					waiting[j].displayCourse();
				break;
			case 7:
				stop = true;
				break;
			default:
				System.err.println("Please enter a valid choice");
			}
			
			//Save object
		}

	}
	

}
