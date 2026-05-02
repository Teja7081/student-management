package com.example.student.controller;

import com.example.student.dao.StudentDAO;
import com.example.student.model.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private StudentDAO studentDAO;

    @Override
    public void init() {
        studentDAO = new StudentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            listStudents(request, response);
        } else {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertStudent(request, response);
                    break;
                case "delete":
                    deleteStudent(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateStudent(request, response);
                    break;
                default:
                    listStudents(request, response);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void listStudents(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("studentList", studentDAO.listAllStudents());
        RequestDispatcher dispatcher = request.getRequestDispatcher("student-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("student", new Student());
        request.setAttribute("formAction", "insert");
        RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = parseIdParameter(request.getParameter("id"));
        Student existingStudent = studentDAO.getStudent(id);
        if (existingStudent == null) {
            request.setAttribute("errorMessage", "Student not found.");
            listStudents(request, response);
            return;
        }
        request.setAttribute("student", existingStudent);
        request.setAttribute("formAction", "update");
        RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String course = request.getParameter("course");

        String validationError = validateStudent(name, email, course);
        if (validationError != null) {
            Student student = new Student(name, email, course);
            request.setAttribute("student", student);
            request.setAttribute("errorMessage", validationError);
            request.setAttribute("formAction", "insert");
            RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
            dispatcher.forward(request, response);
            return;
        }

        Student newStudent = new Student(name, email, course);
        studentDAO.insertStudent(newStudent);
        response.sendRedirect("students");
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = parseIdParameter(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String course = request.getParameter("course");

        String validationError = validateStudent(name, email, course);
        if (validationError != null) {
            Student student = new Student(id, name, email, course);
            request.setAttribute("student", student);
            request.setAttribute("errorMessage", validationError);
            request.setAttribute("formAction", "update");
            RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
            dispatcher.forward(request, response);
            return;
        }

        Student updatedStudent = new Student(id, name, email, course);
        studentDAO.updateStudent(updatedStudent);
        response.sendRedirect("students");
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = parseIdParameter(request.getParameter("id"));
        studentDAO.deleteStudent(id);
        response.sendRedirect("students");
    }

    private int parseIdParameter(String idValue) {
        try {
            return Integer.parseInt(idValue);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private String validateStudent(String name, String email, String course) {
        if (name == null || name.trim().isEmpty()) {
            return "Name is required.";
        }
        if (email == null || email.trim().isEmpty()) {
            return "Email is required.";
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            return "Enter a valid email address.";
        }
        if (course == null || course.trim().isEmpty()) {
            return "Course is required.";
        }
        return null;
    }
}
