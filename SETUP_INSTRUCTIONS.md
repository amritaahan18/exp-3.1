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
├── sql/
│   ├── employee_table.sql      # SQL script for Employee table (Part b)
│   └── attendance_table.sql    # SQL script for Attendance table (Part c)
├── src/
│   ├── LoginServlet.java       # Java Servlet for handling authentication (Part a)
│   ├── EmployeeServlet.java    # Java Servlet for employee management (Part b)
│   └── AttendanceServlet.java  # Java Servlet for attendance submission (Part c)
├── login.html                  # HTML login form (Part a)
├── employee.html               # Employee management landing page (Part b)
├── attendance.jsp              # Attendance form JSP (Part c)
└── README.md                   # Project documentation
```

---

## Prerequisites

- **Java Development Kit (JDK)**: Version 8 or higher
- **Apache Tomcat**: Version 9.0 or higher
- **MySQL Database**: Version 5.7 or higher
- **MySQL JDBC Driver**: mysql-connector-java-8.x.jar
- **IDE**: Eclipse, IntelliJ IDEA, or any Java IDE (Optional)
- **Web Browser**: Any modern web browser

---

## Database Setup

### 1. Create Database

```sql
CREATE DATABASE employeedb;
USE employeedb;
```

### 2. Run SQL Scripts

#### For Part b (Employee Management):
```bash
mysql -u root -p employeedb < sql/employee_table.sql
```

#### For Part c (Attendance Portal):
```bash
mysql -u root -p employeedb < sql/attendance_table.sql
```

### 3. Verify Tables

```sql
USE employeedb;
SHOW TABLES;
SELECT * FROM Employee;
SELECT * FROM Attendance;
```

---

## Setup Instructions

### Step 1: Install Apache Tomcat

1. Download Apache Tomcat from [official website](https://tomcat.apache.org/)
2. Extract to a directory (e.g., `C:\apache-tomcat-9.0.xx`)
3. Set `CATALINA_HOME` environment variable to Tomcat directory
4. Add MySQL JDBC driver to `CATALINA_HOME/lib/` directory

### Step 2: Compile Java Servlets

```bash
# Navigate to the src directory
cd src/

# Compile all servlets
javac -cp "path/to/tomcat/lib/servlet-api.jar;path/to/mysql-connector-java.jar" *.java
```

### Step 3: Deploy Application

#### Option A: Using WAR file

1. Create WAR structure:
```
exp-3-1.war/
├── WEB-INF/
│   ├── web.xml
│   └── classes/
│       ├── LoginServlet.class
│       ├── EmployeeServlet.class
│       └── AttendanceServlet.class
├── login.html
├── employee.html
└── attendance.jsp
```

2. Copy WAR file to `CATALINA_HOME/webapps/`

#### Option B: Manual deployment

1. Copy all files to `CATALINA_HOME/webapps/exp-3-1/`
2. Copy compiled `.class` files to `webapps/exp-3-1/WEB-INF/classes/`

### Step 4: Configure Database Connection

Edit the database credentials in the servlet files:

**In EmployeeServlet.java and AttendanceServlet.java:**
```java
private static final String DB_URL = "jdbc:mysql://localhost:3306/employeedb";
private static final String DB_USER = "root";
private static final String DB_PASSWORD = "your_password";
```

Recompile after making changes.

### Step 5: Start Tomcat Server

**Windows:**
```bash
CATALINA_HOME\bin\startup.bat
```

**Linux/Mac:**
```bash
$CATALINA_HOME/bin/startup.sh
```

---

## Testing the Application

### Part A: User Login

1. Open browser and navigate to:
   ```
   http://localhost:8080/exp-3-1/login.html
   ```

2. Test with credentials:
   - Username: `admin`
   - Password: `password123`

3. Expected results:
   - Valid credentials: Success message
   - Invalid credentials: Error message

### Part B: Employee Management System

1. **Access Landing Page:**
   ```
   http://localhost:8080/exp-3-1/employee.html
   ```

2. **View All Employees:**
   - Click "View All Employees" button
   - URL: `http://localhost:8080/exp-3-1/EmployeeServlet`
   - Displays all employees in an HTML table

3. **Search Employee by ID:**
   - Enter Employee ID in the search form
   - Click "Search" button
   - View specific employee details

4. **Test Cases:**
   - Search for Employee ID: 1, 2, 3, 4, 5 (sample data)
   - Try invalid ID to test error handling

### Part C: Student Attendance Portal

1. **Access Attendance Form:**
   ```
   http://localhost:8080/exp-3-1/attendance.jsp
   ```

2. **Submit Attendance:**
   - Enter Student ID (e.g., S001, S002)
   - Select Date (cannot be future date)
   - Choose Status (Present, Absent, Late, Excused)
   - Click "Submit Attendance"

3. **Expected Results:**
   - Success: Confirmation page with submitted details
   - Error: Appropriate error messages for invalid input

4. **Test Cases:**
   - Valid submission: All fields filled correctly
   - Invalid submission: Missing fields
   - Database error: Incorrect database configuration

---

## Troubleshooting

### Common Issues

1. **Port 8080 already in use:**
   - Change Tomcat port in `CATALINA_HOME/conf/server.xml`
   - Look for `<Connector port="8080"...` and change to another port

2. **404 Error:**
   - Verify application deployment in `webapps` directory
   - Check Tomcat logs in `CATALINA_HOME/logs/`
   - Ensure correct URL path

