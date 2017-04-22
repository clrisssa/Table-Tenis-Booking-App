<%-- 
    Document   : login
    Created on : 22 Apr, 2017, 3:58:17 PM
    Author     : Clarissa Poedjiono
--%>
<%@page import="MODEL.Employee"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title>Log In Page</title>
    </head>
    <body>
         <%
            //if already logged in, redirect to respect homepage  
            if (session.getAttribute("userSession") != null) {
                Object user = session.getAttribute("userSession");
                
                response.sendRedirect("home.jsp");
                
            }
        %>
        <div class = 'container'>
            <form action = "LoginController" method = "post">
                <div class="form-group">
                    <label for="usr">Username:</label>
                    <input type="text" class="form-control" name="username" required >
                </div>
                <div class="form-group">
                    <label for="pwd">Password:</label>
                    <input type="password" class="form-control" name="password" required >
                </div>

                

                <br>
                <br>

                <button type="submit" class="btn btn-primary btn-md">Log In</button>
                
                <%
                    String loginError = (String)session.getAttribute("loginError");
                    if(loginError!=null){
                        out.println("<font color = 'red'>" + loginError + "</font> ");
                        session.removeAttribute("loginError");
                    }
                
                %>        
            </form>
        </div>
    </body>
</html>
