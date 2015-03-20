/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

/**
 *
 * @author Can Jee
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Rater extends User {
    private static final long serialVersionUID = 1L;
    
    @OneToMany (mappedBy = "rater")
    private List<Rating> ratings;
    @OneToMany (mappedBy = "rater")
    private List<RatingItem> itemratings;

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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rater)) {
            return false;
        }
        Rater other = (Rater) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Rater[ id=" + getId() + " ]";
    }
    
}
