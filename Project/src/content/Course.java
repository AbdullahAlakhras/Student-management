package content;

import given.LinkedQueue;
import given.SinglyLinkedList;




public class Course {
	private int crn;
	private String name;
	private int capacity;
	private SinglyLinkedList<Student> enrolled = new SinglyLinkedList<Student>();
	private LinkedQueue<Student> waitingList = new LinkedQueue<Student>();
	
	public Course(int crn, String name, int capacity) {
		setCapacity(capacity);
		setName(name);
		setCrn(crn);
	}
	
	//Adds a student to this course, if course is full adds student to waitingList
	//returns void
	//For use in addStudent(int k, Student s)
	public void addStudent(Student s) {
		if(enrolled.size() < capacity) 
			enrolled.addLast(s);
		else 
			waitingList.enqueue(s);
	}
	
	//Removes a student from the course and adds a student from the waitingList (if it isn't empty) to the enrolled list
	//returns false if student doesn't exist 
	//For use in dropStudent(int i, int c)
	public boolean removeStudent(Student s) {
		Student tmp = null;
		boolean done = false;
		int initialSize = enrolled.size();
		for(int i=1;i<=initialSize;i++) {
			tmp = enrolled.removeFirst();
			if(tmp == s)
				done = true;
			else
				enrolled.addLast(tmp);
		}
		if(done && !waitingList.isEmpty())
			enrolled.addLast(waitingList.dequeue());
		return done;
	}
	
	//Adds capacity to the course and adds from waiting list until course is full or list is empty
	//returns void
	//For use in raiseCapacity(int c, int r)
	public void addCapacity(int r) {
		setCapacity(capacity+r);
		while((enrolled.size() < capacity) && !waitingList.isEmpty()) {
			enrolled.addLast(waitingList.dequeue());
		}
	}
	
	//Checks if student with id i is in enrolled
	//Returns true if in enrolled false otherwise
	//For use in studentEnrolled(int i)
	public boolean existsEnrolled(int i) {
		Student tmp = null;
		boolean exists = false;
		for(int j = 1; j<=enrolled.size();j++) {
			tmp = enrolled.removeFirst();
			if(tmp.getId() == i)
				exists = true;
			enrolled.addLast(tmp);
		}
			
		return exists;
	}
	


	//Checks if student with id i is in waitingList
	//Returns true if in waitingList false otherwise
	//For use in studentWaiting(int i)
	public boolean existsWaiting(int i) {
		Student tmp = null;
		boolean exists = false;
		for(int j = 1; j<=waitingList.size();j++) {
			tmp = waitingList.dequeue();
			if(tmp.getId() == i)
				exists = true;
			waitingList.enqueue(tmp);
		}
		return exists;
	}
	
	//Displays all students in course
	//returns void
	//For use in display students in test class
	public void displayStudents() {
		Student tmp = null;
		System.out.println("Enrolled Students: ");
		for(int j = 1; j<=enrolled.size();j++) {
			tmp = enrolled.removeFirst();
			tmp.display();
			enrolled.addLast(tmp);
		}
		
		System.out.println("Waiting list Students: ");
		for(int j = 1; j<=waitingList.size();j++) {
			tmp = waitingList.dequeue();
			tmp.display();
			waitingList.enqueue(tmp);
		}
	}
	
	//Displays course info
	//returns void
	//For use in student status in test class
	public void displayCourse() {
		System.out.println("[CRN = "+crn+"], Name: "+name+", Capacity = "+capacity);
	}

	public int getCrn() {
		return crn;
	}

	public void setCrn(int crn) {
		this.crn = crn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public SinglyLinkedList<Student> getEnrolled() {
		return enrolled;
	}

	public LinkedQueue<Student> getWaitingList() {
		return waitingList;
	}


	
}
