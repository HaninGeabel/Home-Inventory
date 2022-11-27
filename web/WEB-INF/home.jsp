<%-- 
    Document   : home
    Created on : 18-Nov-2022, 6:23:51 PM
    Author     : Hanin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
         <h1>Home Page</h1>
        <h4>Hello ${email} </h4>
        <p>${user.firstName}
           ${user.lastName}
           ${msg}
                    </p>
                    <p>${userUpdated.firstName}
           ${userUpdated.lastName}
                    </p>
        
         <a href="login">Log out</a>
          <form action="home" method="get">
             <input type="hidden" name="action" value="viewAllItems" >
                <input type="submit" value="Inventory page"> 
             
        </form>
          <form action="home" method="post">
             <input type="hidden" name="action" value="editAccount" >
                <input type="submit" value="edit account"> 
        </form>
          
         
                 
    </body>
</html>
