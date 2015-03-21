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
            menuItem.setRestaurant(restaurant);
            em.merge(restaurant);
            em.merge(menuItem);
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
    
}
