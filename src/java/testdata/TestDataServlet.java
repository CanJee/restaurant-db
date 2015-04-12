package testdata;

import facades.MenuItemFacade;
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
import javax.faces.bean.ManagedProperty;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.transaction.UserTransaction;
import models.Location;
import models.MenuItem;
import models.Rater;
import models.Owner;
import models.Rating;
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
            Rater user = createSecondRater();
            
            createRestaurants(owner);
            Location loc = addLocation(owner, restaurant1, "25 test drive", "Ottawa", "A0A 0A0", "ON" );
            addLocationRating(loc,rater);
            
            Location loc2 = addLocation(owner, restaurant1, "23 test park", "Montreal", "A0A 0A1", "QC" );
            Location loc3 = addLocation(owner, restaurant1, "3 principal", "Toronto", "A0A 0A2", "ON" );
            
            Location loc4 = addLocation(owner, restaurant2, "26 test drive", "Ottawa", "A0A 0A0", "ON" );
            Location loc5 = addLocation(owner, restaurant2, "24 test park", "Montreal", "A0A 0A1", "QC" );
            Location loc6 = addLocation(owner, restaurant2, "3 principal", "Toronto", "A0A 0A2", "ON" );
            
            Location loc7 = addLocation(owner, restaurant3, "27 test drive", "Ottawa", "A0A 0A0", "ON" );
            Location loc8 = addLocation(owner, restaurant3, "28 test park", "Montreal", "A0A 0A1", "QC" );
            Location loc9 = addLocation(owner, restaurant3, "4 principal", "Toronto", "A0A 0A2", "ON" );
            
            Location loc10 = addLocation(owner, restaurant4, "28 test drive", "Ottawa", "A0A 0A0", "ON" );
            Location loc11 = addLocation(owner, restaurant4, "29 test park", "Montreal", "A0A 0A1", "QC" );
            Location loc12 = addLocation(owner, restaurant4, "5 principal", "Toronto", "A0A 0A2", "ON" );
            
            Location loc13 = addLocation(owner, restaurant5, "29 test drive", "Ottawa", "A0A 0A0", "ON" );
            Location loc14 = addLocation(owner, restaurant5, "30 test park", "Montreal", "A0A 0A1", "QC" );
            Location loc15 = addLocation(owner, restaurant5, "6 principal", "Toronto", "A0A 0A2", "ON" );
            
            
            MenuItem item = addMenuItem(restaurant1,"main", "Cheese Burger", "food", "Cheese burger with lettuce and tomatoes"
            , 5.75);
            addMenuItemRating(item, 5, "I Love it", rater);
            
            MenuItem item2 = addMenuItem(restaurant1,"main", "Chicken Burger", "food", 
                    "Chicken burger with lettuce and tomatoes", 6.75);
        //    addMenuItemRating(item2, 3, "Just alright", rater);
            
            MenuItem item3 = addMenuItem(restaurant1,"main", "Burger Combo", "food", 
                    "Cheese Burger and fries", 8.75);
       //     addMenuItemRating(item3, 5, "Was alright.", user);
            
            MenuItem item4 = addMenuItem(restaurant1,"main", "Coke", "drink", 
                    "Coke", 1.75);
         //   addMenuItemRating(item4, 4, "Typical drink", rater);
        //    addMenuItemRating(item4, 1, "Was served water instead", user);
            
            MenuItem item5 = addMenuItem(restaurant1,"desert", "Ice Cream", "food", 
                    "Strawberry Ice Cream. Yummy", 3.75);
         //   addMenuItemRating(item5, 1, "Ew", rater);
         //   addMenuItemRating(item5, 5, "loved it", user);
            
            MenuItem item6 = addMenuItem(restaurant2,"main", "Chicken", "food", "Hot Grilled Chicken"
            , 8.75);
            MenuItem item7 = addMenuItem(restaurant2,"main", "Beef Burger", "food", 
                    "Hot grilled burger", 11.75);
         //   addMenuItemRating(item7, 4, "Decent", rater);
            
            MenuItem item8 = addMenuItem(restaurant2,"side", "Fries", "food", 
                    "baked fries", 3.75);
        //    addMenuItemRating(item, 1, "Came for fries, got a baked potato...", rater);
            
            MenuItem item9 = addMenuItem(restaurant2,"main", "Orange juice", "drink", 
                    "Orange juice", 1.65);
            
            MenuItem item10 = addMenuItem(restaurant4,"main", "Grilled halloumi", "food", "Cheese made from goat and sheep milk"
            , 6.75);
         //   addMenuItemRating(item10, 5, "Excellent", rater);
            
            MenuItem item11 = addMenuItem(restaurant4,"main", "Falafel", "food", 
                    "fried chickpeas with herbs", 5.75);
        //    addMenuItemRating(item11, 5, "No idea what I ate but it was great", user);
            
            MenuItem item12 = addMenuItem(restaurant4,"side", "Fattoush", "food", 
                    "Salad with crispy lettus and crunchy squares of pita", 4.75);
            
            MenuItem item13 = addMenuItem(restaurant4,"main", "milk", "drink", 
                    "Milk", 1.85);

            MenuItem item14 = addMenuItem(restaurant5,"main", "Gobhi Aloo", "food", "Cauliflower with potatoes"
            , 4.75);
            MenuItem item15 = addMenuItem(restaurant5,"main", "Baati", "food", 
                    "Wheat flour", 5.25);
            MenuItem item16 = addMenuItem(restaurant5,"main", "Baigan bharta", "food", 
                    "Eggplant cooked with tomatoes", 7.75);
            MenuItem item17 = addMenuItem(restaurant5,"main", "Water", "drink", 
                    "Water", 1.25);
            
            MenuItem item18 = addMenuItem(restaurant3,"main", "Noodle Soup", "food", "Soup"
            , 4.75);
        //    addMenuItemRating(item18, 2, "Wasn't satisfied.", rater);
            
            MenuItem item19 = addMenuItem(restaurant3,"main", "Chow mein", "food", 
                    "Chow mein mix", 6.75);
        //    addMenuItemRating(item19, 5, "Wasn't cooked properly", user);
            
            MenuItem item20 = addMenuItem(restaurant3,"main", "Fried Rice", "food", 
                    "Rice mix", 3.75);
            MenuItem item21 = addMenuItem(restaurant3,"main", "Water", "drink", 
                    "Water", 1.25);
         //   addMenuItemRating(item21, 5, "Best water I've ever had.", rater);
            
            
            
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
        account.setType("online");
        account.setJoindate(sqlDate);

        Rater user = new Rater();
        user.setReputation(1);
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
        account.setType("online");
        account.setJoindate(sqlDate);

        Rater user2 = new Rater();
        user2.setReputation(1);
        user2.setUserAccount(account);

        em.persist(account);
        em.persist(user2);
        return user2;
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
    
    private Location addLocation(Owner owner, Restaurant restaurant, String address, String city, String postalcode, String province) {
        java.util.Calendar cal = Calendar.getInstance();
        java.sql.Date sqlDate = new java.sql.Date(cal.getTime().getTime());
        List<Location> ownerLocations = new ArrayList<Location>();
        Location location = new Location();
        location.setStreetaddress(address);
        location.setCity(city);
        location.setPostalcode(postalcode);
        location.setProvince(province);
        location.setOpendate(sqlDate);
        location.setRestaurant(restaurant);
        location.setOwner(owner);
        ownerLocations.add(location);
        owner.setLocation(ownerLocations);
        em.persist(owner);
        em.persist(location);
        return location;
    }
    
    private MenuItem addMenuItem(Restaurant restaurant, String category, String name, String type, String desc, double price){
        MenuItem item = new MenuItem();
        item.setCategory(category);
        item.setName(name);
        item.setType(type);
        item.setDescription(desc);
        item.setPrice(price);
        item.setRestaurant(restaurant);
        em.persist(item);
        
        return item;
    }
    
    private void addMenuItemRating(MenuItem menuItem, int rate1, String comment, Rater rate){  
        java.util.Calendar cal = Calendar.getInstance();
        java.sql.Date sqlDate = new java.sql.Date(cal.getTime().getTime());
        List<RatingItem> menuItemRatings = new ArrayList<RatingItem>();
        RatingItem rating = new RatingItem();
        rating.setRating(rate1);
        rating.setComments(comment);
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
    
    private void addLocationRating (Location location, Rater rate) {
        java.util.Calendar cal = Calendar.getInstance();
        java.sql.Date sqlDate = new java.sql.Date(cal.getTime().getTime());
        List<Rating> locationRatings = new ArrayList<Rating>();
        Rating rating = new Rating();
        rating.setPricerating(6);
        rating.setFoodrating(8);
        rating.setMoodrating(3);
        rating.setStaffrating(7);
        rating.setComments("test");
        rating.setRater(rate);
        rating.setLocation(location);
        rating.setVisitdate(sqlDate);
        rating.setRatingdate(sqlDate);
        rating.setLikes(0);
        locationRatings.add(rating);
        location.setRatings(locationRatings);
        rate.setRatings(locationRatings);
        em.merge(location);
        em.merge(rate);
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
