<%-- 
    Document   : manageAccount
    Created on : 26-Nov-2022, 1:57:48 PM
    Author     : Hanin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <title>Manage Account</title>
    </head>
    <body>
        <h1>Manage Account</h1>
         <a href="login">Log out</a>
         <c:choose>
              <c:when test="${action == 'editAccount'}">
                                  <h2>Edit User</h2>
            <form  action="" method = "post">
                First Name: <input type="test" name="firstName" value=${user.firstName}><br>
                Last Name: <input type="test" name="lastName" value=${user.lastName}><br>
                Password: <input type="test" name="password" value=${user.password}><br>
                      
                status: Status:<select name="status">
                    <c:choose>
                        <c:when test="${user.isActive()== true}">
                            <option>Active</option>
                                <option>In-active</option>
                        </c:when>
                    </c:choose>
                            
                                     
                    </select><br>
                <input type="hidden" name="action" value="update" >
                <input type="submit" value="update">
                
                

            </form >
                <a href="<c:url value='home?action=viewAllItems' />">Inventory page </a>
                 </c:when>
             
          </c:choose>

    </body>
</html>
