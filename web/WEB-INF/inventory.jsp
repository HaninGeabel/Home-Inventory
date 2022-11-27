<%-- 
    Document   : inventory
    Created on : 25-Nov-2022, 1:19:23 PM
    Author     : Hanin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inventory Page</title>
    </head>
    <body>
        <h1>Inventory Page</h1>
        <h4>Hello ${user.firstName}
            ${user.lastName} </h4><br>
           ${msg}
        <form action="home" method="post">
             <input type="hidden" name="action" value="back" >
                <input type="submit" value="Back to home page"> 
                        </form>
        <c:choose>
            <c:when test="${items.size()<= 0}">
                                 <form action="home" method="get">
                                        <input type="hidden" name="action" value="AddItem" >
                                        <input type="submit" value="Add item"> 
                                 </form>
            </c:when>
                   
        
          
         
             
            <c:when test="${action == 'viewAllItems'}">
                
                <form action="admin" method="post">
                    
                                <table border ="1">
                                            <tr>
                                                <th>Item name</th>
                                                <th>category name</th>
                                                <th>price</th>
                                                <th></th>
                                                <th></th>
                                                
                                            </tr>
                                                    <c:forEach items="${items}" var="thing" >

                                                            <tr>
                                                                <td>${thing.getItemName()}</td>
                                                                <td>${thing.getCategory().getCategoryName()}</td>
                                                                <td>${thing.getPrice()}</td>
                                                                
                                                                <td>   <a href="<c:url value="home?action=editItem">
                                                                            <c:param name="action" value="editItem"/>
                                                                            <c:param name="SelectedCategoryid" value="${thing.getCategory().getCategoryId()}"/>
                                                                            <c:param name="SelectedId" value="${thing.getItemId()}"/>
                                                                            </c:url>"> Edit </a></td>

                                                                <td> <a href="<c:url value='home?action=deleteItem&amp;SelectedId=${thing.getItemId()}' />">Delete item </a> </td>
                                                            </tr>

                                                    </c:forEach>   
                                </table> 
                </form>
                <form action="home" method="get">
                            <input type="hidden" name="action" value="AddItem" >
                            <input type="submit" value="Add item"> 
                </form>
            </c:when>   
            
        
            <c:when test="${action == 'editItem'}">
                                                                 <h2>Edit item</h2>
                                                    <form  action="" method = "post">
                                                        ${itemName}
                                                        Item Name: <input type="test" name="itemName" value=${item.itemName}><br>
                                                        Category:  <select name="category">

                                                            <option >${categoryName}</option>
                                                                                  
                                                                                <c:forEach var="category" items="${Categories}" >
                                                                            <c:if test="${categoryName ne category.getCategoryName()}">
                                                                                <option>${category.getCategoryName()}</option>
                                                                            </c:if>

                                                                    </c:forEach>
                                                                 </select>
                                                        Price: <input type="text" name="price" value=${item.getPrice()}><br>

                                                        <input type="hidden" name="action" value="updateItem" >

                                                        <input type="submit" value="Update">



                                                    </form >
               
                 </c:when>
             
          
            
                <c:when test="${action == 'AddItem'}">
                     <form  action="" method = "post">
                ${itemName}
                Item Name: <input type="test" name="newItemName"><br>
                Category:  <select name="newCategory">
                                        <c:forEach var="category" items="${Categories}" >

                                        <option>${category.getCategoryName()}</option>
                            </c:forEach>
                         </select>
                Price: <input type="text" name="newPrice" ><br>
               
                <input type="hidden" name="action" value="add" >
                  
                <input type="submit" value="Add">
                
                

            </form >
               
                </c:when>
            
           
                </c:choose>
          
    </body>
</html>
