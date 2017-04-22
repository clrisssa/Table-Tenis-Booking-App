<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Object user = session.getAttribute("userSession");
    if (user == null) {
        response.sendRedirect("login.jsp");
    }
%>
