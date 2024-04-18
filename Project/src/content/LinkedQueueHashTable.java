package content;

import java.util.NoSuchElementException;

import given.LinkedQueue;
import given.SinglyLinkedList;

public class LinkedQueueHashTable {
	private static Node[] table;
	private static int size;

	public LinkedQueueHashTable(int size) {
		this.table = new Node[size];
		this.size = size;
	}

	private static int hash(int key) {
		return key % size;
	}

	public static void addCourse(Course c) { //Method 1
		int index = hash(c.getCrn());
		if (table[index] == null) {
			table[index] = new Node(c);
		} else {
			Node current = table[index];
			while (current.next != null) {
				current = current.next;
			}
			current.next = new Node(c);
		}
	}

	public static Course search(int crn) { //method 2
		int index = hash(crn);
		if (table[index] == null) {
			return null;
		} else {
			Node current = table[index];
			while (current != null) {
				if (current.course.getCrn() == crn) {
					return current.course;
				}
				current = current.next;
			}
			return null;
		}
	}

	public static void addStudent(int crn, Student s) { //method 3
		Course course = search(crn);
		if (course == null) {
			throw new NoSuchElementException("No course with CRN: " + crn);
		}
		course.addStudent(s);
	}

	public static void dropStudent(int i, int c) {
		if(search(c) == null) { throw new Error("There is no Course with CRN:" + c);}
		Course cou = search(c);
		if(cou.existsEnrolled(i) == false) {
			throw new Error("There is no Student enrolled with ID:" + i);
		}
		else {
			Student tmp = null;
			Student removed = null;
			SinglyLinkedList<Student> enrolled = cou.getEnrolled();
			tmp = enrolled.removeFirst();
			for(int j=1;j<=enrolled.size();j++) {
				tmp = enrolled.removeFirst();
				if(tmp.getId() == i) {
					removed = tmp;
				}else {
					enrolled.addLast(tmp);
				}

			}

			cou.removeStudent(removed);
		}


	}

	public static void raiseCapacity(int c, int r) {

		if(search(c) == null) { throw new Error("There is no Course with CRN:" + c);}
		if (r < 0) {throw new IllegalArgumentException("Capacity increase value cannot be negative.");}
		 
		Course cou = search(c);
		SinglyLinkedList<Student> enrolled = cou.getEnrolled();
		LinkedQueue<Student> waitingList = cou.getWaitingList();
		int newCapacity = r + cou.getCapacity(); 
		cou.setCapacity(newCapacity);

		if(waitingList.size() >= r) {
			for(int j=1;j<=r;j++) {

				enrolled.addLast(waitingList.dequeue());
			}

		}

		else {
			for(int j=1;j<=waitingList.size();j++) {

				enrolled.addLast(waitingList.dequeue());
			}

		}

	}

	private static class Node {
		Course course;
		Node next;

		public Node(Course course) {
			this.course = course;
			this.next = null;
		}
	}}
