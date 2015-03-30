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
    
}
