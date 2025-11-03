import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Employee Servlet - Part b
 * This servlet integrates with a database using JDBC to display employee records.
 * Features:
 * 1. Display all employees in an HTML table
 * 2. Search employee by ID
 */
@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Database configuration
    private static final String DB_URL = "jdbc:mysql://localhost:3306/employeedb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";
    
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ServletException("MySQL JDBC Driver not found", e);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String empIdParam = request.getParameter("empId");
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Employee Management System</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 20px; background-color: #f4f4f4; }");
        out.println("h1 { color: #333; }");
        out.println("table { border-collapse: collapse; width: 100%; margin-top: 20px; background-color: white; }");
        out.println("th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }");
        out.println("th { background-color: #4CAF50; color: white; }");
        out.println("tr:hover { background-color: #f5f5f5; }");
        out.println(".search-form { margin: 20px 0; padding: 20px; background-color: white; border-radius: 5px; }");
        out.println("input[type=text] { padding: 10px; width: 200px; border: 1px solid #ddd; border-radius: 4px; }");
        out.println("input[type=submit] { padding: 10px 20px; background-color: #4CAF50; color: white; border: none; border-radius: 4px; cursor: pointer; }");
        out.println("input[type=submit]:hover { background-color: #45a049; }");
        out.println(".error { color: red; padding: 10px; background-color: #ffebee; border-radius: 4px; }");
        out.println(".back-link { display: inline-block; margin-top: 20px; color: #4CAF50; text-decoration: none; }");
        out.println(".back-link:hover { text-decoration: underline; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Employee Management System</h1>");
        
        // Search form
        out.println("<div class='search-form'>");
        out.println("<form action='EmployeeServlet' method='get'>");
        out.println("<label for='empId'>Search by Employee ID:</label>");
        out.println("<input type='text' id='empId' name='empId' placeholder='Enter Employee ID' required>");
        out.println("<input type='submit' value='Search'>");
        out.println("</form>");
        out.println("</div>");
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            
            if (empIdParam != null && !empIdParam.trim().isEmpty()) {
                // Search for specific employee
                out.println("<h2>Search Results</h2>");
                searchEmployee(conn, empIdParam, out);
                out.println("<a href='EmployeeServlet' class='back-link'>← View All Employees</a>");
            } else {
                // Display all employees
                out.println("<h2>All Employees</h2>");
                displayAllEmployees(conn, out);
            }
            
        } catch (SQLException e) {
            out.println("<div class='error'>");
            out.println("<strong>Database Error:</strong> " + e.getMessage());
            out.println("</div>");
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        out.println("</body>");
        out.println("</html>");
    }
    
    private void displayAllEmployees(Connection conn, PrintWriter out) throws SQLException {
        String query = "SELECT * FROM Employee";
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        
        out.println("<table>");
        out.println("<tr>");
        out.println("<th>Employee ID</th>");
        out.println("<th>Name</th>");
        out.println("<th>Salary</th>");
        out.println("</tr>");
        
        boolean hasRecords = false;
        while (rs.next()) {
            hasRecords = true;
            out.println("<tr>");
            out.println("<td>" + rs.getInt("EmpID") + "</td>");
            out.println("<td>" + rs.getString("Name") + "</td>");
            out.println("<td>$" + String.format("%.2f", rs.getDouble("Salary")) + "</td>");
            out.println("</tr>");
        }
        
        if (!hasRecords) {
            out.println("<tr><td colspan='3'>No employees found in the database.</td></tr>");
        }
        
        out.println("</table>");
        
        rs.close();
        pstmt.close();
    }
    
    private void searchEmployee(Connection conn, String empId, PrintWriter out) throws SQLException {
        String query = "SELECT * FROM Employee WHERE EmpID = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        
        try {
            int employeeId = Integer.parseInt(empId);
            pstmt.setInt(1, employeeId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                out.println("<table>");
                out.println("<tr>");
                out.println("<th>Employee ID</th>");
                out.println("<th>Name</th>");
                out.println("<th>Salary</th>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>" + rs.getInt("EmpID") + "</td>");
                out.println("<td>" + rs.getString("Name") + "</td>");
                out.println("<td>$" + String.format("%.2f", rs.getDouble("Salary")) + "</td>");
                out.println("</tr>");
                out.println("</table>");
            } else {
                out.println("<div class='error'>No employee found with ID: " + empId + "</div>");
            }
            
            rs.close();
        } catch (NumberFormatException e) {
            out.println("<div class='error'>Invalid Employee ID format. Please enter a valid number.</div>");
        }
        
        pstmt.close();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
                                }
