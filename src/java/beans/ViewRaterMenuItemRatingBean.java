/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import facades.MenuItemRatingFacade;
import facades.MenuItemFacade;
import facades.RestaurantFacade;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import models.RatingItem;
import models.MenuItem;
import models.Restaurant;
import models.Rater;

/**
 *
 * @author Alan
 */
@ManagedBean
@SessionScoped
public class ViewRaterMenuItemRatingBean extends BaseBean{
    
    @ManagedProperty(value="#{enuItemRatingFacade}")
    MenuItemRatingFacade menuItemRatingFacade;
    
    private String orderBy = "ratingdate";
    private boolean ascending = true;
    private Rater rater;
    private List<RatingItem> itemRatings;
    private int numItemRatings;
    
    public void orderedByChanged (ValueChangeEvent event){
        orderBy = event.getNewValue().toString();
        getItemRatings();
    }
    
    public void ascendingChanged (ValueChangeEvent event){
        String test = event.getNewValue().toString();
        if (test.equals("true"))
            ascending = true;
        else
            ascending = false;
        getItemRatings();
    }
    
    public int getNumItemRatings(){
        numItemRatings = itemRatings.size();
        return numItemRatings;
    }
    
    public List<RatingItem> getItemRatings(Rater rater) {
        itemRatings = menuItemRatingFacade.getRatingByRater(rater,orderBy, ascending, em);
        System.out.println(itemRatings.size());
        return itemRatings;
    }
    
    public void viewMenuItemRatings (Rater rate) {
        this.rater = rate;
        itemRatings = rater.getItemratings();
        
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
        context.redirect(context.getRequestContextPath() + "/view_rater_menuitems_rating.xhtml");
        } catch (Exception e) {}
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

    public Rater getRater() {
        return rater;
    }

    public void setRater(Rater rater) {
        this.rater = rater;
    }

    public List<RatingItem> getItemRatings() {
        return itemRatings;
    }

    public void setItemRatings(List<RatingItem> itemRatings) {
        this.itemRatings = itemRatings;
    }
    
    
}
