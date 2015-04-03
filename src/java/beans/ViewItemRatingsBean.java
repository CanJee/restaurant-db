/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import facades.MenuItemFacade;
import facades.MenuItemRatingFacade;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import models.Location;
import models.MenuItem;
import models.Rating;
import models.RatingItem;

/**
 *
 * @author Can Jee
 */
@ManagedBean
@SessionScoped
public class ViewItemRatingsBean extends BaseBean{
    
    @ManagedProperty(value="#{menuItemRatingFacade}")
    MenuItemRatingFacade menuItemRatingFacade;
    
    private String orderBy = "rating";
    private boolean ascending = true;
    private List<MenuItem> menuItems;
    private MenuItem menuItem;
    private List<RatingItem> menuItemRatings;
    private int ratingsCount;

    public void orderedByChanged (ValueChangeEvent event){
        orderBy = event.getNewValue().toString();
        System.out.println(orderBy);
        getMenuItemRatings();
    }
    
    public void ascendingChanged (ValueChangeEvent event){
        String test = event.getNewValue().toString();
        if (test.equals("true"))
            ascending = true;
        else
            ascending = false;
        getMenuItemRatings();
    }

    public void viewMenuItemRatings (MenuItem menuItem) {
        this.menuItem = menuItem;
        menuItemRatings = menuItem.getRatings();
        
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
        context.redirect(context.getRequestContextPath() + "/view_menuItem_ratings.xhtml");
        } catch (Exception e) {}
    }
    
    public List<RatingItem> getMenuItemRatings() {
        menuItemRatings = menuItemRatingFacade.getRatingsByMenuItem(menuItem, orderBy, ascending, em);
        return menuItemRatings;
    }    

    public MenuItemRatingFacade getMenuItemRatingFacade() {
        return menuItemRatingFacade;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public boolean isAscending() {
        return ascending;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getRatingsCount() {
        getMenuItemRatings();
        if (menuItemRatings == null)
           ratingsCount = 0;
        else
            ratingsCount = menuItemRatings.size();
        return ratingsCount;
    }

    public void setMenuItemRatingFacade(MenuItemRatingFacade menuItemRatingFacade) {
        this.menuItemRatingFacade = menuItemRatingFacade;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public void setMenuItemRatings(List<RatingItem> menuItemRatings) {
        this.menuItemRatings = menuItemRatings;
    }

    public void setRatingsCount(int ratingsCount) {
        this.ratingsCount = ratingsCount;
    }
    
    
    
    
    
    
}
