package facades;

import static facades.BaseFacade.performQuery;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
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
    
    public List<Rater> getAll(String orderBy, Boolean ascending, EntityManager em) {
        String queryString = "SELECT r FROM Rater r";
        
        if (orderBy.equals("name")) {
            queryString += "  ORDER BY r.userAccount.lastname ";
        } else if (orderBy.equals("reputation")){
            queryString += "  ORDER BY r.totalNumberRating ";
        }

        if (ascending) {
            queryString += "ASC";
        } else {
            queryString += "DESC";
        }
        
        Query query = em.createQuery(queryString);
        return performQueryList(Rater.class, query);
    }
    
    
    
}
