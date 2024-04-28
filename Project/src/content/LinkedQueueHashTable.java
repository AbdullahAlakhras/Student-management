package content;

import java.io.Serializable;
import java.util.NoSuchElementException;

import given.LinkedQueue;
import given.SinglyLinkedList;

public class LinkedQueueHashTable implements Serializable {
	private static final Course DEFUNCT = new Course(0, null, 0);
	private Course[] table;
	private int size;

	public LinkedQueueHashTable() {
		table = new Course[10];
		this.size = 0;
	}

	public LinkedQueueHashTable(int size) {
		this.table = new Course[size];
		this.size = 0;
	}

	private int hash(int key) {
		return key % table.length;
	}

	private void enlarge() {
		Course[] tmp = table;
		table = new Course[(int) (size / 0.2)]; // New table will have a load factor of 0.2
		int oldSize = size;
		size = 0;
		int i = 0;
		while (oldSize != size) {
			if (tmp[i] != null && tmp[i] != DEFUNCT) {
				addCourse(tmp[i]);
			}
		}

	}

	public void addCourse(Course c) { // Method 1
		if (size / table.length >= 0.5)
			enlarge();

		int index = hash(c.getCrn());
		int i = 0;
		while (table[index] != null) {
			i++;
			index = hash(c.getCrn() + i);
		}
		table[index] = c;
	}

	public Course search(int crn) { // method 2
		int index = hash(crn);
		int i = 0;
		while (table[index] != null && table[index].getCrn() != crn) {
			i++;
			index = hash(crn + i);
		}
		return table[index];

	}

	public void addStudent(int crn, Student s) { // method 3
		Course course = search(crn);
		if (course == null) {
			throw new NoSuchElementException("No course with CRN: " + crn);
		}
		course.addStudent(s);
		size++;
	}

	public void dropStudent(int id, int crn) {
		Course course = search(crn);
		if (course == null) {
			throw new Error("There is no Course with CRN: " + crn);
		}
		if(!course.removeStudent(id)) {
			throw new Error("There is no Student in course with ID: " + id);
		}
	}
	/*public static Course[] studentWaiting(int i) {
		SinglyLinkedList<Course> course= new SinglyLinkedList<Course>();
		boolean exists =false;
		/*for(int j=0;j<size;j++) {
			if (table[j] == null) {
			} else {
				Node current = table[j];
				while (current != null) {
					exists= current.course.existsEnrolled(i)||current.course.existsWaiting(i);
					current = current.next;
				}
			}
		}
		for(int j =0;j<size;j++) {
			if(table[j]!=null) {
				
			}
		}
		if(exists) {
			for(int j =0;j<size;j++) {
				if(table[j]!=null) {
					Coourse current = table[j];
					if(current.next==null) {
						if(current.course.existsWaiting(i)) {
							course.addFirst(current.course);
						}
					}else {
						while (current.next != null) {
							if(current.course.existsWaiting(i)) {
								course.addFirst(current.course);
							}
							current = current.next;
						}
					}
				}
			}
			Course[] courses = new Course[course.size()];
			int k=0;
			while(!course.isEmpty()) {
				courses[k++]=course.removeFirst();
			}
			return courses;
		}else {
			throw new NoSuchElementException("No Student with id: " + i + " is in table");
		}

	}*/
	public Course[] studentWaiting(int i) {
		SinglyLinkedList<Course> course= new SinglyLinkedList<Course>();
		boolean exists = false;
		for(int j =0;j<table.length;j++) {
			if(table[j]!=null && table[j].existsWaiting(i)) {
				course.addFirst(table[j]);
			}
		}
		Course[] courses = new Course[course.size()];
		exists=!course.isEmpty();
		int k =0;
		while(!course.isEmpty()) {
			courses[k++]=course.removeFirst();
		}
		if(exists) {
			return courses;
		}else {
			for(int f=0; f<table.length;f++) {
				if(table[f]!=null && table[f].existsEnrolled(i)) {
						return courses;
				}
			}
		}
		if(!exists) {
			throw new NoSuchElementException("No Student with id: " + i + " is in table");
		}
		return courses;
	}
	public Course[] studentEnrolled(int i) {
		SinglyLinkedList<Course> course= new SinglyLinkedList<Course>();
		boolean exists = false;
		for(int j =0;j<table.length;j++) {
			if(table[j]!=null && table[j].existsEnrolled(i)) {
				course.addFirst(table[j]);
			}
		}
		Course[] courses = new Course[course.size()];
		exists=!course.isEmpty();
		int k =0;
		while(!course.isEmpty()) {
			courses[k++]=course.removeFirst();
		}
		if(exists) {
			return courses;
		}else {
			for(int f=0; f<table.length;f++) {
				if(table[f]!=null && table[f].existsWaiting(i)) {
						return courses;
				}
			}
		}
		if(!exists) {
			throw new NoSuchElementException("No Student with id: " + i + " is in table");
		}
		return courses;
	}
	public void raiseCapacity(int crn, int r) {
		Course course = search(crn);
		if (course == null) {
			throw new Error("There is no Course with CRN:" + crn);
		}
		if (r < 0) {
			throw new IllegalArgumentException("Capacity increase value cannot be negative.");
		}

		course.addCapacity(r);

		}

	}
