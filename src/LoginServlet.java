import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * LoginServlet handles user login authentication
 * It receives username and password from the HTML form and validates credentials
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Hardcoded credentials for validation
    private static final String VALID_USERNAME = "admin";
    private static final String VALID_PASSWORD = "password123";
    
    /**
     * Handles POST requests from the login form
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Retrieve username and password from request parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Generate HTML response
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Login Result</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }");
        out.println(".result-container { background-color: white; padding: 30px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); text-align: center; }");
        out.println(".success { color: #4CAF50; }");
        out.println(".error { color: #f44336; }");
        out.println("a { display: inline-block; margin-top: 20px; padding: 10px 20px; background-color: #2196F3; color: white; text-decoration: none; border-radius: 4px; }");
        out.println("a:hover { background-color: #0b7dda; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='result-container'>");
        
        // Validate credentials
        if (username != null && password != null && 
            username.equals(VALID_USERNAME) && password.equals(VALID_PASSWORD)) {
            // Successful login
            out.println("<h2 class='success'>Login Successful!</h2>");
            out.println("<p>Welcome, <strong>" + username + "</strong>!</p>");
            out.println("<p>You have successfully logged in to the system.</p>");
        } else {
            // Failed login
            out.println("<h2 class='error'>Login Failed!</h2>");
            out.println("<p>Invalid username or password.</p>");
            out.println("<p>Please try again with correct credentials.</p>");
        }
        
        out.println("<a href='login.html'>Back to Login</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
        
        out.close();
    }
    
    /**
     * Handles GET requests by redirecting to doPost
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doPost(request, response);
    }
}
