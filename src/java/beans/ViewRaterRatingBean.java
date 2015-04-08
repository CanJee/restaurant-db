/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import facades.RatingFacade;
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
public class ViewRaterRatingBean extends BaseBean{
    
    @ManagedProperty(value="#{ratingFacade}")
    RatingFacade ratingFacade;
    
    private String orderBy = "ratingdate";
    private boolean ascending = true;
    private Rater rater;
    private List<Rating> ratings;
    
    public void orderedByChanged (ValueChangeEvent event){
        orderBy = event.getNewValue().toString();
        getRatings();
    }
    
    public void ascendingChanged (ValueChangeEvent event){
        String test = event.getNewValue().toString();
        if (test.equals("true"))
            ascending = true;
        else
            ascending = false;
        getRatings();
    }
    
    public List<Rating> getRatings() {
        ratings = ratingFacade.getRatingsByRater(rater,orderBy, ascending, em);
        System.out.println(ratings.size());
        return ratings;
    }
    
    public void viewRaterRatings (Rater rate) {
        this.rater = rate;
        ratings = rater.getRatings();
        
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
        context.redirect(context.getRequestContextPath() + "/view_rater_rating.xhtml");
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

    public Rater getRater() {
        return rater;
    }

    public void setRater(Rater rater) {
        this.rater = rater;
    }

    public void setRaterRatings(List<Rating> raterRatings) {
        this.ratings = raterRatings;
    }
    
    
    
}
