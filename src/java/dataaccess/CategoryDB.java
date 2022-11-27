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

/**
 *
 * @author Hanin
 */
public class CategoryDB {
     public List<Category> getAll() throws Exception {
        List<Category> categories = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        String sql = "SELECT category_id,category_name from Category ";
        try {
            ps = con.prepareStatement(sql);
            result = ps.executeQuery();
            while (result.next()) {
                int category_id = result.getInt(1); 
                String category_name = result.getString(2);  
                Category category = new Category(category_id, category_name);
                categories.add(category);
            }
             } finally {
            DBUtil.closeResultSet(result);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }  
        return categories;
    }
          public Category getCategory(int id) throws Exception {
         Category category = null ; 
         ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        String sql = "SELECT category_id, category_name FROM  Category  WHERE  category_id =?; ";
        try {
             ps = con.prepareStatement(sql);
             ps.setInt(1, id);
            result = ps.executeQuery();
            if (result.next()) {
                String category_name = result.getString(2);
                 category = new Category(id, category_name);

                 
                
        }
     } 
        finally {
            DBUtil.closeResultSet(result);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        } 
        return category; 
}
 public Category getCategory(String name) throws Exception {
         Category category = null ; 
         ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        String sql = "SELECT category_id, category_name FROM  Category  WHERE  category_name =?; ";
        try {
             ps = con.prepareStatement(sql);
             ps.setString(1, name);
            result = ps.executeQuery();
            if (result.next()) {
                int id = result.getInt(1);
                 category = new Category(id,name);

                 
                
        }
     } 
        finally {
            DBUtil.closeResultSet(result);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        } 
        return category; 
}
  public void insert(String category) throws Exception{
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "Insert into category (category_name) values ( ?)";
        try{
            ps = con.prepareStatement(sql); 
//            ps.setInt(1,category.getCategoryId());
            ps.setString(1,category);
            ps.executeUpdate(); 
        }finally {
             DBUtil.closePreparedStatement(ps);
             cp.freeConnection(con);
        }
        
  } 
public void update(Category category) throws Exception{
     ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        
        String sql = "update category set category_name=? where category_id = ?";
         try{
            ps = con.prepareStatement(sql);
             
            ps.setString(1,category.getCategoryName());
            ps.setInt(2, category.getCategoryId());        
            ps.executeUpdate(); 
} finally {
             DBUtil.closePreparedStatement(ps);
             cp.freeConnection(con);
        }
} 
}
