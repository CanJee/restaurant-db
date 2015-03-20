package testdata;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Random;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.transaction.UserTransaction;
import models.Location;
import models.Rater;
import models.Owner;
import models.Restaurant;
import models.UserAccount;


@WebServlet
public class TestDataServlet extends HttpServlet {
    
    @PersistenceContext(unitName = "MyPersistenceUnit")
    private EntityManager em;

    @Resource
    private UserTransaction utx;
    
    @Override
    public void init() {
        try {
            utx.begin();
            
            Owner owner = createOwner();
            Rater rater = createRater();
            
            createRestaurants();
            
            utx.commit();
        } catch (Exception e) {
            int i = 1;
        }
    }
    
    private Owner createOwner() {
        java.util.Calendar cal = Calendar.getInstance();
        java.sql.Date sqlDate = new java.sql.Date(cal.getTime().getTime());
        UserAccount account = new UserAccount();
        account.setEmail("owner@example.com");
        account.setUsername("owner");
        account.setFirstname("Owner1");
        account.setLastname("Owner1");
        setPassword(account, "test");
        account.setReputation(1);
        account.setType("online");
        account.setJoindate(sqlDate);

        Owner user = new Owner();
        user.setUserAccount(account);

        em.persist(account);
        em.persist(user);
        return user;
    }
    
    private Rater createRater() {
        java.util.Calendar cal = Calendar.getInstance();
        java.sql.Date sqlDate = new java.sql.Date(cal.getTime().getTime());
        UserAccount account = new UserAccount();
        account.setEmail("rater@example.com");
        account.setUsername("rater");
        account.setFirstname("Rater1");
        account.setLastname("Rater1");
        setPassword(account, "test");
        account.setReputation(1);
        account.setType("online");
        account.setJoindate(sqlDate);

        Rater user = new Rater();
        user.setUserAccount(account);

        em.persist(account);
        em.persist(user);
        return user;
    }
    
    private void createRestaurants() {
        Restaurant restaurant1 = new Restaurant();
        Restaurant restaurant2 = new Restaurant();
        Restaurant restaurant3 = new Restaurant();
        Restaurant restaurant4 = new Restaurant();
        Restaurant restaurant5 = new Restaurant();
        restaurant1.setName("McDonalds");
        restaurant1.setUrl("http://www.mcdonalds.com");
        restaurant1.setType("American");
        restaurant2.setName("Nandos");
        restaurant2.setUrl("http://www.nandos.com");
        restaurant2.setType("European");
        restaurant3.setName("Lucky Chinese Restaurant");
        restaurant3.setUrl("http://www.yelp.ca/biz/lucky-chinese-restaurant-scarborough-2");
        restaurant3.setType("Chinese");
        restaurant4.setName("Paramount Fine Foods");
        restaurant4.setUrl("http://www.paramountfinefoods.com/");
        restaurant4.setType("Middle Eastern");
        restaurant5.setName("Sabri Nihari");
        restaurant5.setUrl("http://www.sabrinihari.com");
        restaurant5.setType("Indian");
        em.persist(restaurant1);
        em.persist(restaurant2);
        em.persist(restaurant3);
        em.persist(restaurant4);
        em.persist(restaurant5);
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
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Test data servlet";
    }// </editor-fold>

}
