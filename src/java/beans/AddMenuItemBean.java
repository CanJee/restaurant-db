/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import facades.RestaurantFacade;
import facades.MenuItemFacade;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import models.Owner;
import models.MenuItem;
import models.Restaurant;

/**
 *
 * @author Can Jee
 */
@ManagedBean
@RequestScoped
public class AddMenuItemBean extends BaseBean{
    
    @ManagedProperty(value="#{restaurantFacade}")
    RestaurantFacade restaurantFacade;
    
    @ManagedProperty(value="#{menuItemFacade}")
    MenuItemFacade menuItemFacade;
    
    private String name;
    private String category;
    private String type;
    private String description;
    private String restaurantName;
    private String status;
    private boolean isError = false;
    private double price;
    private Restaurant restaurant;

    public MenuItemFacade getMenuItemFacade() {
        return menuItemFacade;
    }

    public void setMenuItemFacade(MenuItemFacade menuItemFacade) {
        this.menuItemFacade = menuItemFacade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public boolean isIsError() {
        return isError;
    }

    public void setIsError(boolean isError) {
        this.isError = isError;
    }

    public RestaurantFacade getRestaurantFacade() {
        return restaurantFacade;
    }

    public void setRestaurantFacade(RestaurantFacade restaurantFacade) {
        this.restaurantFacade = restaurantFacade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    
    public void addMenuItem() {
        
        Restaurant res = restaurantFacade.findByRestaurantName(restaurantName, em);
        System.out.println(res);
        MenuItem menuItem = new MenuItem();
        Owner resOwner = sessionBean.getOwner();
        
        menuItem.setName(name);
        menuItem.setType(type);
        menuItem.setCategory(category);
        menuItem.setPrice(price);
        menuItem.setRestaurant(restaurant);
        menuItem.setDescription(description);
        System.out.println(menuItem);
        boolean inRestaurantMenuItemList = menuItemFacade.inRestaurantItemList(res, menuItem);
        System.out.println(inRestaurantMenuItemList);
        boolean inOwnerLocationList = restaurantFacade.inOwnerRestaurantList(resOwner, res);
        
        if (!inOwnerLocationList) {
            isError = true;
            status = "You cannot add a menu item for a restaurant you don't own";
        }
        else if (inRestaurantMenuItemList) {
            isError = true;
            status = "Item with this name already exists for this restaurant";
        }
        else {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            if (menuItemFacade.addMenuItem(menuItem, res)){
                try {
                    context.redirect(context.getRequestContextPath() +
                        "/owner/add_menuitem_successful.xhtml");
                } catch (Exception e) {}
            }
        }
        
    }
    
}
