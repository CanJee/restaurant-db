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
import facades.LocationFacade;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import models.Restaurant;
import models.Location;

/**
 *
 * @author Alan
 */

@ManagedBean
@SessionScoped
public class ViewRestaurantByLocation extends BaseBean{
    
    @ManagedProperty(value="#{restaurantFacade}")
    RestaurantFacade restaurantFacade;
    
    @ManagedProperty(value="#{locationFacade}")
    LocationFacade locationFacade;

    private String orderBy = "name";
    private boolean ascending = true;
    private List<Restaurant> restaurants;
    private List<Location> locations;
    private List<Object[]> resl;
    private String name;
    private String url;
    private String restaurantName;
    private String streetname;
    private String province;
    private String city = "Ottawa";
    private String postalcode;
    private int locationCount;
    
    public int getLocationCount(){
        return locations.size();
    }

    public void setLocationCount(int locationCount) {
        this.locationCount = locationCount;
    }
    
    public void viewRestaurants() {
        locations = locationFacade.getCityFromLocationList(orderBy, ascending, em);
        
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
        context.redirect(context.getRequestContextPath() + "/view_restaurant_by_location.xhtml");
        } catch (Exception e) {}
    }
    
/*    public List<Restaurant> getRestaurants(){
        restaurants = restaurantFacade.getRestaurantByLocation(city, orderBy, ascending, em);
        return restaurants;
    }*/
    
    public List<Object[]> getResl(){
        resl = restaurantFacade.getRestaurantByLocation(city, orderBy, ascending, em);
        return resl;
    }
    
    public void cityChanged (ValueChangeEvent event){
        city = event.getNewValue().toString();
        getResl();
    }
    
    public void orderedByChanged (ValueChangeEvent event){
        orderBy = event.getNewValue().toString();
        getResl();
    }
    
    public void ascendingChanged (ValueChangeEvent event){
        String test = event.getNewValue().toString();
        if (test.equals("true"))
            ascending = true;
        else
            ascending = false;
        getResl();
    }

    public RestaurantFacade getRestaurantFacade() {
        return restaurantFacade;
    }

    public void setRestaurantFacade(RestaurantFacade restaurantFacade) {
        this.restaurantFacade = restaurantFacade;
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

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
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
