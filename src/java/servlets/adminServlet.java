
package servlets;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Category;
import models.Item;
import models.Role;
import models.User;
import services.CategoryService;
import services.ItemService;
import services.RoleService;
import services.UserService;

/**
 *
 * @author Hanin
 */
public class adminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         
         HttpSession session = request.getSession();
        
         String action = request.getParameter("action");
         request.setAttribute("action",action);
          UserService user_service = new UserService();
          ItemService item_service = new ItemService(); 
          CategoryService category_service = new CategoryService();

//         if (action.equals("create")){
//             getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response); 
//         }
         if ( action.equals("edit")) {
            try {
                String email = request.getParameter("userEmail");
                 request.setAttribute("email", email);
                User user = user_service.getUser(email);
                request.setAttribute("selectedUser", user);
                 
                     
                
            } catch (Exception ex) {
                Logger.getLogger(adminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
          
           
}
        if ( action.equals("editCategory")) {
            try {
                String CategoryId = request.getParameter("CategoryId");
                 request.setAttribute("CategoryId", CategoryId);
                Category category = category_service.getCategory(Integer.parseInt(CategoryId));
                request.setAttribute("category", category);
                 
                     
                
            } catch (Exception ex) {
                Logger.getLogger(adminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
          
           
}
         else if (action.equals("delete")) {
            try {
                String email = request.getParameter("userEmail");
                request.setAttribute("email", email);
                user_service.deleteItemsUser(email);
                  user_service.delete(email);

            } catch (Exception ex) {
                Logger.getLogger(adminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         else if (action.equals("deleteItem")) {
            try {
                String itemId = request.getParameter("itemId");
                
                request.setAttribute("itemId", itemId);
                  item_service.delete(Integer.parseInt(itemId));
                 
            } catch (Exception ex) {
                Logger.getLogger(adminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
          getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          HttpSession session = request.getSession();
          ItemService item_service = new ItemService(); 
          CategoryService category_service = new CategoryService();
            UserService user_service = new UserService();
             String email = request.getParameter("email");
             request.setAttribute("email", email);
              String firstName = request.getParameter("firstName");
              request.setAttribute("firstName", firstName);
              String lastName = request.getParameter("lastName");
              request.setAttribute("lastName", lastName);
             String status = request.getParameter("status");
              request.setAttribute("status", status);
              String password = request.getParameter("password"); 
              request.setAttribute("password", password);
               String UserEditEmail = request.getParameter("userEmail");
                request.setAttribute("UserEditEmail", UserEditEmail);
                  boolean newUserStatus = false;
                  User userUpdated = null ;
                 
               int id = 0;
          String msg = ""; 
          User newUser = null; 
           Role newRole= null;
           String action = request.getParameter("action");
         request.setAttribute("action",action);
        
        RoleService user_role = new RoleService();
         List<User> users = null;
         List <Role> roles = null;
         List <Item>items = null; 
         List <Category> categories = null; 
         
              String role = request.getParameter("role");
              request.setAttribute("role", role);
              if (role != null){
        if (role.equals("system admin")) {
            id = 1;
        } else {
            id = 2;
        }
          newRole = new Role(id, role);
        request.setAttribute("newRole", newRole);
              }
              if (status != null){
        if (status.equals("Active")){
            newUserStatus = true; 
        }
              }
          if (action.equals("view")){
               try {
           

            users = user_service.getAll();
            roles = user_role.getAll();
            request.setAttribute("users", users);
            request.setAttribute("roles", roles);
            } catch (Exception ex) {
            Logger.getLogger(adminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
          if (action.equals("create")){
              User user = null;
              try{
            user = user_service.getUser(email);  
            if (user == null){
              
            newUser = new User (email, newUserStatus, firstName,lastName,password, newRole);
              user_service.insert(newUser);
              msg ="user created"; 
               request.setAttribute("msg", msg); 
               action = null; 
            }
          } catch (Exception ex) {
                Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
          // new user == null
          if (newUser == null){
          if (user != null){
              msg = "user exist";
              request.setAttribute("msg", msg); 
          }
          }

    }
          if (action != null && action.equals("edit")) {
            try {
               
               userUpdated = new User(UserEditEmail,newUserStatus ,firstName,lastName,password, newRole );
                request.setAttribute("userUpdated", userUpdated);
                 user_service.update(userUpdated);
//                 response.sendRedirect("/");
//                 return; 

            } catch (Exception ex) {
                Logger.getLogger(adminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

     getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
}
          if (action!= null && action.equals("viewAllCategory")){
            
              
              try { 
                  
                 categories = category_service.getAll();
                  
                   session.setAttribute("categories", categories);
              } catch (Exception ex) {
                  Logger.getLogger(adminServlet.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
              if (action!= null && action.equals("editCategory")){
                  String CategoryId = request.getParameter("CategoryId"); 
                     String newCategory =   request.getParameter("newCategory");
                     String  categoryName = request.getParameter("categoryName");
                     request.setAttribute("newCategory", newCategory);
                     request.setAttribute("categoryName", categoryName);
                     request.setAttribute("CategoryId", CategoryId);
                     Category updatedCategory = new Category(Integer.parseInt(CategoryId),categoryName);
              
              try { 
                  category_service.insert(newCategory);
                   category_service.update(updatedCategory);
              } catch (Exception ex) {
                  Logger.getLogger(adminServlet.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
          if (action!= null && action.equals("ViewItem")){
            
              
              try { 
                  
                    userUpdated =   user_service.getUser(email);
                  items = user_service.getAllItem(userUpdated);
                   session.setAttribute("items", items);
              } catch (Exception ex) {
                  Logger.getLogger(adminServlet.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
                   //   edit item
//            if (action!= null && action.equals("editItem")){
//            
//              
//              try { 
//                  Item itemUpdated = new Item ()
//                    Item itemUpdated = item_service.getItem("itemSelected");
//                  items = user_service.getAllItem(userUpdated);
//                   session.setAttribute("items", items);
//              } catch (Exception ex) {
//                  Logger.getLogger(adminServlet.class.getName()).log(Level.SEVERE, null, ex);
//              }
//          }
            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
}
}