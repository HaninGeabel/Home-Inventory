/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.Category;
import models.Item;
import models.Role;
import models.User;

/**
 *
 * @author Hanin
 */
public class UserDB {
    public List<User> getAll() throws Exception {
        List<User> users = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        String sql = "SELECT email,active, first_name, last_name, password, role_id, role_name FROM User, Role  WHERE  role = role_id; ";

try {
            ps = con.prepareStatement(sql);
            result = ps.executeQuery();
            while (result.next()) {
                String email = result.getString(1);
                boolean active = result.getBoolean(2);
                String firstName = result.getString(3);
                String lastName = result.getString(4);
                String password = result.getString(5);
                int role_id = result.getInt(6);   
                String role_name = result.getString(7); 
                Role role = new Role(role_id, role_name);
                User user = new User(email,active, firstName, lastName, password, role);
                users.add(user);
            }
        } finally {
            DBUtil.closeResultSet(result);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }  
        return users;
    }
     public User getUser(String email) throws Exception {
         User user = null ; 
         ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        String sql = "SELECT email,active, first_name, last_name, password, role_id, role_name FROM User, Role  WHERE  role = role_id AND email =?; ";
        try {
             ps = con.prepareStatement(sql);
             ps.setString(1, email);
            result = ps.executeQuery();
            if (result.next()) {
                 boolean active = result.getBoolean(2);
                String firstName = result.getString(3);
                String lastName = result.getString(4);
                String password = result.getString(5);
                int role_id = result.getInt(6);   
                String role_name = result.getString(7); 
                Role role = new Role(role_id, role_name);
                 user = new User(email,active, firstName, lastName, password, role);
                
        }
     } 
        finally {
            DBUtil.closeResultSet(result);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        } 
        return user; 
}
  public void insert(User user) throws Exception{
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "Insert into user (email,active, first_name, last_name, password, role) values (?, ?, ?, ?, ?,?)";
        try{
            ps = con.prepareStatement(sql); 
            ps.setString(1,user.getEmail());
            ps.setBoolean(2,user.isActive());
            ps.setString(3,user.getFirstName());
            ps.setString(4,user.getLastName()); 
            ps.setString(5,user.getPassword()); 
            ps.setInt(6,user.getRole().getRoleId()); 
            ps.executeUpdate(); 
        }finally {
             DBUtil.closePreparedStatement(ps);
             cp.freeConnection(con);
        }
        
  } 
public void update(User user) throws Exception{
     ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        
        String sql = "update user set first_name =?  ,active =?, last_name = ? ,password =? , role =? where email = ?";
         try{
            ps = con.prepareStatement(sql);
             
            ps.setString(1,user.getFirstName());
            ps.setBoolean(2,user.isActive());
            ps.setString(3,user.getLastName());
              ps.setString(4,user.getPassword());
            ps.setInt(5, user.getRole().getRoleId());
            ps.setString(6,user.getEmail());            
            ps.executeUpdate(); 
} finally {
             DBUtil.closePreparedStatement(ps);
             cp.freeConnection(con);
        }
} 

public void delete(User user) throws Exception {
      ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "Delete from user where email = ?";
         try{
            ps = con.prepareStatement(sql);
        ps.setString(1,user.getEmail()); 
            ps.executeUpdate(); 
}
 finally {
             DBUtil.closePreparedStatement(ps);
             cp.freeConnection(con);
        }
}
public List<Item> getAllItem(User user)throws Exception {
     List<Item> items = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        String sql = "SELECT email,item_id ,category ,category_name, item_name, price, email FROM Item, User,Category WHERE  owner = email and category = category_id and email = ?; ";
    try {
            ps = con.prepareStatement(sql);
             ps.setString(1, user.getEmail());
            result = ps.executeQuery();
            while (result.next()) {
                int id = result.getInt(2);
                int categoryId = result.getInt(3);
                String categoryName = result.getString(4);
                String itemName = result.getString(5);
                double price = result.getDouble(6);

                Category category = new Category(categoryId,categoryName); 
                Item item = new Item(id,category, itemName, price, user);
                items.add(item);
            }
        } finally {
            DBUtil.closeResultSet(result);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }  
       
    
 return items;    
}
public void deleteItemsUser(String email) throws Exception {
      ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "Delete from Item where owner = ?";
         try{
            ps = con.prepareStatement(sql);
        ps.setString(1,email); 
            ps.executeUpdate(); 
}
 finally {
             DBUtil.closePreparedStatement(ps);
             cp.freeConnection(con);
        }
}
}
