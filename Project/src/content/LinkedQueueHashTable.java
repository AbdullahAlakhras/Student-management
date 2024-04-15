package content;

import java.util.NoSuchElementException;

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

 

    private static class Node {
        Course course;
        Node next;

        public Node(Course course) {
            this.course = course;
            this.next = null;
        }
    }}
