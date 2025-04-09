package com.student.servlet;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/register1")
public class RegisterServlet extends HttpServlet {
    
	private static final long serialVersionUID = 1L;

	// Handles GET request
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();

	    out.println("<html><body>");
	    out.println("<h2>Welcome to Student Registration</h2>");
	    out.println("<form method='post' action='register1'>");
	    out.println("Name: <input type='text' name='name'><br>");
	    out.println("Email: <input type='text' name='email'><br>");
	    out.println("<input type='submit' value='Register'>");
	    out.println("</form>");
	    out.println("</body></html>");
	}

	// Handles POST request
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();

	    String name = request.getParameter("name");
	    String email = request.getParameter("email");

	    String jdbcURL = "jdbc:mysql://localhost:3306/studentdb";
	    String dbUser = "single007";
	    String dbPassword = "single007";

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

	        String sql = "INSERT INTO students (name, email) VALUES (?, ?)";
	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setString(1, name);
	        statement.setString(2, email);

	        int rowsInserted = statement.executeUpdate();

	        out.println("<html><body>");
	        if (rowsInserted > 0) {
	            out.println("<h2>Registration Successful!</h2>");
	            out.println("<p>Name: " + name + "</p>");
	            out.println("<p>Email: " + email + "</p>");
	        } else {
	            out.println("<h2>Registration Failed!</h2>");
	        }
	        out.println("</body></html>");

	        conn.close();
	    } catch (Exception e) {
	        out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
	        e.printStackTrace(out);
	    } finally {
	        out.close();
	    }
	}
}
