
package servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
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
        try{
         user = user_service.getUser(email);   
        } catch (Exception ex) {
                Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        if (user!= null && user.getPassword().equals(password)){
        
        getServletContext().getRequestDispatcher("/WEB-INF/home.jsp")
                .forward(request, response);
        return;
    }
        else{
            String msg = "Wrong password or user name"; 
            request.setAttribute("msg", msg);
           getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response); 
        }


}
}
