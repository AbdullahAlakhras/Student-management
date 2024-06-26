package content;

import java.io.Serializable;
import java.util.NoSuchElementException;

import given.LinkedQueue;
import given.SinglyLinkedList;

public class LinkedQueueHashTable implements Serializable {
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
			if (tmp[i] != null) {
				addCourse(tmp[i]);
			}
			i++;
		}

	}

	public void addCourse(Course c) { // Method 1
		if (size / table.length >= 0.5)
			enlarge();

		int index = hash(c.getCrn());
		while (table[index] != null) {
			index = (index + 1) % table.length;
		}
		table[index] = c;
		size++;
	}

	public Course search(int crn) { // method 2
		int index = hash(crn);
		while (table[index] != null && table[index].getCrn() != crn) {
			index = (index + 1) % table.length;
		}
		return table[index];

	}

	public void addStudent(int crn, Student s) { // method 3
		Course course = search(crn);
		if (course == null) {
			throw new Error("No course with CRN: " + crn);
		}
		course.addStudent(s);
	}

	public void dropStudent(int id, int crn) {
		Course course = search(crn);
		if (course == null) {
			throw new Error("There is no Course with CRN: " + crn);
		}
		if (!course.removeStudent(id)) {
			throw new NoSuchElementException("There is no Student in course with ID: " + id);
		}
	}

	public Course[] studentWaiting(int i) {
		SinglyLinkedList<Course> course = new SinglyLinkedList<Course>();
		boolean exists = false;
		for (int j = 0; j < table.length; j++) {
			if (table[j] != null && table[j].existsWaiting(i)) {
				course.addFirst(table[j]);
			}
		}
		Course[] courses = new Course[course.size()];
		exists = !course.isEmpty();
		int k = 0;
		while (!course.isEmpty()) {
			courses[k++] = course.removeFirst();
		}

		if (!exists) {
			throw new NoSuchElementException("No Student with id: " + i + " is in table");
		}
		return courses;
	}

	public Course[] studentEnrolled(int i) {
		SinglyLinkedList<Course> course = new SinglyLinkedList<Course>();
		boolean exists = false;
		for (int j = 0; j < table.length; j++) {
			if (table[j] != null && table[j].existsEnrolled(i)) {
				course.addFirst(table[j]);
			}
		}
		Course[] courses = new Course[course.size()];
		exists = !course.isEmpty();
		int k = 0;
		while (!course.isEmpty()) {
			courses[k++] = course.removeFirst();
		}
		if (!exists) {
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
