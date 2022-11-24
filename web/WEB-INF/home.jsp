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
        
         <a href="login">Log out</a>
          <form action="admin" method="post">
             <input type="hidden" name="action" value="viewAllItems" >
                <input type="submit" value="view Items"> 
        </form>
         <c:choose>
          <c:when test="${action == 'ViewItem'}">
                <form action="admin" method="post">
            <table border ="1">
                <tr>
                    <th>Item name</th>
                    <th>category name</th>
                    <th>price</th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach items="${items}" var="item">

                    <tr>
                        <td>${item.getItemName()}</td>
                        <td>${item.getCategory().getCategoryName()}</td>
                        <td>${item.getPrice()}</td>
                                
                        <td> <a href="<c:url value="admin?action=editItem">
                                    <c:param name="action" value="edit item"/>
                                    <c:param name="itemSelected" value="${item.getItemId()}"/>
                                    </c:url>"> Edit item </a> </td>
                        <td> <a href="<c:url value='admin?action=delete&amp;itemId=${item.getItemId()}' />">Delete item </a> </td>
                    </tr>

                </c:forEach>   
            </table> 
        </form>
          </c:when>
                </c:choose>
    </body>
</html>
