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
        return locationFacade.getByLocationsByRestaurant(getRestaurant(), em);
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