3. **ClassNotFoundException for MySQL Driver:**
   - Add `mysql-connector-java.jar` to `CATALINA_HOME/lib/`
   - Restart Tomcat server

4. **Database Connection Failed:**
   - Verify MySQL service is running
   - Check database credentials in servlet code
   - Ensure database and tables exist
   - Verify JDBC URL format

5. **Servlet Not Found:**
   - Check `web.xml` configuration
   - Verify servlet URL mapping
   - Ensure `.class` files are in correct location

6. **Form Data Not Received:**
   - Check form `action` attribute matches servlet mapping
   - Verify form `method` is correct (GET/POST)
   - Check input field `name` attributes

---

## Project Features

### Part A: User Login (Servlet + HTML)
- Simple authentication system
- Form-based login
- Hardcoded credential validation
- Success/error message display

### Part B: Employee Management (Servlet + JDBC)
- Display all employees in HTML table
- Search employees by ID
- JDBC database integration
- Dynamic HTML generation
- Real-time data retrieval
- Error handling for invalid searches

### Part C: Student Attendance Portal (JSP + Servlet + JDBC)
- JSP-based attendance form
- Form data validation
- Servlet backend processing
- Database insertion using JDBC
- Success/error page display
- Date validation (no future dates)
- Multiple status options

---

## Technologies Used

- **Java Servlets**: Backend logic and request handling
- **JSP (JavaServer Pages)**: Dynamic view layer for Part c
- **JDBC**: Database connectivity
- **MySQL**: Data storage
- **HTML/CSS**: User interface
- **Apache Tomcat**: Web server and servlet container
- **HTTP Protocol**: Client-server communication

---

## Deployment Configuration

### web.xml Configuration

The `web.xml` file includes servlet mappings for all three parts:

```xml
<!-- Part A: Login Servlet -->
<servlet>
    <servlet-name>LoginServlet</servlet-name>
    <servlet-class>LoginServlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>LoginServlet</servlet-name>
    <url-pattern>/LoginServlet</url-pattern>
</servlet-mapping>

<!-- Part B: Employee Servlet -->
<servlet>
    <servlet-name>EmployeeServlet</servlet-name>
    <servlet-class>EmployeeServlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>EmployeeServlet</servlet-name>
    <url-pattern>/EmployeeServlet</url-pattern>
</servlet-mapping>

<!-- Part C: Attendance Servlet -->
<servlet>
    <servlet-name>AttendanceServlet</servlet-name>
    <servlet-class>AttendanceServlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>AttendanceServlet</servlet-name>
    <url-pattern>/AttendanceServlet</url-pattern>
</servlet-mapping>
```

---

## Usage Examples

### Part B: Employee Management

**View All Employees:**
- Direct URL: `http://localhost:8080/exp-3-1/EmployeeServlet`
- Via Landing Page: Click button on `employee.html`

**Search by ID:**
- URL: `http://localhost:8080/exp-3-1/EmployeeServlet?empId=1`
- Via Form: Enter ID in search box and submit

### Part C: Attendance Submission

**Submit Form:**
- Access: `http://localhost:8080/exp-3-1/attendance.jsp`
- Fill all required fields
- Click "Submit Attendance"
- Redirects to `AttendanceServlet` (POST request)
- Displays success or error page

---

## Future Enhancements

This basic implementation can be extended with:

1. **Database Integration for Part A**: Store and validate login credentials from database
2. **Password Encryption**: Hash passwords using SHA-256 or bcrypt
3. **Session Management**: Track logged-in users across pages
4. **Employee CRUD Operations**: Add Create, Update, Delete functionality
5. **Attendance Report Generation**: View and export attendance reports
6. **User Roles and Permissions**: Implement role-based access control
7. **Error Logging**: Comprehensive logging system
8. **Input Validation**: Enhanced client-side and server-side validation
9. **HTTPS**: Secure communication using SSL/TLS
10. **RESTful API**: Convert to REST API architecture
11. **Frontend Framework**: Integrate with React/Angular/Vue.js
12. **Connection Pooling**: Optimize database connections

---

## Learning Objectives

After completing this experiment, you should understand:

- How to create and deploy Java Servlets
- How to handle HTML form submissions
- How to process HTTP requests and generate responses
- How to configure web applications using web.xml
- Basic authentication and validation techniques
- Servlet-to-HTML communication patterns
- **JDBC integration with servlets**
- **Dynamic HTML generation from database queries**
- **JSP usage for view layer**
- **Form data processing and validation**
- **Database CRUD operations**
- **Error handling in web applications**

---

## References

- [Java Servlet Specification](https://jakarta.ee/specifications/servlet/)
- [Apache Tomcat Documentation](https://tomcat.apache.org/tomcat-9.0-doc/)
- [Oracle Java EE Tutorial](https://docs.oracle.com/javaee/7/tutorial/)
- [JDBC Tutorial](https://docs.oracle.com/javase/tutorial/jdbc/)
- [JSP Tutorial](https://docs.oracle.com/javaee/5/tutorial/doc/bnagx.html)
- [MySQL Documentation](https://dev.mysql.com/doc/)

---

## Author

amritaahan18

## License

This project is created for educational purposes as part of Experiment 3.1.

---

**Note**: This is a demonstration project. In production environments, always use secure authentication methods, HTTPS, password hashing, prepared statements (to prevent SQL injection), connection pooling, and proper security measures.
