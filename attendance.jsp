<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Attendance Portal</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }
        
        .container {
            background-color: white;
            border-radius: 15px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2);
            max-width: 600px;
            width: 100%;
            padding: 40px;
        }
        
        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 10px;
            font-size: 28px;
        }
        
        .subtitle {
            text-align: center;
            color: #666;
            margin-bottom: 30px;
            font-size: 14px;
        }
        
        .info-box {
            background-color: #e3f2fd;
            border-left: 4px solid #2196F3;
            padding: 15px;
            margin-bottom: 25px;
            border-radius: 4px;
        }
        
        .info-box p {
            color: #1976D2;
            font-size: 14px;
            margin: 0;
        }
        
        .form-group {
            margin-bottom: 20px;
        }
        
        label {
            display: block;
            margin-bottom: 8px;
            color: #333;
            font-weight: 600;
            font-size: 14px;
        }
        
        input[type="text"],
        input[type="date"],
        select {
            width: 100%;
            padding: 12px;
            border: 2px solid #e0e0e0;
            border-radius: 8px;
            font-size: 14px;
            transition: all 0.3s ease;
        }
        
        input[type="text"]:focus,
        input[type="date"]:focus,
        select:focus {
            outline: none;
            border-color: #667eea;
            box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);
        }
        
        select {
            cursor: pointer;
            background-color: white;
        }
        
        .button-group {
            display: flex;
            gap: 10px;
            margin-top: 30px;
        }
        
        button {
            flex: 1;
            padding: 14px;
            font-size: 16px;
            font-weight: 600;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            transition: all 0.3s ease;
        }
        
        button[type="submit"] {
            background-color: #667eea;
            color: white;
        }
        
        button[type="submit"]:hover {
            background-color: #5568d3;
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.3);
        }
        
        button[type="reset"] {
            background-color: #6c757d;
            color: white;
        }
        
        button[type="reset"]:hover {
            background-color: #5a6268;
        }
        
        .required {
            color: #e74c3c;
            margin-left: 2px;
        }
        
        .footer {
            text-align: center;
            margin-top: 20px;
            padding-top: 20px;
            border-top: 1px solid #e0e0e0;
        }
        
        .footer a {
            color: #667eea;
            text-decoration: none;
            font-weight: 600;
        }
        
        .footer a:hover {
            text-decoration: underline;
        }
        
        .icon {
            font-size: 20px;
            margin-right: 5px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1><span class="icon">📚</span>Student Attendance Portal</h1>
        <p class="subtitle">Part c - JSP and Servlet Integration</p>
        
        <div class="info-box">
            <p><strong>📝 Instructions:</strong> Please fill in all the required fields below to record student attendance. The data will be saved to the database upon submission.</p>
        </div>
        
        <form action="AttendanceServlet" method="post">
            <div class="form-group">
                <label for="studentId">
                    Student ID <span class="required">*</span>
                </label>
                <input 
                    type="text" 
                    id="studentId" 
                    name="studentId" 
                    placeholder="Enter Student ID (e.g., S001)" 
                    required
                    pattern="[A-Za-z0-9]+"
                    title="Student ID should contain only letters and numbers"
                >
            </div>
            
            <div class="form-group">
                <label for="attendanceDate">
                    Attendance Date <span class="required">*</span>
                </label>
                <input 
                    type="date" 
                    id="attendanceDate" 
                    name="attendanceDate" 
                    required
                    max="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>"
                >
            </div>
            
            <div class="form-group">
                <label for="status">
                    Attendance Status <span class="required">*</span>
                </label>
                <select id="status" name="status" required>
                    <option value="">-- Select Status --</option>
                    <option value="Present">Present</option>
                    <option value="Absent">Absent</option>
                    <option value="Late">Late</option>
                    <option value="Excused">Excused</option>
                </select>
            </div>
            
            <div class="button-group">
                <button type="submit">✓ Submit Attendance</button>
                <button type="reset">↺ Clear Form</button>
            </div>
        </form>
        
        <div class="footer">
            <a href="index.html">← Back to Home</a>
        </div>
    </div>
</body>
</html>
