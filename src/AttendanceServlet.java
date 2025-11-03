import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Attendance Servlet - Part c
 * This servlet handles form submission from the attendance.jsp page
 * and saves the attendance data to the database using JDBC.
 */
@WebServlet("/AttendanceServlet")
public class AttendanceServlet extends HttpServlet {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Retrieve form parameters
        String studentId = request.getParameter("studentId");
        String attendanceDateStr = request.getParameter("attendanceDate");
        String status = request.getParameter("status");
        
        // Output HTML header and styling
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Attendance Submission Status</title>");
        out.println("<style>");
        out.println("body {");
        out.println("    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;");
        out.println("    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);");
        out.println("    min-height: 100vh;");
        out.println("    display: flex;");
        out.println("    justify-content: center;");
        out.println("    align-items: center;");
        out.println("    padding: 20px;");
        out.println("    margin: 0;");
        out.println("}");
        out.println(".container {");
        out.println("    background-color: white;");
        out.println("    border-radius: 15px;");
        out.println("    box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2);");
        out.println("    max-width: 600px;");
        out.println("    width: 100%;");
        out.println("    padding: 40px;");
        out.println("    text-align: center;");
        out.println("}");
        out.println(".success {");
        out.println("    color: #27ae60;");
        out.println("    background-color: #d4edda;");
        out.println("    border: 2px solid #27ae60;");
        out.println("    padding: 20px;");
        out.println("    border-radius: 8px;");
        out.println("    margin: 20px 0;");
        out.println("}");
        out.println(".error {");
        out.println("    color: #c0392b;");
        out.println("    background-color: #f8d7da;");
        out.println("    border: 2px solid #c0392b;");
        out.println("    padding: 20px;");
        out.println("    border-radius: 8px;");
        out.println("    margin: 20px 0;");
        out.println("}");
        out.println(".info-box {");
        out.println("    background-color: #f8f9fa;");
        out.println("    padding: 20px;");
        out.println("    border-radius: 8px;");
        out.println("    margin: 20px 0;");
        out.println("    text-align: left;");
        out.println("}");
        out.println(".info-box h3 {");
        out.println("    color: #667eea;");
        out.println("    margin-top: 0;");
        out.println("}");
        out.println(".info-box p {");
        out.println("    margin: 10px 0;");
        out.println("    color: #333;");
        out.println("}");
        out.println(".info-box strong {");
        out.println("    color: #764ba2;");
        out.println("}");
        out.println(".button-group {");
        out.println("    margin-top: 30px;");
        out.println("    display: flex;");
        out.println("    gap: 10px;");
        out.println("    justify-content: center;");
        out.println("}");
        out.println(".btn {");
        out.println("    padding: 12px 25px;");
        out.println("    font-size: 16px;");
        out.println("    font-weight: 600;");
        out.println("    border: none;");
        out.println("    border-radius: 8px;");
        out.println("    cursor: pointer;");
        out.println("    text-decoration: none;");
        out.println("    display: inline-block;");
        out.println("    transition: all 0.3s ease;");
        out.println("}");
        out.println(".btn-primary {");
        out.println("    background-color: #667eea;");
        out.println("    color: white;");
        out.println("}");
        out.println(".btn-primary:hover {");
        out.println("    background-color: #5568d3;");
        out.println("    transform: translateY(-2px);");
        out.println("    box-shadow: 0 5px 15px rgba(102, 126, 234, 0.3);");
        out.println("}");
        out.println(".btn-secondary {");
        out.println("    background-color: #6c757d;");
        out.println("    color: white;");
        out.println("}");
        out.println(".btn-secondary:hover {");
        out.println("    background-color: #5a6268;");
        out.println("}");
        out.println("h1 { color: #333; margin-bottom: 10px; }");
        out.println(".icon { font-size: 48px; margin-bottom: 20px; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            // Validate input parameters
            if (studentId == null || studentId.trim().isEmpty() ||
                attendanceDateStr == null || attendanceDateStr.trim().isEmpty() ||
                status == null || status.trim().isEmpty()) {
                
                out.println("<div class='icon'>⚠️</div>");
                out.println("<h1>Invalid Input</h1>");
                out.println("<div class='error'>");
                out.println("<strong>Error:</strong> All fields are required. Please fill in all the information.");
                out.println("</div>");
                out.println("<div class='button-group'>");
                out.println("<a href='attendance.jsp' class='btn btn-primary'>Try Again</a>");
                out.println("</div>");
                out.println("</div>");
                out.println("</body>");
                out.println("</html>");
                return;
            }
            
            // Establish database connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            
            // Prepare SQL statement
            String sql = "INSERT INTO Attendance (StudentID, AttendanceDate, Status) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            
            // Set parameters
            pstmt.setString(1, studentId);
            pstmt.setString(2, attendanceDateStr);
            pstmt.setString(3, status);
            
            // Execute the insert
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                // Success response
                out.println("<div class='icon'>✅</div>");
                out.println("<h1>Attendance Recorded Successfully!</h1>");
                out.println("<div class='success'>");
                out.println("<strong>Success:</strong> The attendance record has been saved to the database.");
                out.println("</div>");
                
                out.println("<div class='info-box'>");
                out.println("<h3>Submitted Details:</h3>");
                out.println("<p><strong>Student ID:</strong> " + studentId + "</p>");
                out.println("<p><strong>Date:</strong> " + attendanceDateStr + "</p>");
                out.println("<p><strong>Status:</strong> " + status + "</p>");
                out.println("<p><strong>Submitted At:</strong> " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "</p>");
                out.println("</div>");
                
                out.println("<div class='button-group'>");
                out.println("<a href='attendance.jsp' class='btn btn-primary'>Submit Another</a>");
                out.println("<a href='index.html' class='btn btn-secondary'>Go to Home</a>");
                out.println("</div>");
            } else {
                // Unexpected error
                out.println("<div class='icon'>❌</div>");
                out.println("<h1>Submission Failed</h1>");
                out.println("<div class='error'>");
                out.println("<strong>Error:</strong> Unable to save the attendance record. Please try again.");
                out.println("</div>");
                out.println("<div class='button-group'>");
                out.println("<a href='attendance.jsp' class='btn btn-primary'>Try Again</a>");
                out.println("</div>");
            }
            
        } catch (SQLException e) {
            // Database error
            out.println("<div class='icon'>❌</div>");
            out.println("<h1>Database Error</h1>");
            out.println("<div class='error'>");
            out.println("<strong>Database Error:</strong> " + e.getMessage());
            out.println("<br><br>Please ensure the database is properly configured and the Attendance table exists.");
            out.println("</div>");
            out.println("<div class='button-group'>");
            out.println("<a href='attendance.jsp' class='btn btn-primary'>Try Again</a>");
            out.println("</div>");
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><title>Invalid Request</title></head>");
        out.println("<body>");
        out.println("<h1>Invalid Request Method</h1>");
        out.println("<p>Please use the attendance form to submit data.</p>");
        out.println("<a href='attendance.jsp'>Go to Attendance Form</a>");
        out.println("</body>");
        out.println("</html>");
    }
          }
