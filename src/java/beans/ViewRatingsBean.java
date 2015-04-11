/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import facades.RaterFacade;
import facades.RatingFacade;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import models.Location;
import models.Rater;
import models.Rating;

/**
 *
 * @author Can Jee
 */
@ManagedBean
@SessionScoped
public class ViewRatingsBean extends BaseBean{
    
    @ManagedProperty(value="#{ratingFacade}")
    RatingFacade ratingFacade;
    
    @ManagedProperty(value="#{raterFacade}")
    RaterFacade raterFacade;
    
    private String orderBy = "ratingdate";
    private boolean ascending = true;
    private List<Location> locations;
    private Location location;
    private List<Rating> locationRatings;
    private int ratingsCount;
    private String status;
    private boolean isError;

    public RaterFacade getRaterFacade() {
        return raterFacade;
    }

    public void setRaterFacade(RaterFacade raterFacade) {
        this.raterFacade = raterFacade;
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
    
    public void addLike (Rating rating) {
        Rater rater = sessionBean.getRater();
        if (ratingFacade.alreadyLikedRating(rater, rating)) {
            isError = true;
            status = "You have already liked this rating";
        }
        else {
            isError = false;
            ratingFacade.addLikeForRating(rater, rating);
        }
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    
    public void viewLocationRatings (Location location) {
        isError = false;
        this.location = location;
        locationRatings = location.getRatings();
        
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
        context.redirect(context.getRequestContextPath() + "/view_location_ratings.xhtml");
        } catch (Exception e) {}
    }
    
    public void orderedByChanged (ValueChangeEvent event){
        orderBy = event.getNewValue().toString();
        getLocationRatings();
    }
    
    public void ascendingChanged (ValueChangeEvent event){
        String test = event.getNewValue().toString();
        if (test.equals("true"))
            ascending = true;
        else
            ascending = false;
        getLocationRatings();
    }

    public RatingFacade getRatingFacade() {
        return ratingFacade;
    }

    public void setRatingFacade(RatingFacade ratingFacade) {
        this.ratingFacade = ratingFacade;
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
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<Rating> getLocationRatings() {
        locationRatings = ratingFacade.getRatingsByLocation(location, orderBy, ascending, em);
        return locationRatings;
    }

    public void setLocationRatings(List<Rating> locationRatings) {
        this.locationRatings = locationRatings;
    }

    public int getRatingsCount() {
        getLocationRatings();
        if (locationRatings == null)
           ratingsCount = 0;
        else
            ratingsCount = locationRatings.size();
        return ratingsCount;
    }

    public void setRatingsCount(int ratingsCount) {
        this.ratingsCount = ratingsCount;
    }
    
}
