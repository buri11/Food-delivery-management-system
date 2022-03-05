package BusinessLayer;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * The type Order.
 */
public class Order implements Serializable {
    private String clientUsername;
    private LocalDateTime orderDate;
    private float price;
    private static int idCount = 1;
    private int id;

    /**
     * Instantiates a new Order.
     */
    public Order(){

    }

    /**
     * Instantiates a new Order.
     *
     * @param clientUsername the client username
     * @param date           the date
     * @param price          the price
     */
    public Order(String clientUsername, LocalDateTime date, float price){
        setId();
        this.clientUsername = clientUsername;
        orderDate = date;
        this.price = price;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Order){
            Order o = (Order) obj;
            if(this.id == o.getId()){
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * Sets id count.
     *
     * @param idCount the id count
     */
    public static void setIdCount(int idCount) {
        Order.idCount = idCount;
    }

    /**
     * Sets id.
     */
    public void setId() {
        id = idCount;
        idCount++;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets client username.
     *
     * @return the client username
     */
    public String getClientUsername() {
        return clientUsername;
    }

    /**
     * Gets order date.
     *
     * @return the order date
     */
    public LocalDateTime getOrderDate() {
        return orderDate;
    }
}
