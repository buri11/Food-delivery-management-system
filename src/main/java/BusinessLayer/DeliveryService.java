package BusinessLayer;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The type Delivery service.
 */
public class DeliveryService implements IDeliveryServiceProcessing, Serializable{
    private List<MenuItem> menuItems = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private HashMap<Order, ArrayList<MenuItem>> orderItems = new HashMap<>();

    public boolean wellFormed(){
        for(MenuItem m : menuItems){
            if(m.getPrice() < 0 || m.getId() < 0)
                return false;
        }
        for(User u : users){
            if(u.getUserName().equals("")){
                return false;
            }
        }
        for(Order o : orderItems.keySet()){
            if(o.getPrice() < 0 || o.getId() < 0){
                return false;
            }
        }
        return true;
    }

    public void readCSV(String fileName){
        File inputF = new File(fileName);
        InputStream inputFS = null;
        try {
            inputFS = new FileInputStream(inputF);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
        List<MenuItem> mergedList;
        List<MenuItem> l1 = menuItems;
        List<MenuItem> l2;
        l2 = br.lines().skip(1).map((line) -> {
            String []row = line.split(",");
            BaseProduct produs = new BaseProduct();
            produs.setTitle(row[0]);
            produs.setRating(Float.parseFloat(row[1]));
            produs.setCalories(Float.parseFloat(row[2]));
            produs.setProtein(Float.parseFloat(row[3]));
            produs.setFat(Float.parseFloat(row[4]));
            produs.setSodium(Float.parseFloat(row[5]));
            produs.setPrice(Float.parseFloat(row[6]));
            return produs;
        }).distinct().collect(Collectors.toList());
        //am folosit distinct ca sa scot elementele duplicate

        //dupa aceea sortam lista dupa titlul produselor
        l2 = l2.stream().sorted(Comparator.comparing(MenuItem::getTitle)).collect(Collectors.toList());

        //se seteaza id-ul tuturor produselor
        l2 = l2.stream().peek(MenuItem::setID).collect(Collectors.toList());

        //concatenam lista initiala cu cea citita si eliminam elementele duplicate
        mergedList = Stream.concat(l1.stream(), l2.stream()).distinct().collect(Collectors.toList());

        menuItems = mergedList;
    }

    public boolean addMenuItem(MenuItem m){
        /**
         * @invariant
         */
        assert wellFormed():"Data has been corrupted!";

        if ( findItemByTitle(m.getTitle()) != null)
            return false;
        menuItems.add(m);
        m.setID();
        //se sorteaza elementele dupa titlu
        menuItems = menuItems.stream().sorted(Comparator.comparing(MenuItem::getTitle)).collect(Collectors.toList());
        /**
         * @invariant
         */
        assert wellFormed():"Data has been corrupted!";

        return true;
    }

    public void addOrder(Order o, ArrayList<MenuItem> orderedItems) throws IOException {
        /**
         * @invariant
         */
        assert wellFormed():"Data has been corrupted!";

        orderItems.put(o, orderedItems);
        findUserByUsername(o.getClientUsername()).incrementTimesOrdered();

        FileWriter fileWriter = new FileWriter("bill" + o.getId() + ".txt");
        fileWriter.write("Order number " + o.getId() + "\n\n");
        for(MenuItem m : orderedItems){
            //incrementam contorul pentru numarul de dati cand a fost comandat produsul
            m.incrementOrderedNb();

            fileWriter.write(m.getTitle());
            for(int j = m.getTitle().length(); j <= 75; j++){
                fileWriter.write(".");
            }
            fileWriter.write(m.getPrice() + "\n");
        }
        fileWriter.write("Total: " + o.getPrice());
        fileWriter.close();
        /**
         * @invariant
         */
        assert wellFormed():"Data has been corrupted!";
    }

    public List<MenuItem> findMenuItems(String title, float minRating, float maxCals, float maxProteins, float maxFats,
                                        float maxSodium, float maxPrice){
        return menuItems.stream().filter(menuItem -> menuItem.getTitle().contains(title) &&
                menuItem.getRating() >= minRating && menuItem.getCalories() <= maxCals &&
                menuItem.getProtein() <= maxProteins && menuItem.getFat() <= maxFats &&
                menuItem.getSodium() <= maxSodium && menuItem.getPrice() <= maxPrice).collect(Collectors.toList());
    }

    public boolean delMenuItem(int id){
        return menuItems.remove(findItemByID(id));
    }

    public MenuItem findItemByTitle(String title){
        return menuItems.stream().filter(menuItem -> title.equals(menuItem.getTitle())).findAny().orElse(null);
    }

    public User findUserByUsername(String username){
        return users.stream().filter(user -> user.getUserName().equals(username)).findAny().orElse(null);
    }

    public boolean editItemTitle(int id, String newTitle){
        MenuItem m = findItemByID(id);
        if(m == null){
            return false;
        }
        m.setTitle(newTitle);
        return true;
    }

    public boolean editItemRating(int id, float rating){
        MenuItem m = findItemByID(id);
        if(m == null){
            return false;
        }
        m.setRating(rating);
        return true;
    }

    public boolean editItemCals(int id, float cals){
        MenuItem m = findItemByID(id);
        if(m == null){
            return false;
        }
        m.setCalories(cals);
        return true;
    }

    public boolean editItemProtein(int id, float protein){
        MenuItem m = findItemByID(id);
        if(m == null){
            return false;
        }
        m.setProtein(protein);
        return true;
    }

    public boolean editItemFat(int id, float fat){
        MenuItem m = findItemByID(id);
        if(m == null){
            return false;
        }
        m.setFat(fat);
        return true;
    }

    public boolean editItemSodium(int id, float sodium){
        MenuItem m = findItemByID(id);
        if(m == null){
            return false;
        }
        m.setSodium(sodium);
        return true;
    }

    public boolean editItemPrice(int id, float price){
        /**
         * @precondition
         */
        assert price < 0:"Price is less than 0!";

        MenuItem m = findItemByID(id);
        if(m == null){
            return false;
        }
        m.setPrice(price);
        /**
         * @postcondition
         */
        assert m.getPrice() < 0:"Price is less than 0!";

        return true;
    }

    public MenuItem findItemByID(int id){
        return menuItems.stream().filter(menuItem -> id == menuItem.getId()).findAny().orElse(null);
    }

    //report 1
    public List<Order> findOrdersByHour(String startHour, String endHour){
        int stHour = Integer.parseInt(startHour);
        int enHour = Integer.parseInt(endHour);
        return orderItems.keySet().stream().filter(order -> order.getOrderDate().getHour() >= stHour &&
                                    order.getOrderDate().getHour() <= enHour).collect(Collectors.toList());
    }

    //report 2
    public List<MenuItem> findProdByTimesOrdered(int minOrd){
        return menuItems.stream().filter(menuItem -> menuItem.getOrderedFor() > minOrd).collect(Collectors.toList());
    }

    //report 3
    public List<User> findUserByTimesOrdered(int minOrders, int minOrderVal){
        List<Order> orders = orderItems.keySet().stream().filter(order -> order.getPrice() > minOrderVal).
                collect(Collectors.toList());

        List<User> clienti = new ArrayList<>();
        orders.stream().filter(order -> findUserByUsername(order.getClientUsername()).getTimesOrdered() > minOrders).
                forEach(order -> clienti.add(findUserByUsername(order.getClientUsername())));

        return clienti.stream().distinct().collect(Collectors.toList());
    }

    //report 4
    public List<MenuItem> findItemByDate(LocalDate date){
        List<Order> menuTemp = orderItems.keySet().stream().filter(order -> order.getOrderDate().toLocalDate().equals(date)).
                collect(Collectors.toList());

        List<MenuItem> result = new ArrayList<>();
        menuTemp.stream().forEach(order -> result.addAll(orderItems.get(order)));

        return result.stream().distinct().collect(Collectors.toList());
    }

    /**
     * Print menu items.
     */
    public void printMenuItems(){
        for(MenuItem m : menuItems){
            System.out.println(m.getId() + " " + m.getTitle() + " " + m.getPrice());
        }
    }

    public boolean addUser(User u){
        /**
         * @precondition
         */
        assert u.getUserName().equals(""):"Username is invalid!";

        User duplicate = users.stream().filter(user -> u.getUserName().equals(user.getUserName())).findAny().orElse(null);
        /**
         * @postcondition
         */
        assert duplicate != null:"Username has been taken!";

        users.add(u);

        return true;
    }

    /**
     * Gets users.
     *
     * @return the users
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    public boolean verifyUser(String username, String password, userType type) {
        User u = users.stream().filter(user -> (username.equals(user.getUserName()) && password.equals(user.getPassword()) && type.equals(user.getType()))).findAny().orElse(null);
        if ( u == null )
            return false;
        return true;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    /**
     * Get max order id int.
     *
     * @return the int
     */
    public int getMaxOrderId(){
        int max = 0;
        for(Order o : orderItems.keySet()){
            if(o.getId() > max){
                max = o.getId();
            }
        }
        return max;
    }

    /**
     * Gets order items.
     *
     * @return the order items
     */
    public HashMap<Order, ArrayList<MenuItem>> getOrderItems() {
        return orderItems;
    }
}
