package beans;

import javax.annotation.Resource;
import javax.faces.bean.ManagedProperty;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

public abstract class BaseBean {

    @PersistenceContext(unitName = "MyPersistenceUnit")
    protected EntityManager em;

    @Resource
    protected UserTransaction utx;
    
    @ManagedProperty(value="#{sessionBean}")
    protected SessionBean sessionBean;
    
    /**
     * Creates a new instance of BaseBean
     */
    public BaseBean() {
    }

    public SessionBean getSessionBean() {
        return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }
    
}
