package content;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Test {

	private static LinkedQueueHashTable loadFromFile() throws FileNotFoundException, IOException, ClassNotFoundException {
	    LinkedQueueHashTable table = null;
	    try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("data.txt"))) {
	        table = (LinkedQueueHashTable) inputStream.readObject();
	        System.out.println("Data loaded successfully.");
	   
	    if (table == null) {
	        table = new LinkedQueueHashTable(5); // Default size if no data is loaded
	        System.out.println("No object found , new table initialized");
	    }
	    return table;}}
	
	
	
	
	private static void saveToFile(LinkedQueueHashTable table) {
	    try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("data.txt"))) {
	        outputStream.writeObject(table);
	    } catch (IOException e) {
	        e.printStackTrace();}}
	
	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException {
		Scanner input = new Scanner(System.in);
		int choice = 0;
		boolean stop = false;

		LinkedQueueHashTable table = loadFromFile();

		Course arab101 = new Course(10, "ar101", 40);
		Student a1 = new Student(100, "Abdullah");
		table.addCourse(arab101);
		arab101.addStudent(a1);

		while (!stop) {
			System.out.println(
					"====================\n" + "[1] Add a new course.\n" + "[2] Display students in a course.\n"
							+ "[3] Add a student to a course.\n" + "[4] Drop a student from a course.\n"
							+ "[5] Raise a course's capacity.\n" + "[6] Display a student's status.\n" + "[7] Exit");
			System.out.print("Input: ");
			choice = input.nextInt();
			int crn;
			int id;
			switch (choice) {
			case 1:
				System.out.print("CRN: ");
				crn = input.nextInt();
				input.nextLine();

				System.out.print("Course name: ");
				String courseName = input.nextLine();

				System.out.print("Capacity: ");
				int cap = input.nextInt();
				input.nextLine();

				table.addCourse(new Course(crn, courseName, cap));
				break;
			case 2:
				System.out.print("CRN: ");
				crn = input.nextInt();
				System.out.println("====================");
				table.search(crn).displayStudents();
				break;
			case 3:
				System.out.print("CRN: ");
				crn = input.nextInt();
				System.out.print("Student ID: ");
				id = input.nextInt();
				System.out.print("Student name: ");
				input.nextLine();
				String name = input.nextLine();
				try {
					table.addStudent(crn, new Student(id, name));
				} catch (Error e) {
					System.err.println("No course with this CRN");
				}
				break;
			case 4:
				System.out.print("CRN: ");
				crn = input.nextInt();
				System.out.print("Student ID: ");
				id = input.nextInt();
				try {
					table.dropStudent(id, crn);
				} catch (Error e1) {
					System.err.println("No course with this CRN");
				} catch (NoSuchElementException e2) {
					System.err.println("No student with this ID");
				}
				break;
			case 5:
				System.out.print("CRN: ");
				crn = input.nextInt();
				System.out.print("Capacity increase: ");
				int capIncrease = input.nextInt();
				try {
					table.raiseCapacity(crn, capIncrease);
				} catch (IllegalArgumentException e1) {
					System.err.println("Capacity cannot be increased by a negative number");
				} catch (Error e2) {
					System.err.println("No course with this CRN");
				}
				break;
			case 6:
				System.out.print("Student ID: ");
				id = input.nextInt();
				boolean isWaiting;
				boolean isEnrolled;
				Course[] enrolled = null;
				Course[] waiting = null;
				try {
					enrolled = table.studentEnrolled(id);
					isEnrolled = true;
				} catch (NoSuchElementException e) {
					isEnrolled = false;
				}
				try {
					waiting = table.studentWaiting(id);
					isWaiting = true;
				} catch (NoSuchElementException e) {
					isWaiting = false;
				}

				boolean exists = isEnrolled || isWaiting;
				
				if(isEnrolled || isWaiting) {
					if (!isEnrolled)
						System.out.println("Not enrolled in any courses");
					else {
						System.out.println("Enrolled Courses: ");
						for (int i = 0; i < enrolled.length; i++)
							enrolled[i].displayCourse();
					}
					
					if (!isWaiting && exists)
						System.out.println("Not in any waiting list");
					else {
						System.out.println("Waiting Courses: ");
						for (int j = 0; j < waiting.length; j++)
							waiting[j].displayCourse();
					}
				} else {
					System.err.println("No students with this ID");
				}
				break;
			case 7:
				stop = true;
			    System.out.println("Saving data to file...");
			    saveToFile(table);
			    System.out.println("Data saved successfully.");
			    System.out.println("Thank You For Using The Students Enrollment System!!!!!");
			    break;
			default:
				System.err.println("Please enter a valid choice");
			}

			// Save object
		}

	}

}
