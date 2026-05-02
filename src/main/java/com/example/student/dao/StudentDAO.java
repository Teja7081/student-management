package com.example.student.dao;

import com.example.student.model.Student;
import com.example.student.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private static final String INSERT_SQL = "INSERT INTO students (name, email, course) VALUES (?, ?, ?)";
    private static final String SELECT_BY_ID_SQL = "SELECT id, name, email, course FROM students WHERE id = ?";
    private static final String SELECT_ALL_SQL = "SELECT id, name, email, course FROM students ORDER BY id";
    private static final String UPDATE_SQL = "UPDATE students SET name = ?, email = ?, course = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM students WHERE id = ?";

    public List<Student> listAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SQL);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String course = resultSet.getString("course");
                students.add(new Student(id, name, email, course));
            }
        }
        return students;
    }

    public void insertStudent(Student student) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {
            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setString(3, student.getCourse());
            statement.executeUpdate();
        }
    }

    public Student getStudent(int id) throws SQLException {
        Student student = null;
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    student = new Student(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getString("course")
                    );
                }
            }
        }
        return student;
    }

    public void updateStudent(Student student) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setString(3, student.getCourse());
            statement.setInt(4, student.getId());
            statement.executeUpdate();
        }
    }

    public void deleteStudent(int id) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
