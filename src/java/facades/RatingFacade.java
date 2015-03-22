/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import static facades.BaseFacade.performQueryList;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import models.Location;
import models.Rating;
import models.Rater;

/**
 *
 * @author Alan
 */
@ManagedBean
@SessionScoped
public class RatingFacade extends BaseFacade{
    
    public boolean addRating (Rating rating, Location location, Rater rater) {
        
        try {
            utx.begin();
            List<Rating> locationRatings = location.getRatings();
            List<Rating> raterRatings = rater.getRatings();
            locationRatings.add(rating);
            raterRatings.add(rating);
            em.merge(location);
            em.merge(rater);
            em.persist(rating);
            utx.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
        
    }
    
}
