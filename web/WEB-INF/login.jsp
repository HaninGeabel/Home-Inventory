<%-- 
    Document   : login
    Created on : 18-Nov-2022, 6:18:59 PM
    Author     : Hanin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>login Page</title>
    </head>
    <body>
        <h1>Login</h1>
          
         
            
        <form method="post" action="login">
            Username:<input type="text" name="email" value=${name} ><br>
            Password:<input type="password" name="password" value=${password}><br>
            <a href="<c:url value='login?action=create' />">Create an account </a>

            <p>${msg}</p> 
              <input type="hidden" name="action" value="login" >
            <input type="submit" value="log in" >
        </form>
             
        
       
    </body>
</html>
