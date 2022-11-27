
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
public class homeServlet extends HttpServlet {

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                 HttpSession session = request.getSession();
               String action = request.getParameter("action");
                // String action = (String)session.getAttribute("action");
         request.setAttribute("action",action);
         String msg; 
                 UserService user_service = new UserService();
                  ItemService item_service = new ItemService();
                  CategoryService category_service = new CategoryService(); 
                 String email = (String)session.getAttribute("email");
                 User user = null; 
                  List<Item> items; 
                 List<Category> Categories; 
                 if(action != null && action.equals("login")){
                      try {
            user = user_service.getUser(email);
            session.setAttribute("user",user);
        } catch (Exception ex) {
            Logger.getLogger(homeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
                 }
if (action != null  && action.equals("viewAllItems")){
     try{
           user = user_service.getUser(email);      
         items = user_service.getAllItem(user);
         
         request.setAttribute("items", items);
          if (items.size()<=0){
              msg = "there are no inventory items"; 
               request.setAttribute("msg", msg);
          }       
        getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp")
                .forward(request, response);
        return; 
    }catch (Exception ex) {
            Logger.getLogger(adminServlet.class.getName()).log(Level.SEVERE, null, ex);
        
    
}
}
                 
       
        if (action != null && action.equals("editItem")){
            String itemSelected = request.getParameter("SelectedId");
             session.setAttribute("itemSelected", itemSelected);
             String categorySelected = request.getParameter("SelectedCategoryid");
             session.setAttribute("categorySelected", categorySelected);
             
                     try {
                          Categories = category_service.getAll(); 
                          request.setAttribute("Categories", Categories);
                         Item item = item_service.getItem(Integer.parseInt(itemSelected));
                         request.setAttribute("item", item);
                         String itemName = item.getItemName(); 
                        Category category = category_service.getCategory(Integer.parseInt(categorySelected));
                        String categoryName = category.getCategoryName(); 
                         request.setAttribute("categoryName", categoryName);
                          request.setAttribute("itemName", itemName);
                          getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp")
                .forward(request, response);
                          return;
                     } catch (Exception ex) {
                         Logger.getLogger(homeServlet.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
        }
           else if (action != null && action.equals("deleteItem")) {
            try {
                String itemId = request.getParameter("SelectedId");
                
                request.setAttribute("itemId", itemId);
                  item_service.delete(Integer.parseInt(itemId));
                  getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp")
                .forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(adminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }if (action != null &&  action.equals("AddItem")){
                     try { 
                         Categories = category_service.getAll();
                         request.setAttribute("Categories", Categories);
                     } catch (Exception ex) {
                         Logger.getLogger(homeServlet.class.getName()).log(Level.SEVERE, null, ex);
                     }
                          
            getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp")
                .forward(request, response);
        }
       
         getServletContext().getRequestDispatcher("/WEB-INF/home.jsp")
                .forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserService user_service = new UserService();
         ItemService item_service = new ItemService();
        CategoryService category_service = new CategoryService(); 
        String email = (String)session.getAttribute("email");
         String action = request.getParameter("action");
          request.setAttribute("action",action);
            String firstName = request.getParameter("firstName");
              request.setAttribute("firstName", firstName);
              String lastName = request.getParameter("lastName");
              request.setAttribute("lastName", lastName);
             String status = request.getParameter("status");
              request.setAttribute("status", status);
              String password = request.getParameter("password"); 
              request.setAttribute("password", password);
               Role newRole = new Role(2, "regular user");
                request.setAttribute("newRole", newRole);
                List<Item>items; 
                Item item ; 
                String msg =""; 
//                change statuse when user edit information
                    Boolean newStatus = false;
   
                         if (status != null && status.equals("Active"))
                                {
                                    newStatus = true; 
                                 }
//         User userUpdated = null; 

if (action.equals("editAccount")){
    try{
          User user = new User(email,newStatus ,firstName,lastName,password, newRole );
         session.setAttribute("user", user);
         user = user_service.getUser(email);
         request.setAttribute("user", user);
                 
         getServletContext().getRequestDispatcher("/WEB-INF/manageAccount.jsp")
                .forward(request, response);
        
    }catch (Exception ex) {
            Logger.getLogger(adminServlet.class.getName()).log(Level.SEVERE, null, ex);
        
    
}

}
if (action.equals("update")){
                         User user = new User(email,newStatus ,firstName,lastName,password, newRole );
         session.setAttribute("user", user);
               request.setAttribute("newStatus", newStatus);
               user = new User(email,newStatus ,firstName,lastName,password, newRole );
                request.setAttribute("user", user);
            try {
                user_service.update(user);
                if (newStatus.equals(false)){
                    session.invalidate();
                    getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request, response);
                    
                }
            } catch (Exception ex) {
                Logger.getLogger(homeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
}

if(action.equals("back")){
   getServletContext().getRequestDispatcher("/WEB-INF/home.jsp")
                .forward(request, response); 
}
   if (action.equals("editItem")){
                User user = new User(email,newStatus ,firstName,lastName,password, newRole );
                    session.setAttribute("user", user);
                    String itemId = (String)session.getAttribute("itemSelected");
//                     String itemId = request.getParameter("itemSelected"); 
                     String categoryName =   request.getParameter("category");
                     String  itemName = request.getParameter("itemName");
                     request.setAttribute("categoryName", categoryName);
                    String  price = request.getParameter("price");
                    request.setAttribute("price", price);
                    double priceD = Double.parseDouble(price);
                    int itemInt = Integer.parseInt(itemId);
                   
                      request.setAttribute("priceD", priceD);
                     request.setAttribute("itemName", itemName);
                     request.setAttribute("itemId", itemId);

              try { 
                  Category  category = category_service.getCategory(categoryName); 
                 request.setAttribute("category", category);
                item = new Item (itemInt,category,itemName,priceD,user); 
                 item_service.update(item);
                  
              } catch (Exception ex) {
                  Logger.getLogger(adminServlet.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
   if (action.equals("AddItem")){
      String newItemName = request.getParameter("newItemName"); 
      String newCategory = request.getParameter("newCategory");
      String newPrice =  request.getParameter("newPrice");
        request.setAttribute("newPrice", newPrice);
      request.setAttribute("newCategory", newCategory);
      request.setAttribute("newItemName", newItemName);
       double priceD = Double.parseDouble(newPrice);
      request.setAttribute("priceD", priceD);
      if (newItemName == null || newItemName.equals("") ||priceD < 0){
          msg = "The item is not added to the list"; 
                request.setAttribute("msg", msg);
getServletContext().getRequestDispatcher("/WEB-INF/home.jsp")
                .forward(request, response);
return; 
      }
      
            try { 
                int id = 0; 
                         User user = new User(email,newStatus ,firstName,lastName,password, newRole );
         session.setAttribute("user", user);
                Category category = category_service.getCategory(newCategory);
                 request.setAttribute("category", category);
                 request.setAttribute("id", id);
                 item = new Item (id,category,newItemName,priceD,user); 
                 request.setAttribute("item", item);
                 item_service.insert(item);
            } catch (Exception ex) {
                Logger.getLogger(homeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
   }

getServletContext().getRequestDispatcher("/WEB-INF/home.jsp")
                .forward(request, response);
    }
}
