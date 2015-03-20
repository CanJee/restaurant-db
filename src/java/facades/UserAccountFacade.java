package facades;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import models.Rater;
import models.Owner;
import models.User;
import models.UserAccount;

@ManagedBean
@SessionScoped
public class UserAccountFacade extends BaseFacade {
    
    public boolean createAccount(String username, String password, String email,
            String firstname, String lastname, String type, String rtype) {
        try {
            utx.begin();
            java.util.Calendar cal = Calendar.getInstance();
            java.sql.Date sqlDate = new java.sql.Date(cal.getTime().getTime());
            
            UserAccount account = new UserAccount();
            User user;
            
            account.setUsername(username);
            setPassword(account, password);
            
            account.setEmail(email);
            account.setFirstname(firstname);
            account.setLastname(lastname);
            account.setType(rtype);
            account.setReputation(1);
            account.setJoindate(sqlDate);
            

            if (type.equals("owner")) {
                user = new Owner();
            }
            else {
                user = new Rater();
            }
            user.setUserAccount(account);
            em.persist(account);
            em.persist(user);
            utx.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean setPassword(UserAccount userAccount, String password) {
        try {
            // randomly generate salt value
            final Random r = new SecureRandom();
            byte[] salt = new byte[32];
            r.nextBytes(salt);
            String saltString = new String(salt, "UTF-8");
            // hash password using SHA-256 algorithm
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String saltedPass = saltString+password;
            byte[] passhash = digest.digest(saltedPass.getBytes("UTF-8"));
            userAccount.setSalt(salt);
            userAccount.setPassword(passhash);
            return true;
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | RuntimeException ex) {
            return false;
        }
    }
    
    public boolean checkPassword(UserAccount userAccount, String password) {
        try {
            byte[] salt = userAccount.getSalt();
            String saltString = new String(salt, "UTF-8");
            String checkPass = saltString + password;
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] checkPassHash = digest.digest(checkPass.getBytes("UTF-8"));
            if (Arrays.equals(checkPassHash, userAccount.getPassword())) {
                return true; 
            }
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(UserAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public UserAccount findByUsername(String username, EntityManager em) {
        Query query = em.createQuery("SELECT ua FROM UserAccount ua WHERE ua.username = :username");
        query.setParameter("username", username);
        UserAccount result = performQuery(UserAccount.class, query);
        return result;
    }           
    
}
