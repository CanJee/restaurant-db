/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import facades.RaterFacade;
import facades.RatingFacade;
import facades.MenuItemRatingFacade;
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
import models.Rater;
import models.Rating;
import models.RatingItem;
import models.Restaurant;

/**
 *
 * @author Can Jee
 */
@ManagedBean
@SessionScoped
public class ViewRaterBean extends BaseBean{
    
    @ManagedProperty(value="#{raterFacade}")
    RaterFacade raterFacade;
    RatingFacade ratingFacade;
    MenuItemRatingFacade menuItemRatingFacade;
    
    private String orderBy = "name";
    private boolean ascending = true;
    private List<Rater> raters;
    private List<Rating> ratings;
    private List<RatingItem> itemRatings;
    private int totalNumberRating;
    private int totalNumberItemRating;
        
    public int getTotalNumberRating() {
        return ratings.size();
    }

    public int getTotalNumberItemRating() {
        return itemRatings.size();
    }
  /*  public void orderedByChanged (ValueChangeEvent event){
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
    }*/
    
    public List<Rater> getRaters() {
        raters = raterFacade.getAll(em);
        System.out.println(raters.size());
        return raters;
    }
        
    public List<Rating> getRatings(Rater rater) {
        ratings = ratingFacade.getRatingsByRater(rater,orderBy, ascending, em);
        System.out.println(ratings.size());
        return ratings;
    }
     
    public List<RatingItem> getItemRatings(Rater rater) {
        itemRatings = menuItemRatingFacade.getRatingByRater(rater,orderBy, ascending, em);
        System.out.println(itemRatings.size());
        return itemRatings;
    }

    public RaterFacade getRaterFacade() {
        return raterFacade;
    }

    public void setRaterFacade(RaterFacade raterFacade) {
        this.raterFacade = raterFacade;
    }

    public RatingFacade getRatingFacade() {
        return ratingFacade;
    }

    public void setRatingFacade(RatingFacade ratingFacade) {
        this.ratingFacade = ratingFacade;
    }

    public MenuItemRatingFacade getMenuItemRatingFacade() {
        return menuItemRatingFacade;
    }

    public void setMenuItemRatingFacade(MenuItemRatingFacade menuItemRatingFacade) {
        this.menuItemRatingFacade = menuItemRatingFacade;
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

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<RatingItem> getItemRatings() {
        return itemRatings;
    }

    public void setItemRatings(List<RatingItem> itemRatings) {
        this.itemRatings = itemRatings;
    }

    public void setTotalNumberRating(int totalNumberRating) {
        this.totalNumberRating = totalNumberRating;
    }

    public void setTotalNumberItemRating(int totalNumberItemRating) {
        this.totalNumberItemRating = totalNumberItemRating;
    }
    
    
    
}
