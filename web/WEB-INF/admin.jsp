<%-- 
    Document   : admin
    Created on : 22-Nov-2022, 12:38:30 PM
    Author     : Hanin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
         <!--if action ==create ==> email like msg--> 
        <h1>Hello ${email}</h1>
        <a href="login">Log out</a><br>
         <form action="admin" method="post">
             <input type="hidden" name="action" value="view" >
                <input type="submit" value="view all users"> 
        </form>
         <form action="admin" method="get">
             <input type="hidden" name="action" value="create" >
                <input type="submit" value="Create user account"> 
        </form>
         
        <form action="admin" method="post">
                     <input type="hidden" name="action" value="viewAllCategory" >
                <input type="submit" value="Categories"> 
                 </form>
        <form action="admin" method="post">
                     <input type="hidden" name="action" value="addCategory" >
                <input type="submit" value="Add Category"> 
                 </form>
        <c:choose>
            <c:when test="${action == 'view'}">
        
       
        <form action="admin" method="post">
            <table border ="1">
                <tr>
                    <th>Email</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Role</th>
                    <th>status</th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach items="${users}" var="user">

                    <tr>
                        <td>${user.getEmail()}</td>
                        <td>${user.getFirstName()}</td>
                        <td>${user.getLastName()}</td>
                        <c:choose>
                                <c:when test="${user.getRole().getRoleId() == 1}">
                                    <td>system admin</td>
                                </c:when>
                                <c:otherwise>
                                    <td>regular user</td>
                                </c:otherwise>
                            </c:choose>
                        <c:choose>
                                 <c:when test="${user.isActive()==true}">
                                    <td>active</td>
                                </c:when>
                                <c:otherwise>
                                     <td>in-active</td>
                                </c:otherwise>
                        </c:choose>
             
                                
                        <td> <a href="<c:url value="admin?action=edit">
                                    <c:param name="action" value="edit"/>
                                    <c:param name="userEmail" value="${user.email}"/>
                                    </c:url>"> Edit </a> </td>
                        <td> <a href="<c:url value='admin?action=delete&amp;userEmail=${user.email}' />">Delete </a> </td>
                    </tr>

                </c:forEach>   
            </table> 
        </form>
                 <form action="admin" method="post">
                     <input type="hidden" name="action" value="delete" >
                <input type="submit" value="Delete"> 
                 </form>
            </c:when>
            <c:when test="${action == 'create'}">
                 <h2>Create your account</h2>
                 ${msg}
            <form  action="" method = "post">
                Email: <input type="text" name="email"  required ><br>
                First Name: <input type="text" name="firstName"  required><br>
                Last Name: <input type="text" name="lastName"  required><br>
                Password: <input type="text" name="password"  required><br>
                Role:  <select name="role">
                            <c:forEach var="role" items="${roles}" >
                                <c:choose>
                                    <c:when test="${role.getRoleId() == 1}">
                                        <option>system admin</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option>regular user</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                         </select>
                Status:<select name="status">

                                <option>Active</option>
                                <option>In-active</option>
                            
                         </select>  
                <input type="hidden" name="action" value="create" >
                <input type="submit" value="Add user">

            </form>
                 
            </c:when>
                 <c:when test="${action == 'edit'}">
                           <h2>Edit User</h2>
            <form  action="" method = "post">
                Email: ${selectedUser.email}<br>
                First Name: <input type="test" name="firstName" value=${selectedUser.firstName}><br>
                Last Name: <input type="test" name="lastName" value=${selectedUser.lastName}><br>
                Password: <input type="test" name="password" value=${selectedUser.password}><br>
                 Role: <select name="role">
                            <c:choose>
                                <c:when test="${selectedUser.getRole().getRoleId() == 1}">
                                    <option>system admin</option>
                                    <option>regular user</option>                              
                                </c:when>
                                <c:otherwise>
                                    <option>regular user</option>
                                    <option>system admin</option>
                                </c:otherwise>
                            </c:choose>
                                       </select>      
                status: Status:<select name="status">
                    <c:choose>
                        
                        <c:when test="${selectedUser.isActive()== true}">
                            <option>Active</option>
                                <option>In-active</option>
                        </c:when>
                        <c:otherwise>
                            <option>In-active</option>
                             <option>Active</option>
                        </c:otherwise>
                    </c:choose>
                            
                                     
                    </select><br>
                <input type="hidden" name="action" value="edit" >
                <input type="submit" value="update">
                
                

            </form >
                <form action="admin" method="post">
                     <input type="hidden" name="action" value="ViewItem" >
                     <input type="hidden" name="email" value="${selectedUser.email}">
                <input type="submit" value="View user items"> 
                 </form>
                 </c:when>
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
<!--                 <form  action="" method = "post">
             
               Item Name: <input type="test" name="ItemName" value={selectedItem.getItemName()}><br>
               Item price: <input type="number" name="itemPrice"><br>
check if it is empty or not 
                <br>
                <input type="hidden" name="action" value="edit" >
                <input type="submit" value="update">
                
                

            </form >-->
                  <form action="admin" method="post">
                     <input type="hidden" name="action" value="deleteItem" >
                <input type="submit" value="Delete"> 
                 </form>
            </c:when>
           
            
             <c:when test="${action == 'viewAllCategory'}">
                <form action="admin" method="post">
            <table border ="1">
                <tr>
                   
                    <th>category name</th>
                    <th></th>
                    
                </tr>
                <c:forEach items="${categories}" var="category">

                    <tr>
                        <td>${category.getCategoryName()}</td>        
                        <td> <a href="<c:url value="admin?action=editCategory">
                                      <c:param name="CategoryId" value="${category.getCategoryId()}"/>
                                    <c:param name="categorySelected" value="${category.getCategoryName()}"/>
                                    </c:url>"> Edit Category </a> </td>
    
                    </tr>

                </c:forEach>   
            </table> 
        </form>
            </c:when>
                       <c:when test="${action == 'editCategory'}">
                           <h2>Edit Category</h2>
            <form  action="" method = "post">
             
               Category Name: <input type="text" name="categoryName" value=${selectedCategory.getCategoryId()}><br>

                <br>
                <input type="hidden" name="action" value="edit" >
                <input type="submit" value="update">
                
                

            </form >
                       </c:when>
           
                            <c:when test="${action == 'addCategory'}">
                                <form  action="" method = "post">
                                Add new Name: <input type="text" name="newCategory"><br>
                                <input type="hidden" name="action" value="AddCategory" >
                <input type="submit" value="add">
                </form>
                            </c:when>
                       
        </c:choose> 
    </body>
</html>