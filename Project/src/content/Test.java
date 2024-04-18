package content;

import java.util.Scanner;

public class Test {

	
	
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int choice = 0; 
		boolean stop = false;
		
		//Load object
		
		
		LinkedQueueHashTable table = new LinkedQueueHashTable(5);
		Course arab101 = new Course(10,"ar101",40);
		Student a1 = new Student(100, "Abdullah"); 
		table.addCourse(arab101);
		arab101.addStudent(a1);
		
		
		
		
		
		
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
				System.out.print("CRN: \n");
				crn = input.nextInt();
				input.nextLine(); 

				System.out.print("Course name: \n");
				String courseName = input.nextLine();

				System.out.print("Capacity: \n");
				int cap = input.nextInt();
				input.nextLine(); 

				LinkedQueueHashTable.addCourse(new Course(crn, courseName, cap));
				break;
			case 2:
				System.out.print("\nCRN: ");
				crn = input.nextInt();
				LinkedQueueHashTable.search(crn).displayStudents(); 
				break;
			case 3:
				System.out.print("\nCRN: ");
				crn = input.nextInt();
				System.out.print("\nStudent ID: ");
				id = input.nextInt();
				System.out.print("\nStudent name: ");
				String name = input.nextLine();
				LinkedQueueHashTable.addStudent(crn, new Student(id, name)); 
				break;
			case 4:
				System.out.print("/nCRN: ");
				crn = input.nextInt();
				System.out.print("/nStudent ID: ");
				id = input.nextInt();
				LinkedQueueHashTable.dropStudent(id, crn); 
				break;
			case 5:
				System.out.print("/nCRN: ");
				crn = input.nextInt();
				System.out.print("/nCapacity increase: ");
				int capIncrease = input.nextInt();
				LinkedQueueHashTable.raiseCapacity(crn, capIncrease); 
				break;
//			case 6:
//				System.out.print("/nStudent ID: ");
//				id = input.nextInt();
//				Course[] enrolled = hashTable.studentEnrolled(id); //studentEnrolled not implemented yet
//				Course[] waiting = hashTable.studentWaiting(id); //studentWaiting not implemented yet
//				System.out.println("/nEnrolled Courses: ");
//				for(int i = 0; i < enrolled.length ; i++) 
//					enrolled[i].displayCourse();
//				System.out.println("/nWaiting Courses: ");
//				for(int j = 0; j < waiting.length ; j++)
//					waiting[j].displayCourse();
//				break;
			case 7:
				stop = true;
				System.out.println("Thank You For Using The Students Enrollment System!!!!!");
				break;
			default:
				System.err.println("Please enter a valid choice");
			}
			
			//Save object
		}

	}
	

}
