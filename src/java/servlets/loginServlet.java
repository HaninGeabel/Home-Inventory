
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
       
      

       getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request, response);
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String msg; 
          HttpSession session = request.getSession();
          
           UserService user_service = new UserService();
        RoleService user_role = new RoleService();
         List<User> users;
         User user = null;
         
          String email = request.getParameter("email");
           request.setAttribute("email", email);
           String firstName = request.getParameter("firstName");
              request.setAttribute("firstName", firstName);
              String lastName = request.getParameter("lastName");
              request.setAttribute("lastName", lastName);
              String password = request.getParameter("password"); 
              request.setAttribute("password", password);
              String address = request.getParameter("address");
              request.setAttribute("address", address);
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
          
      getServletContext().getRequestDispatcher("/WEB-INF/home.jsp")
                    .forward(request,response);
    }

  
}
