<%-- 
    Document   : createAccount
    Created on : 26-Nov-2022, 11:22:54 PM
    Author     : Hanin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>
    </head>
    <body>
       
        
         <c:choose>
            <c:when test="${action == 'create'}">
        ${msg}
          <h2>Create your account</h2>
            <form  action="" method = "post">
                Email: <input type="text" name="email"  required ><br>
                First Name: <input type="text" name="firstName"  required><br>
                Last Name: <input type="text" name="lastName"  required><br>
                Password: <input type="text" name="password"  required><br>
                
                <input type="hidden" name="action" value="add" >
                <input type="submit" value="Add user">

            </form>
          
                   <form  action="" method = "get">
             
               <input type="hidden" name="action" value="back" >
                <input type="submit" value="Back">
          </form>
       </c:when>
        </c:choose>
         
    </body>
</html>
