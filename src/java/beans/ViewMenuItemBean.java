/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import facades.MenuItemRatingFacade;
import facades.MenuItemFacade;
import facades.RestaurantFacade;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import models.RatingItem;
import models.MenuItem;
import models.Restaurant;

/**
 *
 * @author Can Jee
 */
@ManagedBean
@SessionScoped
public class ViewMenuItemBean extends BaseBean{
    
    @ManagedProperty(value="#{menuItemFacade}")
    MenuItemFacade menuItemFacade;
    
    @ManagedProperty(value="#{restaurantFacade}")
    RestaurantFacade restaurantFacade;
    
    private String orderBy = "name";
    private boolean ascending = true;
    private String itemName;
    private String restaurantName;
    private int rating;
    private String comments;
    private List<String> itemNames;
    private List<MenuItem> menuItems;
    private Restaurant restaurant;
    private int menuItemCount;
    
    public void orderedByChanged (ValueChangeEvent event){
        orderBy = event.getNewValue().toString();
        getMenuItems();
    }
    
    public void ascendingChanged (ValueChangeEvent event){
        String test = event.getNewValue().toString();
        if (test.equals("true"))
            ascending = true;
        else
            ascending = false;
        getMenuItems();
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }

    public int getMenuItemCount() {
        getMenuItems();
        if (menuItems == null)
           menuItemCount = 0;
        else
            menuItemCount = menuItems.size();
        return menuItemCount;
    }

    public void setMenuItemCount(int menuItemCount) {
        this.menuItemCount = menuItemCount;
    }
    
    public void viewRestaurantMenuItems(Restaurant restaurant) {
        this.restaurant = restaurant;
        restaurantName = restaurant.getName();
        menuItems = restaurant.getMenuitem();
        
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
        context.redirect(context.getRequestContextPath() + "/view_restaurant_menuitems.xhtml");
        } catch (Exception e) {}
    }

    public RestaurantFacade getRestaurantFacade() {
        return restaurantFacade;
    }

    public void setRestaurantFacade(RestaurantFacade restaurantFacade) {
        this.restaurantFacade = restaurantFacade;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Restaurant getRestaurant() {
        return restaurantFacade.findByRestaurantName(restaurantName, em);
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<MenuItem> getMenuItems() {
        menuItems = menuItemFacade.getByMenuItemsByRestaurant(getRestaurant(), orderBy, ascending, em);
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
    
    public void valueChanged (ValueChangeEvent event){
        restaurantName = event.getNewValue().toString();
    }

    public MenuItemFacade getMenuItemFacade() {
        return menuItemFacade;
    }

    public void setMenuItemFacade(MenuItemFacade menuItemFacade) {
        this.menuItemFacade = menuItemFacade;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public List<String> getItemNames() {
        itemNames = new ArrayList<String>();
        List<MenuItem> items = getMenuItems();
        for (MenuItem item : items) {
            itemNames.add(item.getName());
        }
        return itemNames;
    }

    public void setItemNames(List<String> itemNames) {
        this.itemNames = itemNames;
    }
    
    
    
}
