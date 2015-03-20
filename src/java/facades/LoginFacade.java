package facades;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import models.User;
import models.UserAccount;

@ManagedBean
@SessionScoped
public class LoginFacade extends BaseFacade {
    
    @ManagedProperty(value="#{userAccountFacade}")
    UserAccountFacade userAccountFacade;
    
    @ManagedProperty(value="#{userFacade}")
    UserFacade userFacade;

    public UserAccountFacade getUserAccountFacade() {
        return userAccountFacade;
    }

    public void setUserAccountFacade(UserAccountFacade userAccountFacade) {
        this.userAccountFacade = userAccountFacade;
    }

    public UserFacade getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }
    
    public User login(String username, String password) {
        UserAccount account = userAccountFacade.findByUsername(username, em);
        User user = null;
        if (account != null && userAccountFacade.checkPassword(account, password)) {
            user = userFacade.getByAccount(account, em);
            sessionBean.setUser(user);
        }
        return user;
    }
    
}
