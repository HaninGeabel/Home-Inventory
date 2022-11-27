
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
import models.Role;
import models.User;
import services.RoleService;
import services.UserService;

/**
 *
 * @author Hanin
 */
public class loginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
      
        String action = request.getParameter("action");
        request.setAttribute("action", action);
        if (action == null){
             session.invalidate();
        }
        if (action!= null && action.equals("back")){
            action = "create"; 
            request.setAttribute("action", action);
             response.sendRedirect("/");
             return;
        }
        if (action != null && action.equals("create")){
           getServletContext().getRequestDispatcher("/WEB-INF/createAccount.jsp")
                .forward(request, response); 
        }
       
      

       getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request, response);
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String msg; 
          HttpSession session = request.getSession();
          String action = request.getParameter("action");
          request.setAttribute("action", action);
          session.setAttribute("action", action);
          RoleService user_role = new RoleService();
           User user = null;
            UserService user_service = new UserService();
        
         List<User> users;
     
          if (action.equals("login")){
          
        //validate if user exist and if yes,check if it is manager or user  
        
        String email = request.getParameter("email");
         session.setAttribute("email", email);
        String password = request.getParameter("password");
        
//        List<User> users = null;
         List <Role> roles = null; 
//         int id = 0;
        try{
         user = user_service.getUser(email); 
         session.setAttribute("user", user);
          roles = user_role.getAll(); 
        } catch (Exception ex) {
                Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        if (user!= null && user.getPassword().equals(password)){
            if (user.isActive() == true){
                session.setAttribute("roles", roles);
             int userRole = user.getRole().getRoleId();
             if (userRole == 1){
                 response.sendRedirect("admin");
                 return; 

             }if (userRole == 2 ){
                 
        
        response.sendRedirect("home");
        return; 
            }
           
    
    }if (user.isActive() == false){
         msg = "Your account has deactivated"; 
        request.setAttribute("msg", msg);
         session.invalidate();
           getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response); 
    }
        }   else{
             msg = "Wrong password or user name"; 
            request.setAttribute("msg", msg);
             session.invalidate();
           getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response); 
           return; 
        }
          }
         if (action.equals("create")){
         
          String email = request.getParameter("email");
           request.setAttribute("email", email);
           String firstName = request.getParameter("firstName");
              session.setAttribute("firstName", firstName);
              String lastName = request.getParameter("lastName");
              session.setAttribute("lastName", lastName);
              String password = request.getParameter("password"); 
              request.setAttribute("password", password);
              
              int roleId = 2; 
          try{
            user = user_service.getUser(email);  
            if (user == null){
              
              request.setAttribute("roleId", roleId);
              String roleName = "regular user"; 
              request.setAttribute("roleName", roleName);
              Role role = new Role(roleId, roleName);
              User newUser = new User (email, true, firstName,lastName,password, role);
              user_service.insert(newUser);
              
            }
          } catch (Exception ex) {
                Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
          
          if (user != null){
              msg = "user exist";
              request.setAttribute("msg", msg);
               session.invalidate(); 
              getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request, response);
              return; 
          }
         msg = "You account has been created successfully ";
          request.setAttribute("msg", msg);
      getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                    .forward(request,response);
    }
         if (action.equals("accountCreated")){
             action = ""; 
             request.setAttribute("action",action);
             getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                    .forward(request,response);
             
         }

    }
}
