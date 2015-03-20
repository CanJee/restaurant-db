package facades;

import static facades.BaseFacade.performQueryList;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import models.Location;
import models.Rater;
import models.Owner;
import models.Restaurant;
import models.UserAccount;

@ManagedBean
@SessionScoped
public class RestaurantFacade extends BaseFacade {
    
        public List<Restaurant> getRestaurantList(String orderBy, boolean ascending, EntityManager em) {

            String queryString = "SELECT r FROM Restaurant r";

            if (orderBy.equals("name")) {
                queryString += "  ORDER BY r.name ";
            } else {
                queryString += "  ORDER BY r.type ";
            }

            if (ascending) {
                queryString += "ASC";
            } else {
                queryString += "DESC";
            }

            Query query = em.createQuery(queryString);

            List<Restaurant> restaurants = performQueryList(Restaurant.class, query);

            if( restaurants == null ) {
                restaurants = new ArrayList<Restaurant>();
            }

            return restaurants;
        }
        
        public Restaurant findByRestaurantName(String name, EntityManager em) {
            Query query = em.createQuery("SELECT r FROM Restaurant r WHERE r.name = :name");
            query.setParameter("name", name);
            Restaurant result = performQuery(Restaurant.class, query);
            return result;
        }     
    
}
