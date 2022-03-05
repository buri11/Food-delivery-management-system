package BusinessLayer;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The interface Delivery service processing.
 */
public interface IDeliveryServiceProcessing {
    /**
     * Read csv.
     *
     * @param fileName the file name
     */
    void readCSV(String fileName);

    /**
     * Add menu item boolean.
     *
     * @param m the m
     * @return the boolean
     */
    boolean addMenuItem(MenuItem m);

    /**
     * Del menu item boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean delMenuItem(int id);

    /**
     * Edit item title boolean.
     *
     * @param id       the id
     * @param newTitle the new title
     * @return the boolean
     */
    boolean editItemTitle(int id, String newTitle);

    /**
     * Edit item rating boolean.
     *
     * @param id     the id
     * @param rating the rating
     * @return the boolean
     */
    boolean editItemRating(int id, float rating);

    /**
     * Edit item cals boolean.
     *
     * @param id   the id
     * @param cals the cals
     * @return the boolean
     */
    boolean editItemCals(int id, float cals);

    /**
     * Edit item protein boolean.
     *
     * @param id      the id
     * @param protein the protein
     * @return the boolean
     */
    boolean editItemProtein(int id, float protein);

    /**
     * Edit item fat boolean.
     *
     * @param id  the id
     * @param fat the fat
     * @return the boolean
     */
    boolean editItemFat(int id, float fat);

    /**
     * Edit item sodium boolean.
     *
     * @param id     the id
     * @param sodium the sodium
     * @return the boolean
     */
    boolean editItemSodium(int id, float sodium);

    /**
     * Edit item price boolean.
     *
     * @param id    the id
     * @param price the price
     * @return the boolean
     */
    boolean editItemPrice(int id, float price);

    /**
     * Find item by id menu item.
     *
     * @param id the id
     * @return the menu item
     */
    MenuItem findItemByID(int id);

    /**
     * Well formed boolean.
     *
     * @return the boolean
     */
    abstract boolean wellFormed();

    /**
     * Add order.
     *
     * @param o            the o
     * @param orderedItems the ordered items
     * @throws IOException the io exception
     */
    void addOrder(Order o, ArrayList<MenuItem> orderedItems) throws IOException;

    /**
     * Find menu items list.
     *
     * @param title       the title
     * @param minRating   the min rating
     * @param maxCals     the max cals
     * @param maxProteins the max proteins
     * @param maxFats     the max fats
     * @param maxSodium   the max sodium
     * @param maxPrice    the max price
     * @return the list
     */
    List<MenuItem> findMenuItems(String title, float minRating, float maxCals, float maxProteins, float maxFats,
                                        float maxSodium, float maxPrice);

    /**
     * Find item by title menu item.
     *
     * @param title the title
     * @return the menu item
     */
    MenuItem findItemByTitle(String title);

    /**
     * Find user by username user.
     *
     * @param username the username
     * @return the user
     */
    User findUserByUsername(String username);

    /**
     * Find orders by hour list.
     *
     * @param startHour the start hour
     * @param endHour   the end hour
     * @return the list
     */
    List<Order> findOrdersByHour(String startHour, String endHour);

    /**
     * Find prod by times ordered list.
     *
     * @param minOrd the min ord
     * @return the list
     */
    List<MenuItem> findProdByTimesOrdered(int minOrd);

    /**
     * Find user by times ordered list.
     *
     * @param minOrders   the min orders
     * @param minOrderVal the min order val
     * @return the list
     */
    List<User> findUserByTimesOrdered(int minOrders, int minOrderVal);

    /**
     * Find item by date list.
     *
     * @param date the date
     * @return the list
     */
    List<MenuItem> findItemByDate(LocalDate date);

    /**
     * Add user boolean.
     *
     * @param u the u
     * @return the boolean
     */
    boolean addUser(User u);

    /**
     * Verify user boolean.
     *
     * @param username the username
     * @param password the password
     * @param type     the type
     * @return the boolean
     */
    boolean verifyUser(String username, String password, userType type);

    /**
     * Gets menu items.
     *
     * @return the menu items
     */
    List<MenuItem> getMenuItems();
}
