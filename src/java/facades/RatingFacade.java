/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

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
import models.Rating;
import models.Rater;
import models.Restaurant;

/**
 *
 * @author Alan
 */
@ManagedBean
@SessionScoped
public class RatingFacade extends BaseFacade{
    
    public boolean addRating (Rating rating, Location location, Rater rater, Date visitDate) {
        
        try {
            utx.begin();
            java.sql.Date sqlVisitDate = new java.sql.Date(visitDate.getTime());
            rating.setVisitdate(sqlVisitDate);
            rating.setLikes(0);
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
    
    public boolean alreadyRatedForVisitDate (Date visitDate, Rater rater, Location location) {
        java.sql.Date sqlVisitDate = new java.sql.Date(visitDate.getTime());
        boolean alreadyRatedForDate = false;
        List<Rating> raterRatings = rater.getRatings();
        for (Rating raterRating : raterRatings){
            if (raterRating.getVisitdate().equals(sqlVisitDate) && raterRating.getLocation().equals(location))
                alreadyRatedForDate = true;
        }
        return alreadyRatedForDate;
    }
    
    public List<Rating> getRatingsByLocation(Location location, String orderBy, boolean ascending, EntityManager em) {
        String queryString = "SELECT r FROM Rating r WHERE r.location = :location";
        
        if (orderBy.equals("ratingdate")) {
            queryString += "  ORDER BY r.ratingdate ";
        } else if (orderBy.equals("visitdate")){
            queryString += "  ORDER BY r.visitdate ";
        } else if (orderBy.equals("pricerating")){
            queryString += "  ORDER BY r.pricerating ";
        } else if (orderBy.equals("foodrating")){
            queryString += "  ORDER BY r.foodrating ";
        } else if (orderBy.equals("moodrating")){
            queryString += "  ORDER BY r.moodrating ";
        } else if (orderBy.equals("staffrating")){
            queryString += "  ORDER BY r.staffrating ";
        } else if (orderBy.equals("likes")){
            queryString += "  ORDER BY r.likes ";
        }

        if (ascending) {
            queryString += "ASC";
        } else {
            queryString += "DESC";
        }
        Query query = em.createQuery(queryString);
        query.setParameter("location", location);
        List<Rating> items = performQueryList(Rating.class, query);
        if( items == null ) {
            items = new ArrayList<Rating>();
        }

        return items;
    }
    
    public List<Rating> getRatingsByRestaurant(Restaurant restaurant, String orderBy, boolean ascending, EntityManager em) {
        String queryString = "SELECT r FROM Restaurant x, Location l, Rating r WHERE x = :restaurant AND x = l.restaurant "
                + "AND r.location = l";
        
        if (orderBy.equals("ratingdate")) {
            queryString += "  ORDER BY r.ratingdate ";
        } else if (orderBy.equals("visitdate")){
            queryString += "  ORDER BY r.visitdate ";
        } else if (orderBy.equals("pricerating")){
            queryString += "  ORDER BY r.pricerating ";
        } else if (orderBy.equals("foodrating")){
            queryString += "  ORDER BY r.foodrating ";
        } else if (orderBy.equals("moodrating")){
            queryString += "  ORDER BY r.moodrating ";
        } else if (orderBy.equals("staffrating")){
            queryString += "  ORDER BY r.staffrating ";
        }

        if (ascending) {
            queryString += "ASC";
        } else {
            queryString += "DESC";
        }
        Query query = em.createQuery(queryString);
        query.setParameter("restaurant", restaurant);
        List<Rating> items = performQueryList(Rating.class, query);
        if( items == null ) {
            items = new ArrayList<Rating>();
        }

        return items;
    }
    
    public List<Rating> getRatingsByRater(Rater rater, String orderBy, boolean ascending, EntityManager em) {
        String queryString = "SELECT r FROM Rating r WHERE r.rater = :rater";
        
        if (orderBy.equals("ratingdate")) {
            queryString += "  ORDER BY r.ratingdate ";
        } else if (orderBy.equals("visitdate")){
            queryString += "  ORDER BY r.visitdate ";
        } else if (orderBy.equals("pricerating")){
            queryString += "  ORDER BY r.pricerating ";
        } else if (orderBy.equals("foodrating")){
            queryString += "  ORDER BY r.foodrating ";
        } else if (orderBy.equals("moodrating")){
            queryString += "  ORDER BY r.moodrating ";
        } else if (orderBy.equals("staffrating")){
            queryString += "  ORDER BY r.staffrating ";
        } else if (orderBy.equals("likes")){
            queryString += "  ORDER BY r.likes ";
        }
        
        if (ascending) {
            queryString += "ASC";
        } else {
            queryString += "DESC";
        }
        Query query = em.createQuery(queryString);
        query.setParameter("rater", rater);
        List<Rating> items = performQueryList(Rating.class, query);
        if( items == null ) {
            items = new ArrayList<Rating>();
        }

        return items;
    }
    
    public boolean alreadyLikedRating (Rater rater, Rating rating) {
        List<Rating> raterLikedRatings = rater.getLikedRatings();
        if (raterLikedRatings != null && raterLikedRatings.contains(rating))
            return true;
        else
            return false;
    }
    
    public void addLikeForRating (Rater rater, Rating rating) {
        try {
            utx.begin();
            Rater ratingRater = rating.getRater();
            rating.setLikes(rating.getLikes()+1);
            List<Rating> raterLikedRatings = rater.getLikedRatings();
            if (raterLikedRatings == null) {
                raterLikedRatings = new ArrayList<Rating>();
            }
            raterLikedRatings.add(rating);
            rater.setLikedRatings(raterLikedRatings);
            ratingRater.setReputation(ratingRater.getReputation()+1);
            em.merge(rater);
            em.merge(rating);
            em.merge(ratingRater);
            utx.commit();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
