package facades;

import static facades.BaseFacade.performQuery;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import models.Owner;
import models.UserAccount;

@ManagedBean
@SessionScoped
public class OwnerFacade extends BaseFacade {
        
    public Owner getByAccount(UserAccount account, EntityManager em) {
        Query query = em.createQuery("SELECT o FROM Owner o WHERE o.userAccount = :userAccount");
        query.setParameter("userAccount", account);
        return performQuery(Owner.class, query);
    }
    
}
