/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import static facades.BaseFacade.performQueryList;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import models.MenuItem;
import models.Restaurant;

/**
 *
 * @author Can Jee
 */
@ManagedBean
@SessionScoped
public class MenuItemFacade extends BaseFacade{
    
    public boolean addMenuItem (MenuItem menuItem, Restaurant restaurant) {
        
        try {
            
            utx.begin();
            List<MenuItem> menuItems = restaurant.getMenuitem();
            menuItems.add(menuItem);
            em.merge(restaurant);
            em.persist(menuItem);
            utx.commit();
            return true;
            
        } catch (Exception e) {
            return false;
        }
        
    }
    
    public boolean inRestaurantItemList (Restaurant restaurant, MenuItem menuItem) {
        
        boolean inList = false;
        List<MenuItem> resItems = restaurant.getMenuitem();
        for (MenuItem item : resItems) {
            if (item.equals(menuItem))
                inList = true;
        }
        
        return inList;
        
    }
    
    public MenuItem findByItemName(String name, EntityManager em) {
        Query query = em.createQuery("SELECT i FROM MenuItem i WHERE i.name = :name");
        query.setParameter("name", name);
        MenuItem result = performQuery(MenuItem.class, query);
        return result;
    } 
    
    public List<MenuItem> getByMenuItemsByRestaurant(Restaurant restaurant, EntityManager em) {
        Query query = em.createQuery("SELECT i FROM MenuItem i WHERE i.restaurant = :restaurant");
        query.setParameter("restaurant", restaurant);
        List<MenuItem> items = performQueryList(MenuItem.class, query);
        if( items == null ) {
            items = new ArrayList<MenuItem>();
        }

        return items;
    }
    
}
