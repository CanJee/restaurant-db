/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import static facades.BaseFacade.performQueryList;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import models.Location;
import models.MenuItem;
import models.RatingItem;
import models.Rater;
import models.Rating;
import models.Restaurant;




/**
 *
 * @author Can Jee
 */
@ManagedBean
@SessionScoped
public class MenuItemRatingFacade extends BaseFacade{
    
    public boolean addMenuItemRating (RatingItem ratingItem, Rater rater, MenuItem item, Date visitDate) {
        
        try {
            utx.begin();
            java.sql.Date sqlVisitDate = new java.sql.Date(visitDate.getTime());
            ratingItem.setVisitdate(sqlVisitDate);
            List<RatingItem> raterItemRatings = rater.getItemratings();
            List<RatingItem> itemRatings = item.getRatings();
            raterItemRatings.add(ratingItem);
            itemRatings.add(ratingItem);
            em.merge(rater);
            em.merge(item);
            em.persist(ratingItem);
            utx.commit();
            return true;
            
        } catch (Exception e) {
            return false;
        }
        
    }
    
    public boolean alreadyRatedForVisitDate (Date visitDate, Rater rater, MenuItem item) {
        java.sql.Date sqlVisitDate = new java.sql.Date(visitDate.getTime());
        boolean alreadyRatedForDate = false;
        List<RatingItem> raterItemRatings = rater.getItemratings();
        for (RatingItem raterItemRating : raterItemRatings){
            if (raterItemRating.getVisitdate().equals(sqlVisitDate))
                alreadyRatedForDate = true;
        }
        return alreadyRatedForDate;
    }
    
    public List<RatingItem> getRatingsByMenuItem(MenuItem menuItem, String orderBy, boolean ascending, EntityManager em) {
        String queryString = "SELECT r FROM RatingItem r WHERE r.menuitem = :menuitem";
        
        if (orderBy.equals("ratingdate")) {
            queryString += "  ORDER BY r.ratingdate ";
        } else if (orderBy.equals("visitdate")){
            queryString += "  ORDER BY r.visitdate ";
        } else if (orderBy.equals("rating")){
            queryString += "  ORDER BY r.rating ";
        } else if (orderBy.equals("likes")){
            queryString += "  ORDER BY r.likes ";
        }
            
        if (ascending) {
            queryString += "ASC";
        } else {
            queryString += "DESC";
        }
        Query query = em.createQuery(queryString);
        query.setParameter("menuitem", menuItem);
        List<RatingItem> items = performQueryList(RatingItem.class, query);
        if( items == null ) {
            items = new ArrayList<RatingItem>();
        }

        return items;
    }    
    
    public List<RatingItem> getRatingByRater(Rater rater, String orderBy, boolean ascending, EntityManager em) {
        String queryString = "SELECT i FROM RatingItem i WHERE i.rater = :rater";
        
        if (orderBy.equals("itemname")) {
            queryString += "  ORDER BY i.name ";
        } else if (orderBy.equals("rating")){
            queryString += "  ORDER BY i.rating ";
        }else if (orderBy.equals("visitdate")){
            queryString += "  ORDER BY i.visitdate ";
        } else if (orderBy.equals("ratingdate")){
            queryString += "  ORDER BY i.ratingdate ";
        }
            
        if (ascending) {
            queryString += "ASC";
        } else {
            queryString += "DESC";
        }
        
        Query query = em.createQuery(queryString);
        query.setParameter("rater", rater);
        List<RatingItem> items = performQueryList(RatingItem.class, query);
        if( items == null ) {
            items = new ArrayList<RatingItem>();
        }

        return items;
    }
    
}
