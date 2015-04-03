/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Can Jee
 */
@Entity
@Table(name="ratingitem_6795550")
public class RatingItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private Date ratingdate;
    @Id
    @Column(nullable = false)
    private Date visitdate;
    @Column(nullable = false)
    private int rating;
    @Column(nullable = false)
    private String comments;
    @ManyToOne(fetch = FetchType.EAGER)
    private MenuItem menuitem;
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    private Rater rater;

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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comment) {
        this.comments = comment;
    }

    public MenuItem getMenuitem() {
        return menuitem;
    }

    public void setMenuitem(MenuItem menuitem) {
        this.menuitem = menuitem;
    }

    public Rater getRater() {
        return rater;
    }

    public void setRater(Rater rater) {
        this.rater = rater;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.visitdate);
        hash = 29 * hash + Objects.hashCode(this.menuitem);
        hash = 29 * hash + Objects.hashCode(this.rater);
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
        final RatingItem other = (RatingItem) obj;
        if (!Objects.equals(this.visitdate, other.visitdate)) {
            return false;
        }
        if (!Objects.equals(this.menuitem, other.menuitem)) {
            return false;
        }
        if (!Objects.equals(this.rater, other.rater)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.RatingItem[ user=" + rater + " item=" + menuitem + " date=" + visitdate + " ]";
    }
    
}
