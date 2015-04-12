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
import models.Location;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Alan
 */
@ManagedBean
@RequestScoped
public class AddRestaurantBean extends BaseBean{
    
    @ManagedProperty(value="#{restaurantFacade}")
    RestaurantFacade restaurantFacade;
    
    private String name;
    private String type;
    private String url;
    private List<Location> location;
    private List<MenuItem> menuitem;
    private boolean isError = false;
    private String status;
    
    
    public void addRestaurant() {
        
        Restaurant restaurant = new Restaurant();
        
        restaurant.setName(name);
        restaurant.setType(type);
        restaurant.setUrl(url);
        restaurant.setLocation(location);
        restaurant.setMenuitem(menuitem);
        
        List<Restaurant> currentRestaurant = restaurantFacade.getRestaurantList(name, true, em);
        boolean test = false;
        
        for(int i = 0; i <currentRestaurant.size(); i++){
            if (currentRestaurant.get(i).getName().equals(name)) {test = true;}
        }
        
        if (test) {
            isError = true;
            status = "This restaurant already exists";
        }
        else {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            if (restaurantFacade.addRestaurant(restaurant)){
                try {
                    context.redirect(context.getRequestContextPath() +
                        "/owner/add_restaurant_successful.xhtml");
                } catch (Exception e) {}
            }
        }
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Location> getLocation() {
        return location;
    }

    public void setLocation(List<Location> location) {
        this.location = location;
    }

    public List<MenuItem> getMenuitem() {
        return menuitem;
    }

    public void setMenuitem(List<MenuItem> menuitem) {
        this.menuitem = menuitem;
    }

    public boolean isIsError() {
        return isError;
    }

    public void setIsError(boolean isError) {
        this.isError = isError;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
