/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import facades.RestaurantFacade;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import models.Restaurant;

/**
 *
 * @author Can Jee
 */
@ManagedBean
@SessionScoped
public class ViewRestaurantBean extends BaseBean{
    
    @ManagedProperty(value="#{restaurantFacade}")
    RestaurantFacade restaurantFacade;
    
    
    private String orderBy = "name";
    private boolean ascending = true;
    private List<String> restaurantNames;
    private List<Restaurant> restaurants;
    private String name;
    private String type;
    private String url;
    private int restaurantCount;
    
    public void orderedByChanged (ValueChangeEvent event){
        orderBy = event.getNewValue().toString();
        sort();
    }
    
    public void ascendingChanged (ValueChangeEvent event){
        String test = event.getNewValue().toString();
        if (test.equals("true"))
            ascending = true;
        else
            ascending = false;
        sort();
    }

    public int getRestaurantCount() {
        if (restaurants == null)
            return 0;
        return restaurants.size();
    }

    public void setRestaurantCount(int restaurantCount) {
        this.restaurantCount = restaurantCount;
    }

    public RestaurantFacade getRestaurantFacade() {
        return restaurantFacade;
    }

    public void setRestaurantFacade(RestaurantFacade restaurantFacade) {
        this.restaurantFacade = restaurantFacade;
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

    public List<Restaurant> getRestaurants() {
        restaurants = restaurantFacade.getRestaurantList(orderBy, ascending, em);
        System.out.println(restaurants.size());
        return restaurants;
    }
    
    public void sort() {
        restaurants = restaurantFacade.getRestaurantList(orderBy, ascending, em);
    }
    
    public List<String> getRestaurantNames() {
        List<Restaurant> restaurants = getRestaurants();
        restaurantNames = new ArrayList<String>();
        for (Restaurant restaurant : restaurants){
            restaurantNames.add(restaurant.getName());
        }
        
        return restaurantNames;
    }

    public void setRestaurantNames(List<String> restaurantNames) {
        this.restaurantNames = restaurantNames;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
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
    
}
