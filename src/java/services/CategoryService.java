package services;

import dataaccess.CategoryDB;
import java.util.List;
import models.Category;

/**
 *
 * @author Hanin
 */
public class CategoryService {
    public List<Category> getAll() throws Exception {
        CategoryDB categoryDB = new CategoryDB(); 
        List<Category> categories = categoryDB.getAll();
        return categories;
    }
     public Category getCategory(int id) throws Exception {
           CategoryDB categoryDB = new CategoryDB();
           Category category = categoryDB.getCategory(id);
           return category;
     }
     public void insert(String category) throws Exception{
         CategoryDB categoryDB = new CategoryDB();
         categoryDB.insert(category);
     }
     public void update(Category category) throws Exception{
         CategoryDB categoryDB = new CategoryDB();
         categoryDB.update(category);
     }
      public Category getCategory(String name) throws Exception {
           CategoryDB categoryDB = new CategoryDB();
           Category category = categoryDB.getCategory(name);
           return category;
      }
}
