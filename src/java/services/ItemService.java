package services;

import dataaccess.ItemDB;
import java.util.List;
import models.Item;

/**
 *
 * @author Hanin
 */
public class ItemService {
     public List<Item> getAll() throws Exception {
        ItemDB itemDB = new ItemDB(); 
        List<Item> items = itemDB.getAll(); 
        return items; 
        
    } 
    public Item getItem(int itemID) throws Exception{
        ItemDB itemDB = new ItemDB();  
        Item item = itemDB.getItem(itemID); 
        return item; 
    }
    public void insert(Item item)throws Exception{
        ItemDB itemDB = new ItemDB();  
       itemDB.insert(item);
        
    }
     public void update(Item item)throws Exception{

       ItemDB itemDB = new ItemDB(); 
       itemDB.update(item);
        
    }
      public void delete(int id)throws Exception{
       Item item = new Item(); 
       item.setItemId(id); 
       ItemDB itemDB = new ItemDB(); 
       itemDB.delete(item);
        
    }
}
