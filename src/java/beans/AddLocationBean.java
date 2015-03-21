/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import facades.LocationFacade;
import facades.RestaurantFacade;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import models.Location;
import models.Owner;
import models.Restaurant;

/**
 *
 * @author Can Jee
 */
@ManagedBean
@RequestScoped
public class AddLocationBean extends BaseBean{
    
    @ManagedProperty(value="#{restaurantFacade}")
    RestaurantFacade restaurantFacade;
    
    @ManagedProperty(value="#{locationFacade}")
    LocationFacade locationFacade;
    
    private String streetaddress;
    private String city;
    private String postalcode;
    private String province;
    private String country;
    private String status;
    private String restaurantName;
    private boolean isError = false;
    private Date opendate;
    private Owner owner;
    private Restaurant restaurant;

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

    public LocationFacade getLocationFacade() {
        return locationFacade;
    }

    public void setLocationFacade(LocationFacade locationFacade) {
        this.locationFacade = locationFacade;
    }

    public RestaurantFacade getRestaurantFacade() {
        return restaurantFacade;
    }

    public void setRestaurantFacade(RestaurantFacade restaurantFacade) {
        this.restaurantFacade = restaurantFacade;
    }

    public String getStreetaddress() {
        return streetaddress;
    }

    public void setStreetaddress(String streetaddress) {
        this.streetaddress = streetaddress;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getOpendate() {
        return opendate;
    }

    public void setOpendate(Date opendate) {
        this.opendate = opendate;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
    
    public void addLocation() {
        
        Restaurant res = restaurantFacade.findByRestaurantName(restaurantName, em);
        Location location = new Location();
        Owner resOwner = sessionBean.getOwner();
        java.sql.Date sqlDate = new java.sql.Date(opendate.getTime());
        
        location.setStreetaddress(streetaddress);
        location.setCity(city);
        location.setProvince(province);
        location.setPostalcode(postalcode);
        location.setOpendate(sqlDate);
        location.setOwner(resOwner);
        location.setRestaurant(res);
        
        boolean inRestaurantList = locationFacade.inRestaurantLocationList(res, location);
        
        if (inRestaurantList) {
            isError = true;
            status = "You already own this restaurant location";
        }
        else {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            if (locationFacade.addLocation(location, res)){
                try {
                    context.redirect(context.getRequestContextPath() +
                        "/owner/add_location_successful.xhtml");
                } catch (Exception e) {}
            }
        }
    }
    
}
