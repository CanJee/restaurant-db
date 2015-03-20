package facades;

import beans.SessionBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.bean.ManagedProperty;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

public abstract class BaseFacade implements Serializable {
    
    @PersistenceContext(unitName = "MyPersistenceUnit")
    protected EntityManager em;

    @Resource
    protected UserTransaction utx;
    
    @ManagedProperty(value="#{sessionBean}")
    protected SessionBean sessionBean;
    
    public BaseFacade() {
    }

    public SessionBean getSessionBean() {
        return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }
    
    /*
     * Performs the given query and returns the first result from the list.
     */
    protected static <T> T performQuery(Class<T> type, final Query query) {
        List resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null;
        }
        ArrayList<T> results = new ArrayList<>();
        results.addAll(resultList);
        return results.get(0);
    }
    
    /*
     * Performs the given query and returns all of the results.
     */
    protected static <T> List<T> performQueryList(Class<T> type, final Query query) {
        List resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null;
        } 
        ArrayList<T> results = new ArrayList<>();
        results.addAll(resultList);
        return results;
    }
    
}
