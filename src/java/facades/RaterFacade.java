package facades;

import static facades.BaseFacade.performQuery;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import models.Rater;
import models.UserAccount;

@ManagedBean
@SessionScoped
public class RaterFacade extends BaseFacade {
    
    public Rater getByAccount(UserAccount account, EntityManager em) {
        Query query = em.createQuery("SELECT r FROM Rater r WHERE r.userAccount = :userAccount");
        query.setParameter("userAccount", account);
        return performQuery(Rater.class, query);
    }
    
}
