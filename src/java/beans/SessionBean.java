package beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import models.Owner;
import models.Rater;
import models.User;

@ManagedBean
@SessionScoped
public class SessionBean implements Serializable {
    
    private User user;

    public SessionBean() {
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public boolean isLoggedIn() {
        return user != null;
    }
    
    public boolean isOwner() {        
        return user instanceof Owner;
    }
    
    public Owner getOwner() {        
        if (user instanceof Owner) {
            return (Owner)user;
        }
        return null;
    }
    
    public boolean isRater() {        
        return user instanceof Rater;
    }
    
    public Rater getRater() {        
        if (user instanceof Rater) {
            return (Rater)user;
        }
        return null;
    }    
}
