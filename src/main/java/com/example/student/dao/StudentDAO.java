package com.example.student.dao;

import com.example.student.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDAO {
    private static final List<Student> students = new ArrayList<>();
    private static int idCounter = 1;

    static {
        // Initialize with sample data
        students.add(new Student(idCounter++, "John Doe", "john@example.com", "Java"));
        students.add(new Student(idCounter++, "Jane Smith", "jane@example.com", "Python"));
        students.add(new Student(idCounter++, "Bob Johnson", "bob@example.com", "JavaScript"));
    }

    public List<Student> listAllStudents() {
        return new ArrayList<>(students);
    }

    public void insertStudent(Student student) {
        student.setId(idCounter++);
        students.add(student);
    }

    public Student getStudent(int id) {
        Optional<Student> student = students.stream()
                .filter(s -> s.getId() == id)
                .findFirst();
        return student.orElse(null);
    }

    public void updateStudent(Student student) {
        Optional<Student> existingStudent = students.stream()
                .filter(s -> s.getId() == student.getId())
                .findFirst();
        if (existingStudent.isPresent()) {
            Student existing = existingStudent.get();
            existing.setName(student.getName());
            existing.setEmail(student.getEmail());
            existing.setCourse(student.getCourse());
        }
    }

    public void deleteStudent(int id) {
        students.removeIf(s -> s.getId() == id);
    }
}
