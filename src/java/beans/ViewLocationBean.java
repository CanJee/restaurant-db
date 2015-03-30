/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import facades.LocationFacade;
import facades.RestaurantFacade;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import models.Location;
import models.Restaurant;

/**
 *
 * @author Alan
 */
@ManagedBean
@SessionScoped
public class ViewLocationBean extends BaseBean{
    
    @ManagedProperty(value="#{locationFacade}")
    LocationFacade locationFacade;
    
    @ManagedProperty(value="#{restaurantFacade}")
    RestaurantFacade restaurantFacade;
    
    private String orderBy = "province";
    private boolean ascending = true;
    private List<Location> locations;
    private List<String> restaurantStreetAddresses;
    private String restaurantName;
    private String streetname;
    private String province;
    private String city;
    private String postalcode;
    private Restaurant restaurant;
    private int locationCount;
    
    public void orderedByChanged (ValueChangeEvent event){
        orderBy = event.getNewValue().toString();
        getLocations();
    }
    
    public void ascendingChanged (ValueChangeEvent event){
        String test = event.getNewValue().toString();
        if (test.equals("true"))
            ascending = true;
        else
            ascending = false;
        System.out.println(ascending);
        getLocations();
    }

    public int getLocationCount() {
        getLocations();
        if (locations == null)
           locationCount = 0;
        else
            locationCount = locations.size();
        return locationCount;
    }

    public void setLocationCount(int locationCount) {
        this.locationCount = locationCount;
    }
    
    public void viewRestaurantLocations(Restaurant restaurant) {
        this.restaurant = restaurant;
        restaurantName = restaurant.getName();
        locations = restaurant.getLocation();
        
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
        context.redirect(context.getRequestContextPath() + "/view_restaurant_locations.xhtml");
        } catch (Exception e) {}
    }
    
    public void valueChanged (ValueChangeEvent event){
        restaurantName = event.getNewValue().toString();
    }
    
    public RestaurantFacade getRestaurantFacade() {
        return restaurantFacade;
    }

    public void setRestaurantFacade(RestaurantFacade restaurantFacade) {
        this.restaurantFacade = restaurantFacade;
    }

    public List<String> getRestaurantStreetAddresses() {
        restaurantStreetAddresses = new ArrayList<String>();
        List<Location> locations = getLocations();
        for (Location location : locations){
            restaurantStreetAddresses.add(location.getStreetaddress());
        }
        return restaurantStreetAddresses;
    }

    public void setRestaurantStreetAddresses(List<String> restaurantStreetAddresses) {
        this.restaurantStreetAddresses = restaurantStreetAddresses;
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

    public LocationFacade getLocationFacade() {
        return locationFacade;
    }

    public void setLocationFacade(LocationFacade locationFacade) {
        this.locationFacade = locationFacade;
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

    public List<Location> getLocations() {
        locations = locationFacade.getByLocationsByRestaurant(getRestaurant(), orderBy, ascending, em);
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public String getStreetname() {
        return streetname;
    }

    public void setStreetname(String streetname) {
        this.streetname = streetname;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }
    
}
