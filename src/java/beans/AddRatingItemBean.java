/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import facades.MenuItemFacade;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import facades.MenuItemRatingFacade;
import facades.RestaurantFacade;
import java.util.Calendar;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import models.MenuItem;
import models.Rater;
import models.Restaurant;
import models.RatingItem;
import models.User;

/**
 *
 * @author Can Jee
 */
@ManagedBean
@RequestScoped
public class AddRatingItemBean extends BaseBean{
    
    @ManagedProperty(value="#{menuItemRatingFacade}")
    MenuItemRatingFacade menuItemRatingFacade;
    
    @ManagedProperty(value="#{menuItemFacade}")
    MenuItemFacade menuItemFacade;
    
    @ManagedProperty(value="#{restaurantFacade}")
    RestaurantFacade restaurantFacade;
    
    private Date visitDate;
    private String restaurantName;
    private String itemName;
    private String comments;
    private int rating;
    private String status;
    private MenuItem menuItem;
    private boolean isError = false;

    public MenuItemFacade getMenuItemFacade() {
        return menuItemFacade;
    }

    public void setMenuItemFacade(MenuItemFacade menuItemFacade) {
        this.menuItemFacade = menuItemFacade;
    }

    public MenuItemRatingFacade getMenuItemRatingFacade() {
        return menuItemRatingFacade;
    }

    public void setMenuItemRatingFacade(MenuItemRatingFacade menuItemRatingFacade) {
        this.menuItemRatingFacade = menuItemRatingFacade;
    }

    public RestaurantFacade getRestaurantFacade() {
        return restaurantFacade;
    }

    public void setRestaurantFacade(RestaurantFacade restaurantFacade) {
        this.restaurantFacade = restaurantFacade;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public boolean isIsError() {
        return isError;
    }

    public void setIsError(boolean isError) {
        this.isError = isError;
    }
    
    public void addMenuItemRating() {
        
        Calendar cal = Calendar.getInstance();
        java.sql.Date currentSqlDate = new java.sql.Date(cal.getTime().getTime()); 
        
        MenuItem item = menuItemFacade.findByItemName(itemName, em);
        RatingItem ratingItem = new RatingItem();
        Rater ratingRater = sessionBean.getRater();
        
        ratingItem.setComments(comments);
        ratingItem.setRating(rating);
        ratingItem.setRatingdate(currentSqlDate);
        ratingItem.setMenuitem(item);
        ratingItem.setRater(ratingRater);
        ratingItem.setLikes(0);
        
        if (menuItemRatingFacade.alreadyRatedForVisitDate(visitDate, ratingRater, item)){
            isError = true;
            status = "You have already entered a rating for this item for this visit date";
        }
        else {
            isError = false;
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            if( menuItemRatingFacade.addMenuItemRating(ratingItem, ratingRater, item, visitDate) ) {

                try {
                    context.redirect(context.getRequestContextPath() +
                        "/rater/add_menuitem_rating_successful.xhtml");
                } catch (Exception e) {}
            }
        }
    }
}
