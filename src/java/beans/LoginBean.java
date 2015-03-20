package beans;

import facades.LoginFacade;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import models.User;

@RequestScoped
@ManagedBean
public class LoginBean extends BaseBean {
    
    @ManagedProperty(value="#{loginFacade}")
    LoginFacade loginFacade;

    private String username;
    private String password;
    private boolean loginFailed = false;
    private String status;
    
    /**
     * Creates a new instance of LogInBean
     */
    public LoginBean() {
    }

    public LoginFacade getLoginFacade() {
        return loginFacade;
    }

    public void setLoginFacade(LoginFacade loginFacade) {
        this.loginFacade = loginFacade;
    }

    public boolean isLoginFailed() {
        return loginFailed;
    }

    public void setLoginFailed(boolean loginFailed) {
        this.loginFailed = loginFailed;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void login() {
        User user = loginFacade.login(username, password);
        
        if (user != null) {
            try {
                ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                context.redirect(context.getRequestContextPath() + "/index.xhtml");
            } catch (Exception e) {}
        } else {
            status = "Login failed. Invalid username or password.";
            loginFailed = true;
        }
    }
    
    public void logout() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.invalidateSession();
        try {
        context.redirect(context.getRequestContextPath() + "/logout.xhtml");
        } catch (Exception e) {}
    }
}
