
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
public class homeServlet extends HttpServlet {

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         getServletContext().getRequestDispatcher("/WEB-INF/home.jsp")
                .forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
         UserService user_service = new UserService();
           User user = null;
        //validate if user exist and if yes,check if it is manager or user  
        String logout = request.getParameter("action");
        String email = request.getParameter("email");
         session.setAttribute("email", email);
        String password = request.getParameter("password");
        RoleService user_role = new RoleService();
//        List<User> users = null;
         List <Role> roles = null; 
//         int id = 0;
        try{
         user = user_service.getUser(email); 
          roles = user_role.getAll(); 
        } catch (Exception ex) {
                Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        if (user!= null && user.getPassword().equals(password)){
           
           session.setAttribute("roles", roles);
             int userRole = user.getRole().getRoleId();
             if (userRole == 1){
                getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp")
                .forward(request, response);
                 return; 

             }
        
        getServletContext().getRequestDispatcher("/WEB-INF/home.jsp")
                .forward(request, response);
    
    }
        else{
            String msg = "Wrong password or user name"; 
            request.setAttribute("msg", msg);
           getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response); 
        }


}
}
