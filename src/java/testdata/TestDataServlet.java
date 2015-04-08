package testdata;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.sql.Date;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.transaction.UserTransaction;
import models.Location;
import models.MenuItem;
import models.Rater;
import models.Owner;
import models.Restaurant;
import models.UserAccount;
import models.RatingItem;



@WebServlet
public class TestDataServlet extends HttpServlet {
    
    @PersistenceContext(unitName = "MyPersistenceUnit")
    private EntityManager em;

    @Resource
    private UserTransaction utx;
    
    Restaurant restaurant1;
    Restaurant restaurant2;
    Restaurant restaurant3;
    Restaurant restaurant4;
    Restaurant restaurant5;
    
    @Override
    public void init() {
        try {
            utx.begin();
            
            Owner owner = createOwner();
            Rater rater = createRater();
            
            createRestaurants(owner);
            addLocation(owner, restaurant1);
            MenuItem item = addMenuItem(restaurant1);
            addMenuItemRating(item,rater);            
            
            
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
    
    private Rater createSecondRater() {
        java.util.Calendar cal = Calendar.getInstance();
        java.sql.Date sqlDate = new java.sql.Date(cal.getTime().getTime());
        UserAccount account = new UserAccount();
        account.setEmail("rater2@example.com");
        account.setUsername("rater2");
        account.setFirstname("Rater2");
        account.setLastname("Rater2");
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
    
    private void createRestaurants(Owner owner) {
        restaurant1 = new Restaurant();
        restaurant2 = new Restaurant();
        restaurant3 = new Restaurant();
        restaurant4 = new Restaurant();
        restaurant5 = new Restaurant();
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
    
    private void addLocation(Owner owner, Restaurant restaurant) {
        java.util.Calendar cal = Calendar.getInstance();
        java.sql.Date sqlDate = new java.sql.Date(cal.getTime().getTime());
        List<Location> ownerLocations = new ArrayList<Location>();
        Location location = new Location();
        location.setStreetaddress("25 test drive");
        location.setCity("Ottawa");
        location.setPostalcode("A0A 0A0");
        location.setProvince("ON");
        location.setOpendate(sqlDate);
        location.setRestaurant(restaurant);
        location.setOwner(owner);
        ownerLocations.add(location);
        owner.setLocation(ownerLocations);
        em.persist(owner);
        em.persist(location);
    }
    
    private MenuItem addMenuItem(Restaurant restaurant){
        MenuItem item = new MenuItem();
        item.setCategory("main");
        item.setName("Cheese Burger");
        item.setType("food");
        item.setDescription("Cheese burger with lettuce and tomatoes");
        item.setPrice(5.75);
        item.setRestaurant(restaurant);
        em.persist(item);
        
        return item;
    }
    
    private void addMenuItemRating(MenuItem menuItem, Rater rate){
        java.util.Calendar cal = Calendar.getInstance();
        java.sql.Date sqlDate = new java.sql.Date(cal.getTime().getTime());
        List<RatingItem> menuItemRatings = new ArrayList<RatingItem>();
        RatingItem rating = new RatingItem();
        rating.setRating(3);
        rating.setComments("test");
        rating.setRater(rate);
        rating.setMenuitem(menuItem);
        rating.setVisitdate(sqlDate);
        rating.setRatingdate(sqlDate);
        rating.setLikes(0);
        menuItemRatings.add(rating);
        menuItem.setRatings(menuItemRatings);
        rate.setItemratings(menuItemRatings);
        em.persist(menuItem);
        em.persist(rate);
        em.persist(rating);
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
