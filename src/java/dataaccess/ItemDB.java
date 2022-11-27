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
import models.User;

/**
 *
 * @author Hanin
 */
public class ItemDB {
     public List<Item> getAll() throws Exception {
         UserDB userdb = new UserDB();
         CategoryDB categorydb = new CategoryDB();
        List<Item> items = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        String sql = "SELECT item_id,category, item_name, price, owner FROM item,category  WHERE  category = category_id; ";

try {
            ps = con.prepareStatement(sql);
            result = ps.executeQuery();
            while (result.next()) {
                int id = result.getInt(1);
                int categoryid = result.getInt(2);
                String item_name = result.getString(3);
                double  price = result.getDouble(4);
                String owner = result.getString(5);
                
               Category category = categorydb.getCategory(id);
                User user = userdb.getUser(owner);
                Item item = new Item(id, category, item_name, price, user);
                items.add(item);
            }
        } finally {
            DBUtil.closeResultSet(result);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }  
        return items;
    }
     public Item getItem(int id ) throws Exception {
         Item item = null ; 
         UserDB userdb = new UserDB();
         CategoryDB categorydb = new CategoryDB();
         ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        String sql = "SELECT item_id,category, item_name, price, owner FROM item   WHERE  item_id =?; ";
        try {
             ps = con.prepareStatement(sql);
             ps.setInt(1, id);
            result = ps.executeQuery();
            if (result.next()) {
                 int categoryid = result.getInt(2);
                String item_name = result.getString(3);
                double  price = result.getDouble(4);
                String password = result.getString(5);
                String owner = result.getString(5);
               Category category = categorydb.getCategory(categoryid);
                User user = userdb.getUser(owner);
                item = new Item(id,category, item_name, price, user);   
        }
     } 
        finally {
            DBUtil.closeResultSet(result);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        } 
        return item; 
}
     
  public void insert(Item item) throws Exception{
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "Insert into item (item_id,category, item_name, price, owner ) values (?, ?, ?, ?, ?)";
        try{
            ps = con.prepareStatement(sql); 
            ps.setInt(1,item.getItemId());
            ps.setInt(2,item.getCategory().getCategoryId());
            ps.setString(3,item.getItemName()); 
            ps.setDouble(4,item.getPrice()); 
             ps.setString(5,item.getUser().getEmail()); 
            ps.executeUpdate(); 
        }finally {
             DBUtil.closePreparedStatement(ps);
             cp.freeConnection(con);
        }
        
  } 
public void update(Item item) throws Exception{
     ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        
        String sql = "update item set category =?, item_name =?, price=?, owner=? where item_id = ?";
         try{
            ps = con.prepareStatement(sql);
             
            ps.setInt(1,item.getCategory().getCategoryId());
           ps.setString(2,item.getItemName()); 
            ps.setDouble(3,item.getPrice()); 
             ps.setString(4,item.getUser().getEmail());
            ps.setInt(5,item.getItemId());            
            ps.executeUpdate(); 
} finally {
             DBUtil.closePreparedStatement(ps);
             cp.freeConnection(con);
        }
} 

public void delete(Item item) throws Exception {
      ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "Delete from item where item_id = ?";
         try{
            ps = con.prepareStatement(sql);
        ps.setInt(1,item.getItemId()); 
            ps.executeUpdate(); 
}
 finally {
             DBUtil.closePreparedStatement(ps);
             cp.freeConnection(con);
        }
}
}


