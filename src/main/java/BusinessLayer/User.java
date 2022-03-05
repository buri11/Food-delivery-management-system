package BusinessLayer;

import java.io.Serializable;

/**
 * The type User.
 */
public class User implements Serializable {
    private String userName;
    private String password;
    private userType type;
    //for clients only
    private int timesOrdered = 0;

    /**
     * Instantiates a new User.
     *
     * @param userName the user name
     * @param password the password
     * @param type     the type
     */
    public User(String userName, String password, userType type){
        this.userName = userName;
        this.password = password;
        this.type = type;
    }

    /**
     * Increment times ordered.
     */
    public void incrementTimesOrdered(){
        timesOrdered++;
    }

    /**
     * Gets times ordered.
     *
     * @return the times ordered
     */
    public int getTimesOrdered() {
        return timesOrdered;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public userType getType() {
        return type;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }
}
