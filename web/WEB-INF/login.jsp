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
         <c:if test="${action eq null}">
        <form method="post" action="home">
            Username:<input type="text" name="email" value=${name} ><br>
            Password:<input type="password" name="password" value=${password}><br>
            <a href="<c:url value='login?action=create' />">Create an account </a>
<!--            <a href="home?create">Create an account</a>-->

            <p>${msg}</p> 
              <input type="hidden" name="action" value="login" >
            <input type="submit" value="log in" >
        </form>
              </c:if>
        <c:if test="${action ne null}">
          <h2>Create your account</h2>
            <form  action="" method = "post">
                Email: <input type="text" name="email"  required ><br>
                 Address: <input type="text" name="address"  required ><br>
                First Name: <input type="text" name="firstName"  required><br>
                Last Name: <input type="text" name="lastName"  required><br>
                Password: <input type="text" name="password"  required><br>
                
                <input type="hidden" name="action" value="add" >
                <input type="submit" value="Add user">

            </form>
            
            
            
        </c:if>

    </body>
</html>
