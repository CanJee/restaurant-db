/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 *
 * @author Can Jee
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Rater extends User {
    private static final long serialVersionUID = 1L;
    
    private int reputation;
    @OneToMany(fetch=FetchType.EAGER)
    private List<Rating> ratings;
    @OneToMany(fetch=FetchType.EAGER)
    private List<RatingItem> itemratings;
    private int totalNumberRating;
    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(nullable = true)
    private List<Rating> likedRatings;
    @OneToMany(fetch=FetchType.EAGER)
    private List<RatingItem> likedRatingItems;

    public int getReputation() {
        return reputation;
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
    }

    public List<Rating> getLikedRatings() {
        return likedRatings;
    }

    public void setLikedRatings(List<Rating> likedRatings) {
        this.likedRatings = likedRatings;
    }

    public List<RatingItem> getLikedRatingItems() {
        return likedRatingItems;
    }

    public void setLikedRatingItems(List<RatingItem> likedRatingItems) {
        this.likedRatingItems = likedRatingItems;
    }
    
    public int getTotalNumberRating() {
        int total = ratings.size()+itemratings.size();
        return total;
    }
    
    public void setTotalNumberRating(int rating){
        totalNumberRating = rating;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<RatingItem> getItemratings() {
        return itemratings;
    }

    public void setItemratings(List<RatingItem> itemratings) {
        this.itemratings = itemratings;
    }
    
}
