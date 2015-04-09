/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import facades.RatingFacade;
import facades.RestaurantFacade;
import facades.LocationFacade;
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
import models.Restaurant;
import models.Rating;
import models.RatingItem;

/**
 *
 * @author Alan
 */
@ManagedBean
@SessionScoped
public class ViewRestaurantRatingBean extends BaseBean{
    
    @ManagedProperty(value="#{ratingFacade}")
    RatingFacade ratingFacade;
    
    private String orderBy = "ratingdate";
    private boolean ascending = true;
    private Restaurant restaurant;
    private List<Rating> rating;
    private int ratingCount;
    
    public int getRatingCount(){
        return rating.size();
    }
    
    public void orderedByChanged (ValueChangeEvent event){
        orderBy = event.getNewValue().toString();
        getRestaurantRatings();
    }
    
    public void ascendingChanged (ValueChangeEvent event){
        String test = event.getNewValue().toString();
        if (test.equals("true"))
            ascending = true;
        else
            ascending = false;
        getRestaurantRatings();
    }
    
    public List<Rating> getRestaurantRatings() {
        
        rating = ratingFacade.getRatingsByRestaurant(restaurant, orderBy, ascending, em);
        return rating;
    }
    
    
    public void viewRestaurantRatings (Restaurant restaurant) {
        this.restaurant = restaurant;

        rating = ratingFacade.getRatingsByRestaurant(restaurant, orderBy, ascending, em);
        System.out.println(rating.size());
        
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
        context.redirect(context.getRequestContextPath() + "/view_restaurant_ratings.xhtml");
        } catch (Exception e) {}
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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Rating> getRating() {
        return rating;
    }

    public void setRating(List<Rating> rating) {
        this.rating = rating;
    }   
    
    
}
