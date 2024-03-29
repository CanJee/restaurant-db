/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Can Jee
 */
@Entity
@Table(name="rating_6795550")
public class Rating implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private Date ratingdate;
    @Column(nullable = false)
    private Date visitdate;
    @Column(nullable = false)
    private int pricerating;
    @Column(nullable = false)
    private int foodrating;
    @Column(nullable = false)
    private int moodrating;
    @Column(nullable = false)
    private int staffrating;
    @Column(nullable = false)
    private String comments;
    @Column(nullable = false)
    private int likes;
    @ManyToOne
    private Location location;
    @ManyToOne
    private Rater rater;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
    
    public Date getRatingdate() {
        return ratingdate;
    }

    public void setRatingdate(Date ratingdate) {
        this.ratingdate = ratingdate;
    }

    public Date getVisitdate() {
        return visitdate;
    }

    public void setVisitdate(Date visitdate) {
        this.visitdate = visitdate;
    }

    public int getPricerating() {
        return pricerating;
    }

    public void setPricerating(int pricerating) {
        this.pricerating = pricerating;
    }

    public int getFoodrating() {
        return foodrating;
    }

    public void setFoodrating(int foodrating) {
        this.foodrating = foodrating;
    }

    public int getMoodrating() {
        return moodrating;
    }

    public void setMoodrating(int moodrating) {
        this.moodrating = moodrating;
    }

    public int getStaffrating() {
        return staffrating;
    }

    public void setStaffrating(int staffrating) {
        this.staffrating = staffrating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Rater getRater() {
        return rater;
    }

    public void setRater(Rater rater) {
        this.rater = rater;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rating other = (Rating) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Rating[ user=" + rater + " visitdate=" + visitdate + " ]";
    }
    
}
