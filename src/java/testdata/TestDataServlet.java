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
    Restaurant restaurant6;
    Restaurant restaurant7;
    Restaurant restaurant8;
    Restaurant restaurant9;
    Restaurant restaurant10;
    Restaurant restaurant11;
    Restaurant restaurant12;
    
    
    @Override
    public void init() {
        try {
            utx.begin();
            
            Owner owner = createOwner("owner@example.com", "owner", "Robert", "Paul", "test");
            Owner owner2 = createOwner("owner2@example.com", "owner2", "Mangus", "Willith", "test");
            Owner owner3 = createOwner("owner3@example.com", "owner3", "Marty", "Base", "test");
            Owner owner4 = createOwner("owner4@example.com", "owner4", "Melissa", "Lambert", "test");
            Owner owner5 = createOwner("owner5@example.com", "owner5", "Paul", "Johnson", "test");
            
            Rater rater = createRater("rater@example.com", "rater", "John", "Doe", "test",4);
            Rater rater2 = createRater("rater2@example.com", "rater2", "Bob", "Doe", "test",13);
            Rater rater3 = createRater("rater3@example.com", "rater3", "Maria", "Charles", "test",500);
            Rater rater4 = createRater("rater4@example.com", "rater4", "April", "Wington", "test",1);
            Rater rater5 = createRater("rater5@example.com", "rater5", "Sam", "Bard", "test",1);
            Rater rater6 = createRater("rater6@example.com", "rater6", "Emily", "Thompson", "test",3);
            Rater rater7 = createRater("rater7@example.com", "rater7", "Joe", "Lou", "test",8);
            Rater rater8 = createRater("rater8@example.com", "rater8", "Bill", "White", "test",8);
            Rater rater9 = createRater("rater9@example.com", "rater9", "Brandon", "Philp", "test",12);
            Rater rater10 = createRater("rater10@example.com", "rater10", "Jordan", "Snow", "test",17);
            Rater rater11 = createRater("rater11@example.com", "rater11", "Kelsey", "Strong", "test",24);
            Rater rater12 = createRater("rater12@example.com", "rater12", "Cody", "Georgon", "test",3);
            Rater rater13 = createRater("rater13@example.com", "rater13", "Robert", "Samuel", "test",5);
            Rater rater14 = createRater("rater14@example.com", "rater14", "Emma", "Vank", "test",11);
            Rater rater15 = createRater("rater15@example.com", "rater15", "Moore", "Jingson", "test",33);
            
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
            
            Location loc16 = addLocation(owner, restaurant6, "30 test drive", "Ottawa", "A0A 0A0", "ON" );
            Location loc17 = addLocation(owner, restaurant7, "31 test park", "Montreal", "A0A 0A1", "QC" );
            Location loc18 = addLocation(owner, restaurant8, "7 principal", "Toronto", "A0A 0A2", "ON" );
            
            Location loc19 = addLocation(owner, restaurant9, "31 test drive", "Ottawa", "A0A 0A0", "ON" );
            Location loc20 = addLocation(owner, restaurant10, "32 test park", "Montreal", "A0A 0A1", "QC" );
            Location loc21 = addLocation(owner, restaurant11, "8 principal", "Toronto", "A0A 0A2", "ON" );
            
            Location loc22 = addLocation(owner, restaurant12, "9 principal", "Toronto", "A0A 0A2", "ON" );
            
            
            
            MenuItem item = addMenuItem(restaurant1,"main", "Cheese Burger", "food", "Cheese burger with lettuce and tomatoes"
            , 5.75);
            addMenuItemRating(item, 5, "I Love it", rater,3);
            addMenuItemRating(item, 5, "Best creation ever.", rater2,13);
            
            MenuItem item2 = addMenuItem(restaurant1,"main", "Chicken Burger", "food", 
                    "Chicken burger with lettuce and tomatoes", 6.75);
         //   addMenuItemRating(item2, 3, "Just alright", rater2,2);
            
            MenuItem item3 = addMenuItem(restaurant1,"main", "Burger Combo", "food", 
                    "Cheese Burger and fries", 8.75);
         //   addMenuItemRating(item3, 5, "Was alright.", rater3,0);
            
            MenuItem item4 = addMenuItem(restaurant1,"main", "Coke", "drink", 
                    "Coke", 1.75);
        //    addMenuItemRating(item4, 4, "Typical drink", rater,2);
        //    addMenuItemRating(item4, 1, "Was served water instead", rater4,0);
            
            MenuItem item5 = addMenuItem(restaurant1,"desert", "Ice Cream", "food", 
                    "Strawberry Ice Cream. Yummy", 3.75);
        //    addMenuItemRating(item5, 1, "Ew", rater5,0);
        //    addMenuItemRating(item5, 5, "loved it", rater8,10);
            
            MenuItem item6 = addMenuItem(restaurant2,"main", "Chicken", "food", "Hot Grilled Chicken"
            , 8.75);
            MenuItem item7 = addMenuItem(restaurant2,"main", "Beef Burger", "food", 
                    "Hot grilled burger", 11.75);
        //    addMenuItemRating(item7, 4, "Decent", rater14,10);
            
            MenuItem item8 = addMenuItem(restaurant2,"side", "Fries", "food", 
                    "baked fries", 3.75);
        //    addMenuItemRating(item, 1, "Came for fries, got a baked potato...", rater15,4);
            
            MenuItem item9 = addMenuItem(restaurant2,"main", "Orange juice", "drink", 
                    "Orange juice", 1.65);
            
            MenuItem item10 = addMenuItem(restaurant4,"main", "Grilled halloumi", "food", "Cheese made from goat and sheep milk"
            , 6.75);
        //    addMenuItemRating(item10, 5, "Excellent", rater13,4);
            
            MenuItem item11 = addMenuItem(restaurant4,"main", "Falafel", "food", 
                    "fried chickpeas with herbs", 5.75);
        //    addMenuItemRating(item11, 5, "No idea what I ate but it was great", rater7,1);
            
            MenuItem item12 = addMenuItem(restaurant4,"side", "Fattoush", "food", 
                    "Salad with crispy lettus and crunchy squares of pita", 4.75);
            
            MenuItem item13 = addMenuItem(restaurant4,"main", "milk", "drink", 
                    "Milk", 1.85);
        //    addMenuItemRating(item13, 1, "Was expired.", rater4,29);

            MenuItem item14 = addMenuItem(restaurant5,"main", "Gobhi Aloo", "food", "Cauliflower with potatoes"
            , 4.75);
        //    addMenuItemRating(item14, 4, "Was interesting", rater2,5);
            
            MenuItem item15 = addMenuItem(restaurant5,"main", "Baati", "food", 
                    "Wheat flour", 5.25);
            MenuItem item16 = addMenuItem(restaurant5,"main", "Baigan bharta", "food", 
                    "Eggplant cooked with tomatoes", 7.75);
            MenuItem item17 = addMenuItem(restaurant5,"main", "Water", "drink", 
                    "Water", 1.25);
            
            MenuItem item18 = addMenuItem(restaurant3,"main", "Noodle Soup", "food", "Soup"
            , 4.75);
         //   addMenuItemRating(item18, 2, "Wasn't satisfied.", rater8,3);
            
            MenuItem item19 = addMenuItem(restaurant3,"main", "Chow mein", "food", 
                    "Chow mein mix", 6.75);
         //   addMenuItemRating(item19, 5, "Wasn't cooked properly", rater11,4);
            
            MenuItem item20 = addMenuItem(restaurant3,"main", "Fried Rice", "food", 
                    "Rice mix", 3.75);
            MenuItem item21 = addMenuItem(restaurant3,"main", "Water", "drink", 
                    "Water", 1.25);
          //  addMenuItemRating(item21, 5, "Best water I've ever had.", rater,7);
            
            MenuItem item22 = addMenuItem(restaurant6,"main", "Sandwich", "drink", 
                    "12 inch Sandwich", 7.25);
          //  addMenuItemRating(item22, 1, "I've ate bigger.", rater9,7);
            
            MenuItem item23 = addMenuItem(restaurant6,"side", "Cookie", "food", 
                    "Gingerbread Cookie", 1.25);
          //  addMenuItemRating(item23, 5, "Loving it", rater13,3);
            
            MenuItem item24 = addMenuItem(restaurant7,"side", "Coffee", "drink", 
                    "Refreshing coffee", 1.24);
          //  addMenuItemRating(item24, 5, "Amazing coffee", rater11,2);
            
            MenuItem item25 = addMenuItem(restaurant7,"side", "Cookie", "food", 
                    "Chocolat Cookie", 1.25);
          //  addMenuItemRating(item25, 1, "Worst cookie I've ever had", rater15,23);
            
            MenuItem item26 = addMenuItem(restaurant8,"main", "Pepperoni Pizza", "food", 
                    "Pizza with pepperoni and cheese", 6.24);
          //  addMenuItemRating(item26, 3, "Tasted fake", rater11,7);
            
            MenuItem item27 = addMenuItem(restaurant8,"main", "Cheese Pizza", "food", 
                    "Pizza with only cheese and tomato sauce", 6.24);
            
            MenuItem item28 = addMenuItem(restaurant9,"main", "Fried Chicken", "food", 
                    "24 fried chicken", 10.24);
          //  addMenuItemRating(item28, 5, "Awesome!", rater3,7);
            
            MenuItem item29 = addMenuItem(restaurant9,"side", "fries", "food", 
                    "30 fries filled in a box", 4.24);
          //  addMenuItemRating(item29, 3, "Didn't taste like what I thought it would", rater5,2);
            
            MenuItem item30 = addMenuItem(restaurant10,"main", "Hawaii Pizza", "food", 
                    "Pizza with cheese pepperoni and pineapple", 6.24);
          //  addMenuItemRating(item30, 5, "Cool pizza", rater11,1);
            
            MenuItem item31 = addMenuItem(restaurant10,"main", "Pepperoni Pizza", "food", 
                    "Pizza with cheese and pepperoni", 6.24);
          //  addMenuItemRating(item21, 5, "Similar to Pizza hut", rater6,14);
            
            MenuItem item32 = addMenuItem(restaurant10,"main", "Cheese Pizza", "food", 
                    "Pizza with cheese", 6.24);
          //  addMenuItemRating(item32, 1, "Pretty sure what I had technically wasn't a pizza", rater4,0);
            
            MenuItem item33 = addMenuItem(restaurant11,"main", "Regular Burger", "food", 
                    "A burger with lettuce and tomatoes", 7.24);
          //  addMenuItemRating(item33, 5, "Acceptable.", rater3,7);
            
            MenuItem item34 = addMenuItem(restaurant11,"side", "fries", "food", 
                    "Fries to serve with a burger", 2.24);
           // addMenuItemRating(item34, 1, "Eh didn't like them", rater,7);
            
            MenuItem item35 = addMenuItem(restaurant11,"main", "Cream Soda", "drink", 
                    "Soda drink", 1.84);
           // addMenuItemRating(item35, 3, "Amazing<3.", rater7,3);
            
            MenuItem item36 = addMenuItem(restaurant12,"main", "Coffee", "drink", 
                    "Warm Coffee", 1.44);
           // addMenuItemRating(item36, 5, "Better than I thought.", rater14,12);
            
            MenuItem item37 = addMenuItem(restaurant12,"main", "Chocolat Donut Ring", "food", 
                    "Chocolat glazed donut", 1.48);
           // addMenuItemRating(item37, 3, "Not the best, not the worst", rater15,2);
            
            MenuItem item38 = addMenuItem(restaurant12,"main", "Cruller", "food", 
                    "glazed donut", 1.48);
           // addMenuItemRating(item38, 5, "Didn't realize it was donut. What a surprise!", rater12,6);
            
            MenuItem item39 = addMenuItem(restaurant12,"main", "Plain bagel", "food", 
                    "Bagel with butter served on the side", 1.20);
           // addMenuItemRating(item39, 5, "I love plain bagels.", rater,7);
            
            MenuItem item40 = addMenuItem(restaurant12,"main", "Smile", "N/A", 
                    "A free smile!", 0);
           // addMenuItemRating(item40, 1, "How is this a menu item?", rater9,0);
          //  addMenuItemRating(item40, 1, "All lies. There was no smile.", rater10,11);
            
            
            
            
            utx.commit();
        } catch (Exception e) {
            int i = 1;
        }
    }
    
    private Owner createOwner(String email, String userName, String firstName, String lastName, String password) {
        java.util.Calendar cal = Calendar.getInstance();
        java.sql.Date sqlDate = new java.sql.Date(cal.getTime().getTime());
        UserAccount account = new UserAccount();
        account.setEmail(email);
        account.setUsername(userName);
        account.setFirstname(firstName);
        account.setLastname(lastName);
        setPassword(account, password);
        account.setType("online");
        account.setJoindate(sqlDate);

        Owner user = new Owner();
        user.setUserAccount(account);

        em.persist(account);
        em.persist(user);
        return user;
    }
    
    private Rater createRater(String email, String userName, String firstName, String lastName, String password, int rep) {
        java.util.Calendar cal = Calendar.getInstance();
        java.sql.Date sqlDate = new java.sql.Date(cal.getTime().getTime());
        UserAccount account = new UserAccount();
        account.setEmail(email);
        account.setUsername(userName);
        account.setFirstname(firstName);
        account.setLastname(lastName);
        setPassword(account, password);
        account.setType("online");
        account.setJoindate(sqlDate);

        Rater user = new Rater();
        user.setReputation(rep);
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
        restaurant6 = new Restaurant();
        restaurant7 = new Restaurant();
        restaurant8 = new Restaurant();
        restaurant9 = new Restaurant();
        restaurant10 = new Restaurant();
        restaurant11 = new Restaurant();
        restaurant12 = new Restaurant();
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
        restaurant6.setName("Subway");
        restaurant6.setUrl("http://w.subway.com/en-ca/");
        restaurant6.setType("American");
        restaurant7.setName("Starbucks Coffee");
        restaurant7.setUrl("http://www.starbucks.ca/");
        restaurant7.setType("American");
        restaurant8.setName("Pizza Hut");
        restaurant8.setUrl("https://www.pizzahut.ca/");
        restaurant8.setType("American");
        restaurant9.setName("KFC");
        restaurant9.setUrl("http://www.kfc.ca/");
        restaurant9.setType("American");
        restaurant10.setName("Domino's Pizza");
        restaurant10.setUrl("http://www.dominos.ca/");
        restaurant10.setType("American");
        restaurant11.setName("Burger King");
        restaurant11.setUrl("http://burgerking.ca/");
        restaurant11.setType("American");
        restaurant12.setName("Tim Hortons");
        restaurant12.setUrl("http://www.timhortons.com/");
        restaurant12.setType("American");
        
        em.persist(restaurant1);
        em.persist(restaurant2);
        em.persist(restaurant3);
        em.persist(restaurant4);
        em.persist(restaurant5);
        em.persist(restaurant6);
        em.persist(restaurant7);
        em.persist(restaurant8);
        em.persist(restaurant9);
        em.persist(restaurant10);
        em.persist(restaurant11);
        em.persist(restaurant12);
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
    
    private void addMenuItemRating(MenuItem menuItem, int rate1, String comment, Rater rate, int likes){ 
        
        java.util.Calendar cal = Calendar.getInstance();
        java.sql.Date sqlDate = new java.sql.Date(cal.getTime().getTime());
        List<RatingItem> menuItemRatings;
        if (menuItem.getRatings() == null)
            menuItemRatings = new ArrayList<RatingItem>();
        else
            menuItemRatings = menuItem.getRatings();
        List<RatingItem> raterItemRatings;
        if (rate.getItemratings() == null)
            raterItemRatings = new ArrayList<RatingItem>();
        else
            raterItemRatings = rate.getItemratings();
        RatingItem rating = new RatingItem();
        rating.setRating(rate1);
        rating.setComments(comment);
        rating.setRater(rate);
        rating.setMenuitem(menuItem);
        rating.setVisitdate(sqlDate);
        rating.setRatingdate(sqlDate);
        rating.setLikes(likes);
        menuItemRatings.add(rating);
        raterItemRatings.add(rating);
        menuItem.setRatings(menuItemRatings);
        rate.setItemratings(raterItemRatings);
        em.merge(menuItem);
        em.merge(rate);
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
