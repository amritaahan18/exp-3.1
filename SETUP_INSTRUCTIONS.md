# User Login Servlet Application - Setup Instructions

## Experiment 3.1: Web Applications Using Servlets and JSP
### Part A: User Login Using Servlet and HTML Form

---

## Project Overview

This project demonstrates a simple user authentication system using Java Servlets and HTML forms. The application accepts user credentials through an HTML form and validates them against hardcoded values, displaying appropriate success or error messages.

---

## Project Structure

```
exp-3.1/
├── WEB-INF/
│   └── web.xml                 # Web application deployment descriptor
├── src/
│   └── LoginServlet.java       # Java Servlet for handling authentication
├── login.html                  # HTML login form
└── README.md                   # Project documentation
```

---

## Features

1. **HTML Login Form**: A styled login page that accepts username and password
2. **Servlet Processing**: LoginServlet validates credentials and generates dynamic responses
3. **Credential Validation**: Hardcoded username and password validation
4. **Dynamic Response**: Personalized welcome message on success, error message on failure
5. **Session Management**: Basic session configuration

---

## Prerequisites

Before running this application, ensure you have the following installed:

- **Java Development Kit (JDK)**: Version 8 or higher
- **Apache Tomcat**: Version 8.5 or higher (or any other Servlet container)
- **Text Editor/IDE**: Eclipse, IntelliJ IDEA, NetBeans, or VS Code

---

## Default Credentials

The application uses the following hardcoded credentials:

- **Username**: `admin`
- **Password**: `password123`

---

## Setup and Deployment Instructions

### Option 1: Using Apache Tomcat

#### Step 1: Download and Install Tomcat
1. Download Apache Tomcat from [https://tomcat.apache.org/](https://tomcat.apache.org/)
2. Extract the downloaded archive to a directory (e.g., `C:\tomcat` or `/opt/tomcat`)
3. Set the `CATALINA_HOME` environment variable to the Tomcat directory

#### Step 2: Prepare the Application

1. Clone this repository:
   ```bash
   git clone https://github.com/amritaahan18/exp-3.1.git
   cd exp-3.1
   ```

2. Compile the servlet:
   ```bash
   javac -cp "path/to/tomcat/lib/servlet-api.jar" src/LoginServlet.java
   ```

3. Create the WAR directory structure:
   ```
   exp-3.1-app/
   ├── WEB-INF/
   │   ├── web.xml
   │   └── classes/
   │       └── LoginServlet.class
   └── login.html
   ```

4. Copy the compiled class:
   ```bash
   mkdir -p exp-3.1-app/WEB-INF/classes
   cp src/LoginServlet.class exp-3.1-app/WEB-INF/classes/
   cp WEB-INF/web.xml exp-3.1-app/WEB-INF/
   cp login.html exp-3.1-app/
   ```

#### Step 3: Deploy to Tomcat

1. Copy the `exp-3.1-app` folder to Tomcat's `webapps` directory:
   ```bash
   cp -r exp-3.1-app $CATALINA_HOME/webapps/
   ```

2. Start Tomcat:
   - **Windows**: Run `bin\startup.bat`
   - **Linux/Mac**: Run `bin/startup.sh`

3. Access the application:
   - Open your browser and navigate to: `http://localhost:8080/exp-3.1-app/login.html`

---

## How to Use

1. **Open the Login Page**: Navigate to `http://localhost:8080/exp-3.1-app/login.html`

2. **Enter Credentials**:
   - Username: `admin`
   - Password: `password123`

3. **Submit the Form**: Click the "Login" button

4. **View Results**:
   - **Success**: You'll see a welcome message with your username
   - **Failure**: You'll see an error message with a link to try again

---

## Code Explanation

### 1. login.html
- Creates an HTML form with username and password fields
- Uses POST method to send data to LoginServlet
- Includes CSS styling for a modern appearance

### 2. LoginServlet.java
- Extends `HttpServlet` to handle HTTP requests
- Uses `@WebServlet` annotation to map to `/LoginServlet` URL
- Implements `doPost()` method to:
  - Retrieve username and password using `request.getParameter()`
  - Validate credentials against hardcoded values
  - Generate dynamic HTML response using `PrintWriter`
  - Display success or error messages

### 3. web.xml
- Defines servlet configuration and mapping
- Sets login.html as the welcome file
- Configures session timeout (30 minutes)

---

## Key Concepts Demonstrated

1. **Form Handling**: HTML form submission using POST method
2. **Request Parameters**: Retrieving form data using `request.getParameter()`
3. **Servlet Lifecycle**: Understanding doPost() and doGet() methods
4. **Response Generation**: Creating dynamic HTML content using PrintWriter
5. **Servlet Mapping**: Configuring URL patterns in web.xml
6. **Credential Validation**: Basic authentication logic

---

## Troubleshooting

### Issue 1: 404 Error - Servlet Not Found
**Solution**: 
- Verify that LoginServlet.class is in `WEB-INF/classes/` directory
- Check that web.xml has correct servlet mapping
- Restart Tomcat after making changes

### Issue 2: Compilation Errors
**Solution**:
- Ensure servlet-api.jar is in the classpath
- Check Java version compatibility
- Verify all import statements are correct

### Issue 3: Form Not Submitting
**Solution**:
- Check that the form action matches the servlet URL pattern
- Verify method is set to "post"
- Ensure required fields are marked correctly

---

## Enhancements and Extensions

This basic implementation can be extended with:

1. **Database Integration**: Store and validate credentials from a database
2. **Password Encryption**: Hash passwords using SHA-256 or bcrypt
3. **Session Management**: Track logged-in users across pages
4. **Multiple Users**: Support for multiple user accounts
5. **JSP Integration**: Use JSP for view layer instead of generating HTML in servlet
6. **Error Handling**: More robust exception handling
7. **Input Validation**: Client-side and server-side input validation
8. **HTTPS**: Secure communication using SSL/TLS

---

## Learning Objectives

After completing this experiment, you should understand:

- How to create and deploy Java Servlets
- How to handle HTML form submissions
- How to process HTTP requests and generate responses
- How to configure web applications using web.xml
- Basic authentication and validation techniques
- Servlet-to-HTML communication patterns

---

## References

- [Java Servlet Specification](https://jakarta.ee/specifications/servlet/)
- [Apache Tomcat Documentation](https://tomcat.apache.org/tomcat-9.0-doc/)
- [Oracle Java EE Tutorial](https://docs.oracle.com/javaee/7/tutorial/)

---

## Author

amritaahan18

## License

This project is created for educational purposes as part of Experiment 3.1.

---

**Note**: This is a demonstration project. In production environments, always use secure authentication methods, HTTPS, password hashing, and proper security measures.
