
package services;

import dataaccess.UserDB;
import java.util.List;
import models.Item;
import models.User;

/**
 *
 * @author Hanin
 */
public class UserService {
     public List<User> getAll() throws Exception {
        UserDB userDB = new UserDB(); 
        List<User> users = userDB.getAll(); 
        return users; 
        
    } 
    public User getUser(String Email) throws Exception{
        UserDB userDB = new UserDB();  
        User user = userDB.getUser(Email); 
        return user; 
    }
    public void insert(User user)throws Exception{
       UserDB userDB = new UserDB(); 
       userDB.insert(user);
        
    }
     public void update(User user)throws Exception{
//          User user = new User(); 
       UserDB userDB = new UserDB(); 
       userDB.update(user);
        
    }
      public void delete(String email)throws Exception{
       User user = new User(); 
       user.setEmail(email); 
       UserDB userDB = new UserDB(); 
       userDB.delete(user);
        
    }
        public List<Item> getAllItem(User user) throws Exception {
        UserDB userDB = new UserDB(); 
        List<Item> items = userDB.getAllItem(user); 
        return items; 
        
    } 
        public void deleteItemsUser(String email) throws Exception {
            UserDB userDB = new UserDB(); 
            userDB.deleteItemsUser(email);
        }
}
