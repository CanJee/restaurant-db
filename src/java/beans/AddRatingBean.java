/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import facades.LocationFacade;
import facades.RatingFacade;
import facades.RestaurantFacade;
import java.util.Calendar;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import models.Rater;
import models.Location;
import models.Rating;
import models.Restaurant;
import beans.ViewLocationBean;
import models.User;

/**
 *
 * @author Alan
 */
@ManagedBean
@SessionScoped
public class AddRatingBean extends BaseBean{
    
    @ManagedProperty(value="#{locationFacade}")
    LocationFacade locationFacade;
    
    @ManagedProperty(value="#{ratingFacade}")
    RatingFacade ratingFacade;
    
    @ManagedProperty(value="#{restaurantFacade}")
    RestaurantFacade restaurantFacade;
    
    private Date ratingDate;
    private int pricerating;
    private int foodrating;
    private int staffrating;
    private int moodrating;
    private String comments;
    private String restaurantName;
    private String streetaddress;
    private Date visitDate;
    private Location location;
    private Rater rater;
    private String status;
    private boolean isError = false;

    public int getMoodrating() {
        return moodrating;
    }

    public void setMoodrating(int moodrating) {
        this.moodrating = moodrating;
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

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        
        this.restaurantName = restaurantName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public RatingFacade getRatingFacade() {
        return ratingFacade;
    }

    public void setRatingFacade(RatingFacade ratingFacade) {
        this.ratingFacade = ratingFacade;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public Date getRatingDate() {
        return ratingDate;
    }

    public void setRatingDate(Date ratingDate) {
        this.ratingDate = ratingDate;
    }

    public int getPricerating() {
        return pricerating;
    }

    public void setPricerating(int pricerating) {
        this.pricerating = pricerating;
    }

    public int getFoodrating() {
        return foodrating;
    }

    public void setFoodrating(int foodrating) {
        this.foodrating = foodrating;
    }

    public int getStaffrating() {
        return staffrating;
    }

    public void setStaffrating(int staffrating) {
        this.staffrating = staffrating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Rater getRater() {
        return rater;
    }

    public void setRater(Rater rater) {
        this.rater = rater;
    }
    
    public void addRating() {
        
        Calendar cal = Calendar.getInstance();
        java.sql.Date currentSqlDate = new java.sql.Date(cal.getTime().getTime()); 
        
        Restaurant res = restaurantFacade.findByRestaurantName(restaurantName, em);
        Location loc = locationFacade.findByStreetAddress(streetaddress, em);
        Rating rating = new Rating();
        User user = sessionBean.getUser();
        Rater ratingRater = sessionBean.getRater();
        
        rating.setComments(comments);
        rating.setFoodrating(foodrating);
        rating.setMoodrating(moodrating);
        rating.setPricerating(pricerating);
        rating.setStaffrating(staffrating);
        rating.setRatingdate(currentSqlDate);
        rating.setRater(ratingRater);
        rating.setLocation(loc);
        System.out.println(visitDate);
        if (ratingFacade.alreadyRatedForVisitDate(visitDate, ratingRater, loc)){
            isError = true;
            status = "You have already entered a rating for this visit date";
        }
        else {
            isError = false;
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            if( ratingFacade.addRating(rating, loc, ratingRater, visitDate) ) {

                try {
                    context.redirect(context.getRequestContextPath() +
                        "/rater/add_rating_successful.xhtml");
                } catch (Exception e) {}
            }
        }
    }
    
}
