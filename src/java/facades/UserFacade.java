package facades;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import models.Owner;
import models.Rater;
import models.User;
import models.UserAccount;

@ManagedBean
@SessionScoped
public class UserFacade extends BaseFacade {
    
    @ManagedProperty(value="#{raterFacade}")
    RaterFacade raterFacade;
    
    @ManagedProperty(value="#{ownerFacade}")
    OwnerFacade ownerFacade;

    public RaterFacade getRaterFacade() {
        return raterFacade;
    }

    public void setRaterFacade(RaterFacade raterFacade) {
        this.raterFacade = raterFacade;
    }

    public OwnerFacade getOwnerFacade() {
        return ownerFacade;
    }

    public void setOwnerFacade(OwnerFacade ownerFacade) {
        this.ownerFacade = ownerFacade;
    }
    
    public User getByAccount(UserAccount userAccount, EntityManager em) {
        Owner owner = ownerFacade.getByAccount(userAccount, em);
        if( owner != null ) {
            return owner;
        }
        
        Rater rater = raterFacade.getByAccount(userAccount, em);
        
        return rater;
    }
    
}
