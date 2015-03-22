package facades;

import static facades.BaseFacade.performQueryList;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import models.Location;
import models.Rater;
import models.Owner;
import models.Restaurant;
import models.UserAccount;

@ManagedBean
@SessionScoped
public class LocationFacade extends BaseFacade {
    
    public boolean addLocation(Location location, Restaurant restaurant) {        
        try {
            utx.begin();
            Owner owner = sessionBean.getOwner();
            UserAccount account = owner.getUserAccount();
            List<Location> locationList = restaurant.getLocation();
            List<Location> ownerLocationList = owner.getLocation();
            locationList.add(location);
            ownerLocationList.add(location);
            em.merge(owner);
            em.merge(restaurant);
            em.persist(location);
            utx.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean inOwnerLocationList(Location location){
        boolean inList = false;
        Owner owner = sessionBean.getOwner();
        List<Location> ownerLocationList = owner.getLocation();
        for (Location loc : ownerLocationList){
            if (loc.equals(location)){
                inList = true;
            }
        }
        return inList;
    }
    
    public boolean inRestaurantLocationList(Restaurant restaurant, Location location){
        boolean inList = false;
        List<Location> locationList = restaurant.getLocation();
        for (Location loc : locationList){
            if (loc.equals(location)){
                inList = true;
            }
        }
        return inList;
    }
    
}
